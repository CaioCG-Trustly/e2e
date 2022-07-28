package org.e2e.config.execution;

public enum Browser {
    CHROME("Chrome"),
    SAFARI("Safari");

    public final String browserName;

    Browser(String browserName) {
        this.browserName = browserName;
    }

//    public static Browser valueOf(String browserName) {
//        for (Browser b: values()) {
//            if (b.browserName.equals(browserName)) {
//                return b;
//            }
//        }
//
//        return null;
//    }
}
