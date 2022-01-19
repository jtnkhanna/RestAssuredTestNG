package com.restassured.testcases;

import static io.restassured.RestAssured.given;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.restassured.constants.Constants;
import com.restassured.reports.ExtentReport;
import com.restassured.reports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTest {
	
	protected StringWriter writer;
	protected PrintStream printStrObj;
	
	
	/*
	 * Initializing the extent report
	 * @author jkhanna
	 */
	@BeforeSuite
	public void setUpSuite() {
		ExtentReport.initialize();
		
	}

	/*
	 * Flusing the extent report
	 * Opening the extent report automatically after the test suite execution.
	 * @author jkhanna
	 */
	
	@AfterSuite
	public void afterSuite() throws IOException  {
		ExtentReport.report.flush();
		File htmlFile = new File(Constants.EXTENTREPORTPATH);
		Desktop.getDesktop().browse(htmlFile.toURI());

	}

	/*
	 * This method helps to write the request and reponse to the extent report
	 * @author jkhanna
	 */
	@BeforeMethod
	public void setUp() {
		
		writer = new StringWriter();
		printStrObj = new PrintStream(new WriterOutputStream(writer), true);
	}

	
	
	/*
	 * Provided as an sample to handle bearer token based scenarios and to handle x-www-form-urlencoded content type.
	 * Perform auth once before class
	 * @author : jkhanna
	 */
	protected void performAuth() {

		Response response=given().header("Content-Type", "application/json").
				config(RestAssured.config()
						.encoderConfig(EncoderConfig.encoderConfig()
								.encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
				.contentType("application/x-www-form-urlencoded; charset=UTF-8")
				.formParam("username", Constants.USERNAME)
				.formParam("authType",  Constants.AUTHTYPE)
				.formParam("password",  Constants.PASSWORD)
				.post(Constants.BASEURL+Constants.AUTH_ENDPOINT);
		response.then().statusCode(201);
		//extract().path("the path to token");
		
		System.out.println("AUTH Successful");
	}
	
	/*
	 * Format the api string and log in Extent Report
	 * @author : jkhanna
	 * @param  : apicontent
	 */
	protected void formatAPIAndLogInReport(String content) {

		String prettyPrint = content.replace("\n", "<br>");
		LogStatus.info("<pre>" + prettyPrint + "</pre>");

	}


	/*
	 * Read the json file and convert to String
	 * @author : jkhanna
	 * @param  : filepath
	 */
	public String generateStringFromResource(String path) throws IOException {
		String temp="";
		try {
			temp= new String(Files.readAllBytes(Paths.get(path)));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return temp;

	}
	
	public void writeRequestAndResponseInReport(String request,String response) {

		LogStatus.info("---- Request ---");
		formatAPIAndLogInReport(request);
		LogStatus.info("---- Response ---");
		formatAPIAndLogInReport(response);
	}
	
}
