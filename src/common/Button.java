package common;

/**
 * Button
 * 
 * A Button has the following properties:
 * - Location (Elevator #, Floor #)
 * - Type (defines the type of event it causes)
 * - Lamp (each button is associated with a Lamp, each Lamp is associated with a Button)
 * 
 * When pressed, a button causes a(n):
 * - Event to occur
 * - Lamp to turn on
 */
public class Button {
    private Lamp lamp;

    /**
     * @return the status of the Button's lamp. TRUE = ON; FALSE = OFF
     */
    public boolean getStatus() {
        return lamp.getStatus();
    }

    // When button is pressed, call the following method:
    // lamp.setLampOn();
}
