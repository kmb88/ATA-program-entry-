
/**
 * Kiva sets facingDirection,Floormap,Pod and motorlifetime.
 * 
 * @author (Karen Bergan) 
 * @version (version 1 5/5/21)
 */
import edu.duke.Point;

public class Kiva {
    Point currentLocation;
    FacingDirection directionFacing;
    FloorMap map;
    boolean carryingPod;
    boolean successfullyDropped;
    double motorLifetime;
  
   /**
     * Constructor for Kiva object.
     * @param   map  A resprestentation of the Kiva enivironment.
     */
  public Kiva(FloorMap map){
    this.currentLocation = map.getInitialKivaLocation();
    directionFacing = FacingDirection.UP;
    carryingPod = false;
    successfullyDropped = false;
    this.map= map;
    motorLifetime=0;
    }
   
    /**
     * Constructor for Kiva object.
     * @param   newPoint  The initial starting Location of the Kiva robot.
     */
  public Kiva (Point newPoint){
    this.currentLocation = newPoint;
    directionFacing = FacingDirection.UP;
    motorLifetime=0;
    }
    
    /**
     * Constructor for Kiva object.
     * @param   map       A resprestentation of the Kiva enivironment.
     * @param   newPoint  The initial starting Location of the Kiva robot.
     * 
     */
  public Kiva(FloorMap map,Point newPoint){
    this.currentLocation = newPoint;
    directionFacing = FacingDirection.UP;
    this.map= map;
    motorLifetime=0;
    }
    
   /**
     * Gets the current location of the Kiva robot.
     * @return  Point object of the Kiva robot.
     * 
     */
  public Point getCurrentLocation(){
    return currentLocation;
    }
  
     /**
     * Checks if the Kiva robot is carrying a POD.
     * @return  boolean if the robot is carrying the POD.
     * 
     */
  public boolean isCarryingPod(){
    return carryingPod;
    }

   /**
     * Checks if Kiva robot dropped the POD.
     * @return  boolean if the POD was successfully dropped.
     * 
     */
  public boolean isSuccessfullyDropped(){
    return successfullyDropped;
    }
  
     /**
     * Tracks how many hours the kiva robot has been running.
     * @return  double object of the Kiva robot.
     * 
     */
  public double getMotorLifetime(){
       
    return motorLifetime;
    
    }
  
    /**
     * This method increments the method by 1000.
     * 
     */
  public void incrementMotorLifetime(){
    motorLifetime += 1000;
    }
  
  
    /**
     * This method moves depending on the KivaCommand.
     * @param command updates the current location of the Kiva robot.
     */
  public void move(KivaCommand command){ 
    //System.out.println(this.map.getObjectAtLocation(this.currentLocation));
    //System.out.println(this.currentLocation);
    switch(command){
        
            case FORWARD:
               this.moveForward();
                break;
            case TURN_LEFT:
               this.moveTurn_Left();
               break;
            case TURN_RIGHT:
               this.moveTurn_Right();
               break;
            case TAKE:
               this.Take();
               break;
            case DROP:
               this.Drop();
               break;
            default:
               this.moveForward();
               
            }
          
    }
          
   /**
     * This method moves the Kiva cart FORWARD.
     * 
     */
    
  private void moveForward() {
         // if(this.directionFacing==UP)
          Point temp = this.currentLocation;
          int x = temp.getX();
          int y= temp.getY();
          //Obstacle
          //
          int oldX= x;
          int oldY=y;
                      
         
     if(directionFacing==FacingDirection.UP){
            y--;
            }          
       
        else if( directionFacing==FacingDirection.LEFT){
            x--;
            }
        else if( directionFacing==FacingDirection.DOWN){
            y++;
            }
        else if( directionFacing==FacingDirection.RIGHT){
         x++;
            }
          this.currentLocation= new Point(x,y);
        
     if(x < 0 || x > this.map.getMaxColNum()){
        this.currentLocation=new Point (oldX, oldY);
        throw new IllegalMoveException("Out of bounds");
      }
     if(y < 0 || y > this.map.getMaxRowNum()){
       this.currentLocation=new Point (oldX, oldY);
       throw new IllegalMoveException("Out of bounds");
      }
     if(this.map.getObjectAtLocation(this.currentLocation)==FloorMapObject.OBSTACLE){
       Point tempPoint = new Point(x,y);
       this.currentLocation=new Point (oldX, oldY);
      throw new IllegalMoveException(String.format("IllegalMoveException: Can't move onto an obstacle at " + tempPoint+"!"));
     
    }
     this.incrementMotorLifetime();
   }
        
  public FacingDirection getDirectionFacing(){
     return directionFacing;
   
    }
  
   /**
     * This method moves the cart to the LEFT.
     * 
     */
  private void moveTurn_Left() {
      this.incrementMotorLifetime();
    if(directionFacing==FacingDirection.UP){
         directionFacing = FacingDirection.LEFT; 
    }
       
    else if( directionFacing==FacingDirection.LEFT){
        directionFacing=FacingDirection.DOWN;
        }
    else if( directionFacing==FacingDirection.DOWN){
        directionFacing=FacingDirection.RIGHT;
        }
    else if( directionFacing==FacingDirection.RIGHT){
        directionFacing=FacingDirection.UP;
        }
   }
   
   /**
     * This method moves the cart to the RIGHT.
     * 
     */
  private void moveTurn_Right() {
       this.incrementMotorLifetime();
          if(directionFacing==FacingDirection.UP){
         directionFacing = FacingDirection.RIGHT; 
    }
       
    else if( directionFacing==FacingDirection.LEFT){
        directionFacing=FacingDirection.UP;
        }
    else if( directionFacing==FacingDirection.DOWN){
        directionFacing=FacingDirection.LEFT;
        }
    else if( directionFacing==FacingDirection.RIGHT){
        directionFacing=FacingDirection.DOWN;
      }
  }
  
   /**
     * This method tells the Kiva robot to pick up a POD in the correct location and alerts the user if there are no PODs.
     * 
     */
  private void Take(){
    if(this.map.getObjectAtLocation(this.currentLocation)==FloorMapObject.POD){
       if (!this.isCarryingPod()){
            carryingPod= true;
         }
        else {  
           throw new NoPodException("currently carrying POD");
        }
      }
      else{
          throw new NoPodException(String.format("NoPodException: Can't take nonexistent pod from location " + this.currentLocation+"!"));
      }
  }
   
   /**
     * This method tells the Kiva robot to DROP a POD in the correct location and alerts the user if they are not in the DROPZONE or not carrying a POD.
     * 
     */
  private void Drop(){
      
    if(this.map.getObjectAtLocation(this.currentLocation)==FloorMapObject.DROP_ZONE){
     if (this.isCarryingPod()){
        carryingPod= false;
        this.successfullyDropped=true;
        
     } 
     else{
        throw new IllegalDropZoneException("No POD carried");
        //System.out.println("I'm sorry.The Kiva Robot did not pick up the pod and then drop it off in the right place");
     } 
   } 
   else{
      throw new IllegalDropZoneException(String.format("IllegalDropZoneException: Can't just drop pods willy-nilly at " + this.currentLocation +"!"));
   }
  }
}