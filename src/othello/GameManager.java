/*
GameManager är den som administrerar pågående parti. GameManagerns uppgift är enkel.
Den skall gång på gång, omväxlande, be de två spelarna att ange sitt nästa drag. 
Den skall också tala om för spelarna om de vunnit eller gjort ett felaktigt drag.
Eftersom GameManagern inte skall veta vilken typ av spelare den har att göra 
med skall dynamisk bindning användas vid anrop av spelarnas operationer.
 */
package othello;

import java.util.Scanner;

/**
 *
 * @author S153298
 */
public class GameManager {
    GameGrid board;
    Scanner in;

    public GameManager(){
        board = new GameGrid();
        in = new Scanner(System.in);
    }
    public void run(){
        int resetter = -1;
        int x = resetter;
        int y = resetter;
        int player;
        int turn = 0;
        while(true){
            player = turn % 2 + 1;
            while(!board.isLegalMove(player, x, y)){
                System.out.println("Player " + player + " Enter x");
                x = in.nextInt();
                System.out.println("Player " + player + " Enter y");
                y = in.nextInt();
            }
            Move move = new Move(x,y,player);
            board.add(move);
            turn++;
            board.printboardSkipZero();
            x = resetter;
            y = resetter;
        }
    }
}
