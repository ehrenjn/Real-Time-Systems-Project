package common;

/**
 * A enum representing the direction of an elevator
 */

public enum Direction{
    UP("up"), DOWN("down");

    String key;

    Direction(String key) { 
    	this.key = key; 
    }

    /**
     * Returns an Enum of a given key specified by the string
     * @param key A string that is a key to return an Enum value
     */
    public static Direction getValue(String key) {
    	//Normalize the direction
    	key = key.toLowerCase();
        if ("up".equals(key)) { 
        	return UP; 
        }
        else if ("down".equals(key)) {
        	return DOWN;
        }
        else {
        	throw new IllegalArgumentException("Illegal Key Argument");
        }
    }
}