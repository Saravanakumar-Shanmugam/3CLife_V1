package com.runner;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({LoginPageRunner.class})
@IncludeTags("positive")
@Execution(ExecutionMode.CONCURRENT) 
public class TestSuiteRunner {
	
}
