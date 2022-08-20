package org.e2e.config.execution;

public class ExecutionConfig {
    public static final String targetBrowser = System.getProperty("target_browser");
    public static final String targetEnvironment = System.getProperty("target_environment");
    public static final String browserSize = System.getProperty("browser_size");
    public static final Boolean globexOptimizationForTesting = Boolean.valueOf(System.getProperty("globex_optimized_for_test"));
    public static final Boolean headlessMode = Boolean.valueOf(System.getProperty("headless_mode"));
    public static final Long defaultTimeout = Long.valueOf(System.getProperty("default_timeout"));
    public static final String browserstackUser = System.getProperty("browserstack_user");
    public static final String browserstackAccessKey = System.getProperty("browserstack_access_key");
    public static final Long timeBetweenSteps = Long.valueOf(System.getProperty("time_between_steps"));
}
