package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * Suite to run all test cases
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({
	tests.TestEventReader.class
})

public class AllTests {}
