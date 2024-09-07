package utils.common;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class RestUtil {
    //Global Setup Functions
    public static String endpoints;
    public static RequestSpecification request = RestAssured.given()
            .log().method()
            .log().uri();
    public static void setBaseURI(String baseURI) throws Exception {
        if (baseURI == null) {
            throw new Exception("Values must be in key-value pairs.");
        } else {
            RestAssured.baseURI = baseURI;
        }
    }

    public static String setPath(String path) throws Exception {
        if (path == null) {
            throw new Exception("Values must be not null.");
        } else {
            endpoints = RestAssured.baseURI + path;
        }
        return endpoints;
    }

    public static void resetEndPoint() {
        endpoints = null;
    }

        /*
    ***Reset Base URI Endpoint (after test)***
    After the test, we should reset the RestAssured.baseURI
    */

    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    /*
    ***Add keys and values***
    WWe add key and value into param or header
    */

    public static void addPairValues(Map<String, String> target, String... paramPairs) {
        if (paramPairs.length % 2 != 0) {
            throw new IllegalArgumentException("Pairs must be in key-value pairs.");
        }
        for (int i = 0; i < paramPairs.length; i += 2) {
            target.put(paramPairs[i], paramPairs[i + 1]);
        }
    }

    /*
    ***Generate Request Body***
    We generate request body based on createRequestBody
    */
    public static String createRequestBody(String filePath, String... values) throws IOException {
        if (values.length % 2 != 0) {
            throw new IllegalArgumentException("Values must be in key-value pairs.");
        }
        String body = new String(Files.readAllBytes(Paths.get(filePath)));

        for (int i = 0; i < values.length; i += 2) {
            body = body.replace("{{" + values[i] + "}}", values[i + 1]);
        }
        return body;
    }

    /*
     ***Validate input values NULL or NOT***
     */
    public static <T> Boolean Validation(T input) {
        if (input instanceof Map) {
            Map<?, ?> headersMap = (Map<?, ?>) input;
            return headersMap != null && !headersMap.isEmpty();
        } else if (input instanceof String) {
            String headersString = (String) input;
            return headersString != null && !headersString.isEmpty();
        }
        return false;
    }

    /*
    ***Send GET request***
    We send a "get" method with specified parameters
    and "get" method returns response of API
    */

    public static Response getRequest(Map<String, String> headers, Map<String, String> params, String requestBody) throws Exception {
        if (endpoints == null) {
            throw new Exception("Endpoint is null at GET request method");
        }
        if (Validation(headers) == true) {
            request.headers(headers);
        }
        if (Validation(params) == true) {
            request.queryParams(params);
        }
        if (Validation(requestBody) == true) {
            request.body(requestBody);
        }
        System.out.println("Send");
        return request.get(endpoints);
    }

    public static Response postRequest(Map<String, String> headers, Map<String, String> params, String requestBody) throws Exception {
        if (endpoints == null) {
            throw new Exception("Endpoint is null at POST request method");
        }

        if (Validation(headers) == true) {
            request = request.headers(headers);
        }
        if (Validation(params) == true) {
            request = request.queryParams(params);
        }
        if (Validation(requestBody) == true) {
            request = request.body(requestBody);
        }

        return request.post(endpoints);
    }

    public static Response putRequest(Map<String, String> headers, Map<String, String> params, String requestBody) throws Exception {
        if (endpoints == null) {
            throw new Exception("Endpoint is null at PUT request method");
        }

        if (Validation(headers) == true) {
            request = request.headers(headers);
        }
        if (Validation(params) == true) {
            request = request.queryParams(params);
        }
        if (Validation(requestBody) == true) {
            request = request.body(requestBody);
        }

        return request.put(endpoints);
    }

}