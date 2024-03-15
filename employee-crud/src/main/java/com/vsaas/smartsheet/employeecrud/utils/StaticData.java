package com.vsaas.smartsheet.employeecrud.utils;

import org.springframework.beans.factory.annotation.Value;


public class StaticData {

    @Value("${smartsheet.api.accesstoken}")
    public static String SHEET_ACCESS_TOKEN;

    public static String SHEET_NAME = "Employee";

    public static String WORKSPACE_NAME = "smart-sheet-poc";
}
