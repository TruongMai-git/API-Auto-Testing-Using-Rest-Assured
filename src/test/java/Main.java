import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import utils.common.RestUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Main {
    @Test
    public void getSerperApiKey() throws Exception {
        RestUtil.setBaseURI("https://api.serper.dev");
        System.out.println(RestAssured.baseURI);
        RestUtil.setPath("/users/api-key");
        System.out.println(RestUtil.endpoints);
        String cookie = "_ga=GA1.1.1379991271.1722658905; _gcl_aw=GCL.1723651711."
                +"Cj0KCQjwh7K1BhCZARIsAKOrVqEC15Uq1ejcOVk1Rd6ZTXdQxnnjtda-fKeMKqxPwFM-uxXw5aO83pIaAkiiEALw_wcB;"
                +"_gcl_gs=2.1.k1$i1723651710; _clck=1kjf9aw%7C2%7Cfoh%7C0%7C1676; __cf_bm=P2mWvdYysoUqzMMOrzMz."
                +"yoIwJDJabQUdOq.pbukxc8-1724139993-1.0.1.1-fr34y6WlNoz7RN2vc5lvUUFAMxDXzoCpUfLx5AKefWR8hAiqrBNesG"
                +"_wggrP1EL7L4fFmoB.JL_dyWwaU0W2jA; _gcl_au=1.1.952281011.1722658904.79919033.1724139998.1724139998;"
                +"connect.sid=s%3AEixScdVmMaMx26mu3lSGDARdFVyiSnNe.PG84XvRZ7y9k0XFqk3RXavyPoMCCCjuP%2FSm7tM4mz%2BQ;"
                +"_ga_M5LDW4K59C=GS1.1.1724139989.12.1.1724140017.0.0.0; "
                +"_clsk=1q85o5u%7C1724140017939%7C3%7C1%7Cp.clarity.ms%2Fcollect";
        Map<String, String> queryParams = new HashMap<>();
        RestUtil.addPairValues( queryParams, "Cookie", cookie);
        Response response = RestUtil.getRequest(null, queryParams,null);
        System.out.println("getStatusCode ==>" + response.getStatusCode());
//        Helper.SprintOutResponseBody(response);
        String apiKey = response.jsonPath().getString("apiKey");
        System.setProperty("apiKey", apiKey);
        String envApiKey = System.getProperty("apiKey");
        System.out.println("apiKey is set in the environment: " + envApiKey);
    }

    @Test
    public void TestRandom(){
        for(int i =1; i<10; i++){
            System.out.println(Helper.RandomYopmail());
            System.out.println(Helper.RandomPhoneNumber10Char());
        }
    }
    @Test
    public void testRplaceFiles() throws IOException {
        String phoneNumber = Helper.RandomPhoneNumber10Char();
        String emailAddress = Helper.RandomYopmail();
        String requestBody = RestUtil.createRequestBody("C:\\Auto\\02-Project\\Test\\src\\main\\java\\utils\\files\\Register.json"
                        ,"email_address",emailAddress,"phone_number",phoneNumber);
        System.out.println(requestBody);
    }
    @AfterClass
    public void afterClass() {
        RestUtil.resetBaseURI();
        RestUtil.resetEndPoint();
    }

}
