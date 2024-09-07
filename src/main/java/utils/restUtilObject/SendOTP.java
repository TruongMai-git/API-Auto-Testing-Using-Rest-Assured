package utils.restUtilObject;

import io.restassured.response.Response;
import utils.common.RestUtil;

import java.util.HashMap;
import java.util.Map;

public class SendOTP {
    public static Response sendOtpPostRequest(String phoneNumber) throws Exception {
        RestUtil.setBaseURI("https://api-gateway-qa2.dragoncapital.com.vn");
        RestUtil.setPath("/investor/api/v1/Prospect/SendOtpToVerifyTradingAccount");
        Map<String, String> headers = new HashMap<>();
        RestUtil.addPairValues(headers, "phoneNumber", phoneNumber);
        RestUtil.postRequest(null, headers, null);
        return RestUtil.postRequest(headers, null, null);
    }
}
