package com.restassured.testcases;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.restassured.constants.Constants;
import com.restassured.utils.RandomUtils;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class PostRequestsTests extends BaseTest{

	

	/*
	 * There should be a test case matching this test name in RUNMANAGER and TESTDATA sheet
	 * If there are multiple data lines in TESTDATA sheet, it will treated as iteration
	 * Mark Yes in RUNMANAGER and TESTDATA to run this test case
	 * @author jkhanna
	 */
	
	
	@Test
	public void postActivityDetails(Hashtable<String, String> data) throws IOException {
		/*
		 * Replacing the name parameter in the endpoint with the data from excel sheet.
		 * Data providers return a hastable and the column name is used as a key to get the value
		 */
		
		HashMap<String, Object> requestParams = new HashMap<String, Object>();
		requestParams.put("id", "55");
		requestParams.put("title", "Activity 55");
		requestParams.put("dueDate", RandomUtils.todaysDate());
		requestParams.put("completed", true);
		
		
		Response response=	given()
				.filter(new RequestLoggingFilter(printStrObj)) //This line is mandatory to log the request details to extent report
				.log()
				.all()
				.header("Content-Type", ContentType.JSON)
				.body(requestParams.toString())
				.post(Constants.BASEURL+Constants.CREATEACTIVITY);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		//TODO Assert.assertEquals (headers, )

		/*
		 * Writing the request and response to extent report
		 *  
		 */
		writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
		
		//Asserting the country code in the response using jsonPath. 
		//Expected value is from TESTDATA sheet and column activity
		Assert.assertEquals(response.jsonPath().get("[0].title"), data.get("expectedActivity"));
		
		//Writing the response to an log file
		Files.write(response.asByteArray(), 
				new File(Constants.RESPONSETXTPATH+data.get("TestCaseName")+data.get("Id")+".txt"));
		
	}

}
