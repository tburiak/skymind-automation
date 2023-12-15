package com.westpac;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
/*
Cucumber tags are mapped to JUnit tags. Note that the @ symbol is not part of the JUnit tag
https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-junit-platform-engine#tags
E.g.,
@IncludeTags(value = {"releaseregression | Commodity"})
@ExcludeTags(value = {"Credit"})
Use annotations ONLY for local running, for CI should be empty (not {""}, but {}).
*/
@IncludeTags(value = {"ratesOldFrameworkMigration & lifeCycleEvent=Amendment & regime=CANADA"})
@ExcludeTags(value = {})

public class TestSuiteRunner {
}
