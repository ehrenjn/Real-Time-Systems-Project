package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import common.*;
import floor.Floor;

public class TestFloor {
	
	private Floor floor;
	
	@Before
	public void setup() {
		floor = new Floor(22);
	}

	@Test
	public void testChangeButtonLampState() {
		floor = new Floor(22);
		floor.changeButtonLampState(Direction.UP, LampState.ON);
		assertEquals(floor.getUpButton(), LampState.ON);
		
		floor.changeButtonLampState(Direction.UP, LampState.OFF);
		assertEquals(floor.getUpButton(), LampState.OFF);
		
		floor.changeButtonLampState(Direction.DOWN, LampState.ON);
		assertEquals(floor.getDownButton(), LampState.ON);
		
		floor.changeButtonLampState(Direction.DOWN, LampState.OFF);
		assertEquals(floor.getDownButton(), LampState.OFF);
	}

}
