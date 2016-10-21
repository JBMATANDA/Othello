/*
Då nästa drag begärs från GameManager väntar man tills användaren utfört draget.
 */
package participants;

import java.util.Scanner;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import othello.Move;

/**
 *
 * @author optimusprime
 */
public class HumanPlayer extends Player{
    Scanner in;
    
    
    public HumanPlayer(String name, int markerId){
        setName(name);
        setMarkerId(markerId);
        in = new Scanner(System.in);
    }

    @Override
    public Move getMove() {
        Move move;
        System.out.println("Player " + getName() + " Enter x");
        int x = in.nextInt();
        System.out.println("Player " + getName() + " Enter y");
        int y = in.nextInt();
        move = new Move(x,y, getMarkerId());
        return move;
    }
}