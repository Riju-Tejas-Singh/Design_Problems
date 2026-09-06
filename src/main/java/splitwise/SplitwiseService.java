package splitwise;

import splitwise.entities.*;

import java.util.*;
import java.util.stream.Collectors;

public class SplitwiseService {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Group> groups = new HashMap<>();

    private SplitwiseService() {
    }

    private static class Holder {
        private static final SplitwiseService INSTANCE = new SplitwiseService();
    }

    public static SplitwiseService getInstance() {
        return Holder.INSTANCE;
    }

    // --- Setup Methods ---
    public User addUser(String name) {
        User user = new User(name);
        users.put(user.getId(), user);
        return user;
    }

    public Group addGroup(String name, List<User> members) {
        Group group = new Group(name, members);
        groups.put(group.getId(), group);
        return group;
    }

    // --- Core Functional Methods (Facade) ---
    public synchronized void createExpense(Expense expense) {
        User paidBy = expense.getPaidBy();

        for (Split split : expense.getSplits()) {
            User participant = split.getUser();
            double amount = split.getAmount();

            if (!paidBy.equals(participant)) {
                paidBy.getBalanceSheet().adjustBalance(participant, amount);
                participant.getBalanceSheet().adjustBalance(paidBy, -amount);
            }
        }
        System.out.println("Expense '" + expense.getDescription() + "' of amount " + expense.getAmount() + " created.");
    }

    public synchronized void settleUp(String payerId, String payeeId, double amount) {
        User payer = users.get(payerId);
        User payee = users.get(payeeId);
        System.out.println(payer.getName() + " is settling up " + amount + " with " + payee.getName());
        // Settlement is like a reverse expense. payer owes less to payee.

        payee.getBalanceSheet().adjustBalance(payer, -amount);
        payer.getBalanceSheet().adjustBalance(payee, amount);
    }

    public void showBalanceSheet(String userId) {
        User user = users.get(userId);
        user.getBalanceSheet().showBalances();
    }

    public List<Transaction> simplifyGroupDebts(String groupId) {
        Group group = groups.get(groupId);
        if (group == null) {
            throw new IllegalArgumentException("Group not found");
        }

        // Calculate net balance for each member within the group context
        Map<User, Double> netBalances = new HashMap<>();

        for (User member : group.getMembers()) {
            double balance = 0;

            for (Map.Entry<User, Double> entry :
                    member.getBalanceSheet().getBalances().entrySet()) {

                // Consider only balances with other group members
                if (group.getMembers().contains(entry.getKey())) {
                    balance += entry.getValue();
                }
            }

            netBalances.put(member, balance);
        }

        // Max heap for creditors: largest positive balance first
        PriorityQueue<Map.Entry<User, Double>> creditors =
                new PriorityQueue<>(
                        Map.Entry.<User, Double>comparingByValue().reversed()
                );

        // Max heap for debtors: largest debt first
        PriorityQueue<Map.Entry<User, Double>> debtors =
                new PriorityQueue<>(
                        Map.Entry.<User, Double>comparingByValue()
                );

        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
            if (entry.getValue() > 0) {
                creditors.offer(entry);
            } else if (entry.getValue() < 0) {
                debtors.offer(entry);
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        while (!creditors.isEmpty() && !debtors.isEmpty()) {

            Map.Entry<User, Double> creditor = creditors.poll();
            Map.Entry<User, Double> debtor = debtors.poll();

            double amountToSettle =
                    Math.min(creditor.getValue(), -debtor.getValue());

            transactions.add(
                    new Transaction(
                            debtor.getKey(),
                            creditor.getKey(),
                            amountToSettle
                    )
            );

            double creditorRemaining =
                    creditor.getValue() - amountToSettle;

            double debtorRemaining =
                    debtor.getValue() + amountToSettle;

            // If creditor still needs money, put them back into heap
            if (Math.abs(creditorRemaining) >= 0.01) {
                creditor.setValue(creditorRemaining);
                creditors.offer(creditor);
            }

            // If debtor still owes money, put them back into heap
            if (Math.abs(debtorRemaining) >= 0.01) {
                debtor.setValue(debtorRemaining);
                debtors.offer(debtor);
            }
        }

        return transactions;
    }

}
