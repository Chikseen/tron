package com.github.mrpaulblack.tron;
import java.util.Arrays;
import java.util.UUID;

/**
* <h1>Player</h1>
* <p>Player class for each player in the game. Contains information like name, clientName and clientVersion as well as 
* as tailLenght which is mainly used for the two movement arrays positionX and positionY. Main identifier is the playerID
* which is generated by the server. Included is also the movement but not the CollisionDetection. The setter for the movement arrays
* is the move method.</p>

* @author: swt_lerngruppe_tron
* @version 1.0
* @since   2022-01-19
*/
public class Player{

    private String name;
    private String clientName;
    private Float clientVersion;
    private UUID playerID;      //müsste eigentlich raus, wenn ich sie als Identifier für die Hashmap im Game nutze
    private Integer tailLenght = 1;
    protected Integer[] positionX;   //startpositionen über eine Funktion setzen? Am Besten im Construktor irgnedwie klären!
    protected Integer[] positionY;
    private PlayerColor color;
    private Boolean alive = false;
    private Boolean ready = false;
    private char direction;
    

    /**
	 * <h1><i>Player</i></h1>
	 * <p>Player constructor which mainly serves as a constructor for a dummy-client in order to keep name and color changeable.</p>
	 * @param clientName - String with the name of the client
	 * @param clientVersion - Float with the version of the client
     * @param playerID - Main identifier for each player. 
     * @param tailLenght - Lenght of the player, used for the movement arrays. Must be subtracted by 1 so that the position arrays have the right lenght.
	 */
    public Player(String clientName, Float clientVersion, UUID playerID, Integer tailLenght){
        this.clientName = clientName;
        this.clientVersion = clientVersion;
        this.tailLenght = tailLenght- 1;
        this.playerID = playerID;
        positionX = new Integer [tailLenght]; 
        positionY = new Integer [tailLenght];
    }

    /**
	 * <h1><i>setStartDirection</i></h1>
	 * <p>Method that sets the initial direction for a player based on the position of the spawn to avoid instant collision after start.</p>
	 * @param fieldSize - Integer that determins the maximum field size.
	 */
    public void setStartDirection(int fieldSize){
        if(positionX[0] == 0){
            direction = 'E';
        }
        else if(positionX[0] == fieldSize){
            direction = 'W';
        }
        else if(positionY[0] == 0){
            direction = 'S';
        }
        else if(positionY[0] == fieldSize){
            direction = 'N';
        }
        else{}
    }

    /**
	 * <h1><i>changeDirection</i></h1>
	 * <p>Method to change the direction relative to the current direction.</p>
	 * @param fieldSize - integer that indicates a directional change or not, communicated from client. 0 = no change | 1 = left turn | 2 = right turn
	 */
    public void changeDirection(int newDirection){
        if (newDirection == 0){}

        else if ((direction == 'N') && (newDirection == 1)){
            direction = 'W';
        }
        else if ((direction == 'N') && (newDirection == 2)){
            direction = 'E';
        }
        else if ((direction == 'E') && (newDirection == 1)){
            direction = 'N';
        }
        else if ((direction == 'E') && (newDirection == 2)){
            direction = 'S';
        }
        else if ((direction == 'S') && (newDirection == 1)){
            direction = 'E';
        }
        else if ((direction == 'S') && (newDirection == 2)){
            direction = 'W';
        }
        else if ((direction == 'W') && (newDirection == 1)){
            direction = 'S';
        }
        else if ((direction == 'W') && (newDirection == 2)){
            direction = 'N';
        }
    }

	/**
	 * <h1><i>getDirection</i></h1>
	 * <p>This method returns the players current direction.</p>
	 * @return char - Current direction (North, South, East, West).
	 */
    public char getDirection(){
        return direction;
    }

    /**
	 * <h1><i>setReadyPlayer</i></h1>
	 * <p>Method to set the Ready Status as well as name and color as the last two are chosen by each player and locked in by pressing ready.</p>
	 * @param name - String to be set as the chosen name by eahc player.
	 * @param color - ENUM PlayerColor to set the Color for each player.
	 */
    protected void setReadyPlayer(String name, PlayerColor color){
        this.ready = true;
        this.name = name;
        this.color = color;
        //playerCount++
    }

    /**
	 * <h1><i>setUnReadyPlayer</i></h1>
	 * <p>Method to set the Ready Status as well as clearing name and color as the last two can be changed by each player.</p>
	 * @param name - String to be set as the chosen name by eahc player.
	 * @param color - ENUM PlayerColor to set the Color for each player.
	 */
    protected void setUnreadyPlayer(){
        this.ready = false;
        this.name = "";
        this.color = PlayerColor.UNDEFINED;
        //playerCount--
    }

    /**
	* <h1><i>setName</i></h1>
	* <p>Sets player name.</p>
	* @param name - String as name of the player.
	*/
    protected void setName(String name){
        this.name = name;
    }

	/**
	 * <h1><i>getName</i></h1>
	 * <p>This method returns the players name.</p>
	 * @return String - Name of the player
	 */
    protected String getName(){
        return name;
    }

    /**
	* <h1><i>setClientName</i></h1>
	* <p>Sets player clientName.</p>
	* @param clientName - String as clientName of the player.
	*/
    protected void setClientName(String clientName){
        this.clientName = clientName;
    }

