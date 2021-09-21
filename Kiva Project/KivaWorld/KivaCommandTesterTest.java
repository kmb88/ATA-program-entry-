
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Write a description of KivaCommandTesterTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class KivaCommandTesterTest {
    @Test
     public void testLeft(){
        KivaCommand command = KivaCommand.TURN_LEFT;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
        }
    @Test
     public void testForward(){
        KivaCommand command = KivaCommand.FORWARD;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
        }
    @Test
        public void testRight(){
        KivaCommand command = KivaCommand.TURN_RIGHT;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
        }
    @Test
        public void testTake(){
        KivaCommand command = KivaCommand.TAKE;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
        }
    @Test
        public void testDrop(){
        KivaCommand command = KivaCommand.DROP;
        System.out.println(command);
        System.out.println(command.getDirectionKey());
        }
       
    }