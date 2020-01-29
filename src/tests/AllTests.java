package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * Suite to run all test cases
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({
	tests.TestEvent.class,
	tests.TestCommunicationSocket.class
})

public class AllTests {}
