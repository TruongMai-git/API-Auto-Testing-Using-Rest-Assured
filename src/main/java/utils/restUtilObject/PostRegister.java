package utils.restUtilObject;

import io.restassured.response.Response;
import utils.common.RestUtil;
import java.util.HashMap;
import java.util.Map;

public class PostRegister {
    public static Response sendRegisterPostRequest(String emailAddress, String phoneNumber) throws Exception {
        RestUtil.setBaseURI("https://api-gateway-qa2.dragoncapital.com.vn");
        RestUtil.setPath("/investor/api/v1/Prospect/Register");
        String requestBody =
                RestUtil.createRequestBody("C:\\Auto\\02-Project\\Test\\src\\main\\java\\utils\\files\\Register.json"
                        ,"email_address",emailAddress,"phone_number",phoneNumber);
        System.out.println("Json Body after replacing ==> \n"+ requestBody);
        Map<String, String> headers = new HashMap<>();
        RestUtil.addPairValues(headers, "Content-Type", "application/json");
        return RestUtil.postRequest(headers, null, requestBody);
    }

}
