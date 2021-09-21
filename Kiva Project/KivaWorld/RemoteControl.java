import edu.duke.FileResource;
import java.util.Arrays;

/**
 * This is the class that controls Kiva's actions. 
 
 * 
 */
public class RemoteControl {
    KeyboardResource keyboardResource;

    /**
     * This method is the letter representation of the moves Kiva robot can take.
     */
    public RemoteControl() {
        keyboardResource = new KeyboardResource();
    }

    /**
     * This method controller that directs Kiva's activity. Prompts the user for the floor map
     * to load, displays the map, and asks the user for the commands for Kiva to execute.
     *
     * 
     */
   public void run() {
        System.out.println("Please select a map file.");
        FileResource fileResource = new FileResource();
        String inputMap = fileResource.asString();
        FloorMap floorMap = new FloorMap(inputMap);
        System.out.println(floorMap);
        Kiva kiva= new Kiva(floorMap);
        System.out.println("Please enter the directions for the Kiva Robot to take.");
        String directions = keyboardResource.getLine();
        System.out.println("Directions that you typed in: " + directions);
        KivaCommand[] commands = convertToKivaCommands(directions);
        for(int i =0; i<directions.length();i++){
            System.out.println(commands[i]);
            kiva.move(commands[i]);
        }
        if(kiva.successfullyDropped && commands[directions.length()-1]==KivaCommand.DROP){
            System.out.println("Successfully picked up the pod and dropped it off.Thank you!");
        }
        else{
            System.out.println("I'm sorry. The Kiva Robot did not pick up the pod then drop it off in the right place.");
    }
}
   /**
     * This method takes a string which will be the commands that the user types into the console.
     * @param commands updates the current location of the Kiva robot.
     */
   public KivaCommand[] convertToKivaCommands(String commands){
       KivaCommand[] arr = new KivaCommand[commands.length()];
       for(int i = 0; i< commands.length();i++){
           switch(commands.charAt(i)){
               case 'F':
                    arr[i]= KivaCommand.FORWARD;
                    break;
               case 'R':
                    arr[i]= KivaCommand.TURN_RIGHT;
                    break;
               case 'L':
                    arr[i]= KivaCommand.TURN_LEFT;
                    break;
               case 'D':
                    arr[i]= KivaCommand.DROP;
                    break;
               case 'T':
                    arr[i]= KivaCommand.TAKE;
                    break;
               default: throw new IllegalArgumentException("This character does not correspond to a command please use: F,L,R,D,T");
            }
        }
       
      
       return arr;
    }
}
