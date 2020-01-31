package common;

/**
 * Lamp
 * 
 * A Lamp has the following properties:
 * - Status
 * - Associated Button
 * 
 * A Lamp can be turned on  only when the associated Button is pressed.
 *      This is accomplished by having the method public, but only available to call by it's associated Button.
 * 
 * A Lamp can be turned off only when the associated Floor is reached by the Elevator.
 *      This is still TO-DO.
 */
public class Lamp {
    private boolean lampStatus;

    /** 
     * @return returns the boolean status of the Lamp. TRUE = ON; FALSE = OFF
     */
    public boolean getStatus() {
        return lampStatus;
    }

    /**
     * @return set the Lamp status to ON (TRUE)
     */
    public void setLampOn() {
        lampStatus = true;
        return;
    }

    /**
     * @return set the Lamp status to OFF (FALSE)
     */
    public void setLampOff() {
        lampStatus = false;
        return;
    }
}
