package tictactoe;

import tictactoe.enums.Symbol;
import tictactoe.models.Player;

public class Main {
    public static void main(String[] args) {
        TicTacToeSystem system = TicTacToeSystem.getInstance();

        Player alice = new Player("Alice", Symbol.X);
        Player bob = new Player("Bob", Symbol.O);

        // --- GAME 1: Alice wins ---
        System.out.println("--- GAME 1: Alice (X) vs. Bob (O) ---");
        system.createGame(alice, bob);
        system.printBoard();

        system.makeMove(alice, 0, 0);
        system.makeMove(bob, 1, 0);
        system.makeMove(alice, 0, 1);
        system.makeMove(bob, 1, 1);
        system.makeMove(alice, 0, 2); // Alice wins, scoreboard is notified
        System.out.println("----------------------------------------\n");

        // --- GAME 2: Bob wins ---
        System.out.println("--- GAME 2: Alice (X) vs. Bob (O) ---");
        system.createGame(alice, bob); // A new game instance
        system.printBoard();

        system.makeMove(alice, 0, 0);
        system.makeMove(bob, 1, 0);
        system.makeMove(alice, 0, 1);
        system.makeMove(bob, 1, 1);
        system.makeMove(alice, 2, 2);
        system.makeMove(bob, 1, 2); // Bob wins, scoreboard is notified
        System.out.println("----------------------------------------\n");

        // --- GAME 3: A Draw ---
        System.out.println("--- GAME 3: Alice (X) vs. Bob (O) - Draw ---");
        system.createGame(alice, bob);
        system.printBoard();

        system.makeMove(alice, 0, 0);
        system.makeMove(bob, 0, 1);
        system.makeMove(alice, 0, 2);
        system.makeMove(bob, 1, 1);
        system.makeMove(alice, 1, 0);
        system.makeMove(bob, 1, 2);
        system.makeMove(alice, 2, 1);
        system.makeMove(bob, 2, 0);
        system.makeMove(alice, 2, 2); // Draw, scoreboard is not notified of a winner
        System.out.println("----------------------------------------\n");

        // --- Final Scoreboard ---
        // We get the scoreboard from the system and print its final state
        system.printScoreBoard();
    }

    //    --- GAME 1: Alice (X) vs. Bob (O) ---
    //    Game started between Alice (X) and Bob (O).
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Alice plays at (0, 0)
    //-------------
    //        | X | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Bob plays at (1, 0)
    //-------------
    //        | X | _ | _ |
    //            -------------
    //            | O | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Alice plays at (0, 1)
    //-------------
    //        | X | X | _ |
    //            -------------
    //            | O | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Bob plays at (1, 1)
    //-------------
    //        | X | X | _ |
    //            -------------
    //            | O | O | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Alice plays at (0, 2)
    //[Scoreboard] Alice wins! Their new score is 1.
    //            -------------
    //            | X | X | X |
    //            -------------
    //            | O | O | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: WINNER_X
    //    Winner: Alice
    //----------------------------------------
    //
    //        --- GAME 2: Alice (X) vs. Bob (O) ---
    //    Game started between Alice (X) and Bob (O).
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Alice plays at (0, 0)
    //-------------
    //        | X | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Bob plays at (1, 0)
    //-------------
    //        | X | _ | _ |
    //            -------------
    //            | O | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Alice plays at (0, 1)
    //-------------
    //        | X | X | _ |
    //            -------------
    //            | O | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Bob plays at (1, 1)
    //-------------
    //        | X | X | _ |
    //            -------------
    //            | O | O | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Alice plays at (2, 2)
    //-------------
    //        | X | X | _ |
    //            -------------
    //            | O | O | _ |
    //            -------------
    //            | _ | _ | X |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Bob plays at (1, 2)
    //[Scoreboard] Bob wins! Their new score is 1.
    //            -------------
    //            | X | X | _ |
    //            -------------
    //            | O | O | O |
    //            -------------
    //            | _ | _ | X |
    //            -------------
    //    Game Status: WINNER_O
    //    Winner: Bob
    //----------------------------------------
    //
    //        --- GAME 3: Alice (X) vs. Bob (O) - Draw ---
    //    Game started between Alice (X) and Bob (O).
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Alice plays at (0, 0)
    //-------------
    //        | X | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Bob plays at (0, 1)
    //-------------
    //        | X | O | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Alice plays at (0, 2)
    //-------------
    //        | X | O | X |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Bob plays at (1, 1)
    //-------------
    //        | X | O | X |
    //            -------------
    //            | _ | O | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Alice plays at (1, 0)
    //-------------
    //        | X | O | X |
    //            -------------
    //            | X | O | _ |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Bob plays at (1, 2)
    //-------------
    //        | X | O | X |
    //            -------------
    //            | X | O | O |
    //            -------------
    //            | _ | _ | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Alice plays at (2, 1)
    //-------------
    //        | X | O | X |
    //            -------------
    //            | X | O | O |
    //            -------------
    //            | _ | X | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Bob plays at (2, 0)
    //-------------
    //        | X | O | X |
    //            -------------
    //            | X | O | O |
    //            -------------
    //            | O | X | _ |
    //            -------------
    //    Game Status: IN_PROGRESS
    //    Alice plays at (2, 2)
    //-------------
    //        | X | O | X |
    //            -------------
    //            | X | O | O |
    //            -------------
    //            | O | X | X |
    //            -------------
    //    Game Status: DRAW
    //----------------------------------------
    //
    //
    //        --- Overall Scoreboard ---
    //    Player: Bob        | Wins: 1
    //    Player: Alice      | Wins: 1
    //            --------------------------
}