	/**
	 * <h1><i>getClientName</i></h1>
	 * <p>This method returns the players clientname.</p>
	 * @return String - clientName of the player
	 */
    protected String getClientName(){
        return clientName;
    }

    /**
	* <h1><i>setClientVersion</i></h1>
	* <p>Sets player clientVersion.</p>
	* @param clientName - Float as ClientVersion of the player.
	*/
    protected void setClientVersion(Float clientVersion){
        this.clientVersion = clientVersion;
    }

	/**
	 * <h1><i>getClientVersion</i></h1>
	 * <p>This method returns the players clientVersion.</p>
	 * @return Float - Float Number to identify the player clientVersion.
	 */
    protected Float getClientVersion(){
        return clientVersion;
    }

    /**
	* <h1><i>setPlayerID</i></h1>
	* <p>Sets playerID.</p>
	* @param playerID - UUID as playerID and therefor main identifier for each player.
	*/
    protected void setPlayerID(UUID playerID){
        this.playerID = playerID;
    }

    /**
	 * <h1><i>getPlayerID</i></h1>
	 * <p>This method returns the playerID.</p>
	 * @return UUID - Main identifier for the player.
	 */
    protected UUID getPlayerID(){
        return playerID;
    }

    /**
	* <h1><i>setTailLenght</i></h1>
	* <p>Sets player tail lenght.</p>
	* @param tailLenght - Integer as tail lenght for each player.
	*/
    protected void setTailLenght(Integer tailLenght){
        this.tailLenght = tailLenght;
    }

    /**
	 * <h1><i>getTailLenght</i></h1>
	 * <p>This method returns the players tailLenght.</p>
	 * @return Integer - Lenght of the of the player.
	 */
    protected Integer getTailLenght(){
        return tailLenght;
    }

    /**
	* <h1><i>setColor</i></h1>
	* <p>Sets player color.</p>
	* @param color - ENUM PlayerColor as color for each player.
	*/
    protected void setColor(PlayerColor color){
        this.color = color;
    }

    /**
	 * <h1><i>getColor</i></h1>
	 * <p>This method returns the players color.</p>
	 * @return ENUM PlayerColor - Color of the player.
	 */
    protected PlayerColor getColor(){
        return color;
    }

    /**
	* <h1><i>setAlive</i></h1>
	* <p>Sets player alive status.</p>
	* @param alive - Boolean set the alive status for each player.
	*/
    protected void setAlive(Boolean alive){
        this.alive = alive;
    }

    /**
	 * <h1><i>getAlive</i></h1>
	 * <p>This method returns the players alive status.</p>
	 * @return Boolean - Alive status of the player - true for still participating 
     * - false for spectating / eliminated.
	 */
    protected Boolean getAlive(){
        return alive;
    }

    /**
	* <h1><i>setReady</i></h1>
	* <p>Sets player ready status.</p>
	* @param ready - Boolean set the ready status for each player.
	*/
    protected void setReady(Boolean ready){
        this.ready = ready;
    }
    
    /**
	 * <h1><i>getReady</i></h1>
	 * <p>This method returns the players ready status.</p>
	 * @return Boolean - Ready status of the player - true for pressed Ready
     * - false for not pressed Ready.
	 */
    protected Boolean getReady(){
        return ready;
    }
    
    /**
	 * <h1><i>move</i></h1>
	 * <p>This method executes one move and changes the positions in the movement arrays.</p>
	 */
    protected void move(){
        if (alive == true){
            for(int i = tailLenght; i>0; i--){
                positionX[i] = positionX[i-1];
                positionY[i] = positionY[i-1];
            }
            switch(direction){
                case 'N':
                positionY[0] = positionY[0]-1;
                break;
    
                case 'E':
                positionX[0] = positionX[0]+1;
                break;
    
                case 'S':
                positionY[0] = positionY[0]+1;
                break;
    
                case 'W':
                positionX[0] = positionX[0]-1;
                break;
            }
        }
        else{
            Arrays.fill(positionX, null);
            Arrays.fill(positionY, null);
        }
    }

    /**
	 * <h1><i>getPositionX</i></h1>
	 * <p>This method returns the players current position on the X-axis.</p>
	 * @return Integer - That stands for the corresponding "pixel" on the field.
	 */
    protected Integer getPositionX(Integer requestedPositionX){
        if((requestedPositionX <= tailLenght) && requestedPositionX >= 0){
            return positionX[requestedPositionX];
        }
        else{ //kleiner 0 hinzufügen
            return null;    //wenn null dann muss in collisiondetector dann null als invalid definiert werden.
        }
    }

    /**
	 * <h1><i>getPositionY</i></h1>
	 * <p>This method returns the players current position on the Y-axis..</p>
	 * @return Integer - That stands for the corresponding "pixel" on the field.
	 */
    protected Integer getPositionY(Integer requestedPositionY){
        if((requestedPositionY <= tailLenght) && requestedPositionY >= 0){
            return positionY[requestedPositionY];
        }
        else{
            return null;
        }
    }

    protected void setPositionXY(int[] newPosition){
        
        positionX[0] = newPosition[0];
        positionY[0] = newPosition[1];
    }
}
