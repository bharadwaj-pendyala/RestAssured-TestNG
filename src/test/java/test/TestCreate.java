package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.PropertiesManager;
import utils.Validators;
import net.minidev.json.JSONObject;

import java.io.IOException;

public class TestCreate {

    PropertiesManager prop;
    RequestSpecification request;
    JSONObject requestParams;
    Validators val;
    boolean expectedVal = true;

    @BeforeSuite
    public void init(){
        try {
            prop.initializeProperties();
            RestAssured.baseURI = prop.getProperty("baseURI");
            request = RestAssured.given();
            requestParams = new JSONObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test (priority=1)
    public void testCreateAPI() {

        requestParams.put("name", prop.getProperty("newChannelName"));
        request.header("Content-Type", "application/json");
        request.header("Authorization","Bearer "+ prop.getProperty("token"));
        request.body(requestParams.toJSONString());
        Response response = request.post(prop.getProperty("createChannelEP"));
        System.out.println(response.body().asString());
        val.validationPoints(response, 200, "channel.name", prop.getProperty("newChannelName"));
        System.out.println("Validation of Create Channel API has been successful");

    }

    @Test (priority=2)
    public void testJoinAPI(){

        requestParams.put("name", prop.getProperty("newChannelName"));
        request.header("Content-Type", "application/json");
        request.header("Authorization","Bearer "+ prop.getProperty("token"));
        request.body(requestParams.toJSONString());
        Response response = request.post(prop.getProperty("joinChannelEP"));
        val.validationPoints(response, 200, "channel.is_member", expectedVal);
        System.out.println("Validation of Join Channel API has been successful");
    }

    @Test (priority=3)
    public void testRenameChannelAPI(){

        requestParams.put("channel", prop.getProperty("renameChannelNameFromID"));
        requestParams.put("name", prop.getProperty("renameChannelTo"));
        request.header("Content-Type", "application/json");
        request.header("Authorization","Bearer "+ prop.getProperty("token"));
        request.body(requestParams.toJSONString());
        Response response = request.post(prop.getProperty("renameChannelEP"));

        val.validationPoints(response, 200, "ok", expectedVal );
        System.out.println("Validation of Rename Channel API has been successful");
    }

    @Test (priority=4)
    public void testListChannelsAPI(){

        requestParams.put("channel", prop.getProperty("exclude_members"));
        requestParams.put("name", prop.getProperty("exclude_archived"));
        requestParams.put("name", prop.getProperty("limit"));
        request.header("Content-Type", "application/json");
        request.header("Authorization","Bearer "+ prop.getProperty("token"));
        request.body(requestParams.toJSONString());
        Response response = request.post(prop.getProperty("listChannelEP"));

        val.validationPoints(response, 200, "ok", expectedVal );
        System.out.println("Validation of List Channels API has been successful");
    }

    @Test (priority=5)
    public void testChannelsArchiveAPI(){

        requestParams.put("channel", prop.getProperty("archiveChannelID"));
        request.header("Content-Type", "application/json");
        request.header("Authorization","Bearer "+ prop.getProperty("token"));
        request.body(requestParams.toJSONString());
        Response response = request.post(prop.getProperty("archiveChannelEP"));

        val.validationPoints(response, 200, "ok", expectedVal);
        System.out.println("Validation of Archive Channel API has been successful");
    }


}
