package snakeandladder;

import snakeandladder.models.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<BoardEntity> boardEntities = List.of(
                new Snake(17, 7), new Snake(54, 34),
                new Snake(62, 19), new Snake(98, 79),
                new Ladder(3, 38), new Ladder(24, 33),
                new Ladder(42, 93), new Ladder(72, 84)
        );

        Board board = new Board(100, boardEntities);
        List<String> players = Arrays.asList("Alice", "Bob", "Charlie");
        Dice dice = new Dice(1,6);

        Game game = new Game.Builder().setBoard(board).setPlayers(players).setDice(dice).build();
        game.play();
    }
    //    Alice's turn. Rolled a 2.
    //    Alice moved from 0 to 2.
    //
    //    Bob's turn. Rolled a 2.
    //    Bob moved from 0 to 2.
    //
    //    Charlie's turn. Rolled a 2.
    //    Charlie moved from 0 to 2.
    //
    //    Alice's turn. Rolled a 3.
    //    Alice moved from 2 to 5.
    //
    //    Bob's turn. Rolled a 6.
    //    Bob moved from 2 to 8.
    //    Bob rolled a 6 and gets another turn!
    //
    //    Bob's turn. Rolled a 6.
    //    Bob moved from 8 to 14.
    //    Bob rolled a 6 and gets another turn!
    //
    //    Bob's turn. Rolled a 4.
    //    Bob moved from 14 to 18.
    //
    //    Charlie's turn. Rolled a 2.
    //    Charlie moved from 2 to 4.
    //
    //    Alice's turn. Rolled a 2.
    //    Alice moved from 5 to 7.
    //
    //    Bob's turn. Rolled a 4.
    //    Bob moved from 18 to 22.
    //
    //    Charlie's turn. Rolled a 3.
    //    Charlie moved from 4 to 7.
    //
    //    Alice's turn. Rolled a 3.
    //    Alice moved from 7 to 10.
    //
    //    Bob's turn. Rolled a 6.
    //    Bob moved from 22 to 28.
    //    Bob rolled a 6 and gets another turn!
    //
    //    Bob's turn. Rolled a 5.
    //    Bob moved from 28 to 33.
    //
    //    Charlie's turn. Rolled a 5.
    //    Charlie moved from 7 to 12.
    //
    //    Alice's turn. Rolled a 1.
    //    Alice moved from 10 to 11.
    //
    //    Bob's turn. Rolled a 5.
    //    Bob moved from 33 to 38.
    //
    //    Charlie's turn. Rolled a 6.
    //    Charlie moved from 12 to 18.
    //    Charlie rolled a 6 and gets another turn!
    //
    //    Charlie's turn. Rolled a 5.
    //    Charlie moved from 18 to 23.
    //
    //    Alice's turn. Rolled a 3.
    //    Alice moved from 11 to 14.
    //
    //    Bob's turn. Rolled a 1.
    //    Bob moved from 38 to 39.
    //
    //    Charlie's turn. Rolled a 5.
    //    Charlie moved from 23 to 28.
    //
    //    Alice's turn. Rolled a 3.
    //    Oh no! Alice was bitten by a snake 🐍 at 17 and slid down to 7.
    //
    //    Bob's turn. Rolled a 3.
    //    Wow! Bob found a ladder 🪜 at 42 and climbed to 93.
    //
    //    Charlie's turn. Rolled a 4.
    //    Charlie moved from 28 to 32.
    //
    //    Alice's turn. Rolled a 5.
    //    Alice moved from 7 to 12.
    //
    //    Bob's turn. Rolled a 4.
    //    Bob moved from 93 to 97.
    //
    //    Charlie's turn. Rolled a 2.
    //    Charlie moved from 32 to 34.
    //
    //    Alice's turn. Rolled a 5.
    //    Oh no! Alice was bitten by a snake 🐍 at 17 and slid down to 7.
    //
    //    Bob's turn. Rolled a 2.
    //    Bob moved from 97 to 99.
    //
    //    Charlie's turn. Rolled a 4.
    //    Charlie moved from 34 to 38.
    //
    //    Alice's turn. Rolled a 2.
    //    Alice moved from 7 to 9.
    //
    //    Bob's turn. Rolled a 1.
    //    Hooray! Bob reached the final square 100 and won!
    //    Game Finished!
    //    The winner is Bob!
}
