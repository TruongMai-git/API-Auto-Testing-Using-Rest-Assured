import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.common.RestUtil;

import java.util.*;

public class Helper {
    //Verify the api response

    //Verify the http response status returned
    public static int getHttpStatus(Response res) {
        if (res != null) {
            int statusCode = res.getStatusCode();
        } else {
            System.out.println("Response is null at checking http status");
        }
        return res.getStatusCode();
    }

    //Get item list
    public static List getItemList(JsonPath jp, String path) {
        List itemList = jp.getList(path);

        return itemList;
    }

    //Find Duplicate Items
    public static boolean findDuplicateItems(List<Integer> itemsList) {
        for (int i = 0; i < itemsList.size(); i++) {
            if (Collections.frequency(itemsList, itemsList.get(i)) > 1) {
                System.out.println("This item is duplicated: " + itemsList.get(i));
                return false;
            }
        }
        return true;
    }

    public static Response getSerperApiKey() throws Exception {
        RestUtil.setBaseURI("https://api.serper.dev");
        RestUtil.setPath("/users/api-key");
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
        return RestUtil.getRequest(null, queryParams,null);
    }

    public static void SprintOutResponseBody(Response response) {
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        System.out.println("Json Body: \n" + jsonObject.toString(4));
    }
        /*
    ***Print response in Json type***
    Print out API response
    */

    //Get item
    public static Object getItem(Response response, String path) {
        return response.jsonPath().get(path);
    }


    public static String RandomYopmail() {
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyz1234567890";
        String DOMAIN = "yopmail.com";
        Random random = new Random();
        StringBuilder email = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            email.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return "tmtest_" + email.append("@").append(DOMAIN);
    }

    public static String RandomPhoneNumber10Char() {
        Random random = new Random();
        String phoneNumber = String.valueOf(100000000 + random.nextInt(900000000));
        return String.valueOf(phoneNumber);
    }


}