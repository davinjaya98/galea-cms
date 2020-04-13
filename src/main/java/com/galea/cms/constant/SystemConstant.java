package com.galea.cms.constant;

public class SystemConstant {

    public static final String SUCCESS = "000000";
    public static final String FAILED = "111111";
    public static final String FAILED_MAXIMUM_ATTEMPT = "222222";
    public static final String FAILED_AUTHENTICATION_FAILED = "333333";
    public static final Character ACTIVE = 'A';
    public static final Character DEACTIVE = 'D';
    public static final Character LOG_SUCCESS = 'S';
    public static final Character LOG_FAILED = 'F';

    // Constant for custom data
    public static final int CUSTOM_DATA_SINGLE = 1;
    public static final int CUSTOM_DATA_ARRAY = 2;
    public static final int CUSTOM_DATA_MULTIFIELD = 3;

    // Constant for custom data setting
    public static final int CUSTOM_DATA_SETTING_TEXTFIELD = 1;
    public static final int CUSTOM_DATA_SETTING_BOOLEAN = 2;
    public static final int CUSTOM_DATA_SETTING_URL_PATH = 3;
    public static final int CUSTOM_DATA_SETTING_TEXTAREA = 4;
    public static final int CUSTOM_DATA_SETTING_IMAGE_BLOB = 5;
    public static final int CUSTOM_DATA_SETTING_RICH_TEXT_WYSWYG = 6;
}
