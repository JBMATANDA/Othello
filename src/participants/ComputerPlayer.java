/*
Denna klass beräknar nästa drag utifrån den information som finns i klassen GameGrid.
Som du säkert inser är den besvärligaste metoden att konstruera en intelligent datorspelare.
För att inte fastna på detta nu, är det tillåtet att låta datorn vara hur dum som helst,
dvs. den genererar helt enkelt ett slumpmässigt tillåtet drag från en lista med alla möjliga 
tillåtna drag (istället för att beräkna ett drag från GameGrid).
Vi kommer att kika på hur man skapar smartare datorspelare i kursen intelligenta och lärande system.
 */
package participants;

import othello.Move;

/**
 *
 * @author optimusprime
 */
public class ComputerPlayer extends Player{
    int x;
    int y;
    
    public ComputerPlayer(String name, int markerId){
        setName(name);
        setMarkerId(markerId);
        x = 0;
        y = 0;
    }

    @Override
    public Move getMove() {
        int[] move = new int[3];
        move[2] = getMarkerId();
        if(y >= 8){            
            x++;
            y = y % 8;
        }
        move[1] = y++;
        x = x % 8;
        move[0] = x;
        Move realMove = new Move(move[0] ,move[1],move[2]);
        return realMove;
    }
}
