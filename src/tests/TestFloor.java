package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import common.*;
import floor.Floor;

class TestFloor {
	
	Floor floor;
	
	@Before
	void setup() {
		floor = new Floor(22);
	}

	@Test
	void testChangeButtonLampState() {
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
