package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * Suite to run all test cases
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({
	tests.TestCommunicationSocket.class,
	tests.TestElevator.class,
	tests.TestElevatorFault.class,
	tests.TestEventReader.class,
	tests.TestEventSocket.class,
	tests.TestFloor.class
})

public class AllTests {}
