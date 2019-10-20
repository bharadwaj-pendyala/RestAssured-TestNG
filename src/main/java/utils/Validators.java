package utils;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class Validators {

    public static void validateStatusCode( Response response, int ExpectedCode){
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, ExpectedCode);
    }

    public static String getResponseAsString(Response response){
        return response.getBody().asString();
    }

    public static boolean assertResponeContains(Response response, String expectedValue){
        return getResponseAsString(response).contains(expectedValue);
    }

    public static void JSONResEvalString(Response response, String key, String expectedValue) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        String valReturned = jsonPathEvaluator.get(key);
        System.out.println(valReturned);
        System.out.println(expectedValue);
        Assert.assertTrue(valReturned.equalsIgnoreCase(expectedValue));
    }

    public static void JSONResEvalBoolean(Response response, String key, boolean expectedValue){
        JsonPath jsonPathEvaluator = response.jsonPath();
        boolean valReturned = jsonPathEvaluator.get(key);
        Assert.assertEquals(valReturned, expectedValue);
    }

    public static void validationPoints(Response response, int ExpectedCode, String key, String expectedValue){
        validateStatusCode(response,ExpectedCode);
        JSONResEvalString(response, key, expectedValue);
    }

    public static void validationPoints(Response response, int ExpectedCode, String key, boolean expectedValue){
        validateStatusCode(response,ExpectedCode);
        JSONResEvalBoolean(response, key, expectedValue);
    }

}
