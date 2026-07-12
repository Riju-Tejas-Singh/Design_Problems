package snakeandladder;

import snakeandladder.enums.GameStatus;
import snakeandladder.models.Board;
import snakeandladder.models.Dice;
import snakeandladder.models.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> players;
    private GameStatus status;
    private Player winner;

    public Game(Builder builder) {
        this.board = builder.board;
        this.dice = builder.dice;
        this.players = builder.players;
        this.status = GameStatus.NOT_STARTED;
    }

    public static class Builder {
        private Board board;
        private Queue<Player> players;
        private Dice dice;

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder setDice(Dice dice) {
            this.dice = dice;
            return this;
        }

        public Builder setPlayers(List<String> playerNames) {
            this.players = new LinkedList<>();
            playerNames.forEach(player -> this.players.add(new Player(player)));
            return this;
        }

        public Game build() {
            if (board == null || players == null || dice == null) {
                throw new IllegalStateException("Board, Players, and Dice must be set.");
            }
            return new Game(this);
        }

    }

    public void play () {
        if (players.size() < 2) {
            System.out.println("Cannot start game. At least 2 players are required.");
            return;
        }

        this.status = GameStatus.RUNNING;
        System.out.println("Game started!");

        while (this.status == GameStatus.RUNNING) {
            Player currentPlayer = players.poll();
            if (currentPlayer == null) {
                break;
            }
            takeTurn(currentPlayer);
            players.add(currentPlayer);
        }

        System.out.println("Game Finished!");
        if (winner != null) {
            System.out.printf("The winner is %s!\n", winner.getName());
        }
    }

    private void takeTurn (Player player) {
        int roll = dice.roll();
        System.out.printf("\n%s's turn. Rolled a %d.\n", player.getName(), roll);

        int currentPosition = player.getPosition();
        int nextPosition = currentPosition + roll;

        if (nextPosition > board.getSize()) {
            System.out.printf("Oops, %s needs to land exactly on %d. Turn skipped.\n", player.getName(), board.getSize());
            return;
        }

        if (nextPosition == board.getSize()) {
            player.setPosition(nextPosition);
            this.status = GameStatus.FINISHED;
            this.winner = player;
            System.out.printf("Hooray! %s reached the final square %d and won!\n", player.getName(), board.getSize());
            return;
        }

        int finalPosition = board.getFinalPosition(nextPosition);
        player.setPosition(finalPosition);


        if (finalPosition > nextPosition) { // Ladder
            System.out.printf("Wow! %s found a ladder 🪜 at %d and climbed to %d.\n", player.getName(), nextPosition, finalPosition);
        } else if (finalPosition < nextPosition) { // Snake
            System.out.printf("Oh no! %s was bitten by a snake 🐍 at %d and slid down to %d.\n", player.getName(), nextPosition, finalPosition);
        } else {
            System.out.printf("%s moved from %d to %d.\n", player.getName(), currentPosition, finalPosition);
        }


        if (roll == 6) {
            System.out.printf("%s rolled a 6 and gets another turn!\n", player.getName());
            takeTurn(player);
        }
    }



}
