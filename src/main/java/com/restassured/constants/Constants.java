package com.restassured.constants;

public class Constants {
	
	private Constants() {
		
	}

	public static final String EXCELPATH = System.getProperty("user.dir")+"/src/test/resources/testdata.xlsx";
	public static final String TESTDATASHEETNAME = "TestData";
	public static final String EXTENTREPORTPATH = System.getProperty("user.dir")+"/ExtentReports/index.html";
	
	public static final String EXTENTCONFIGFILEPATH = System.getProperty("user.dir")
			+"/src/test/resources/extentreport.xml";
	
	
	  public static final String SCHEMAVALIDATIONJSONPATH =
	  System.getProperty("user.dir")+
	  "/src/test/resources/jsonsforschemavalidations/response_schema.json";
	 
	
	public static final String RESPONSETXTPATH="./output/responses/";
	
	// Base URI and properties
	public static final String RUNMANAGERSHEET= "RUNMANAGER";
	public static final String JSONSLOCATION=System.getProperty("user.dir")+"/src/test/resources/jsons";
	public static final String BASEURL = "https://fakerestapi.azurewebsites.net";
	public static final String BASEURLFORTRON = "https://<IP_address>";
	public static final String USERNAME ="protractor";
	public static final String AUTHTYPE="password";
	public static final String PASSWORD= "12345678";
	public static final String FSI_LANGUAGE="";

	//Endpoints
	public static final String GETACTIVITYBYID = "/api/v1/Activities/{id}";
	public static final String CREATEACTIVITY = "/api/v1/Activities";
	public static final String UPDATEACTIVITYBYID = "/api/v1/Activities/{id}";
	public static final String DELETEACTIVITYBYID = "/api/v1/Activities/{id}";
	
	public static final String AUTH_ENDPOINT = "/tron/api/v1/token";
	
	//Request xml paths
	public static final String REQUEST_JSON_FOLDER_PATH =  System.getProperty("user.dir")+
			"/src/test/resources/jsonsforrequestbody/";


}
