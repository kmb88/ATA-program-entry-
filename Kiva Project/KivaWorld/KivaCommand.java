
/**
 * KivaCommand sets enums that are connected to Kiva and RemoteControl objects.
 * 
 * @return directionKey sets enums as the directions the Kiva will use.
 * 
 * @author (Karen Bergan) 
 * @version (1 5/2/21)
 */
   public enum KivaCommand {
    FORWARD('F'),
    TURN_LEFT('L'),
    TURN_RIGHT('R'),
    DROP('D'),
    TAKE('T');
    
    private char directionKey;
    
    private KivaCommand (char directionKey){
    this.directionKey= directionKey;
    }
    public char getDirectionKey(){
    return directionKey;
    }
    
   }
