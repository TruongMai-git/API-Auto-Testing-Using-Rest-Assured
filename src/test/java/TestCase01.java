import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.common.RestUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase01 {
    @BeforeClass
    public void setup() throws Exception {
//         Set the base URI for RestAssured
        RestUtil.setBaseURI("https://api-gateway-qa2.dragoncapital.com.vn");
    }

        @Test
    public void TC01_GetMethodParam() throws Exception {
        RestUtil.setPath("/adapter/api/Province/GetForDropdown");
        // Specify the base URL to the RESTful web service
        Response response = RestUtil.getRequest(null, null,null);
        Helper.SprintOutResponseBody(response);
        List idd = response.jsonPath().getList("result.id");
        for (int i = 0; (Integer) idd.get(i) <= 71; i++) {
            System.out.println("id ==>  " + idd.get(i));
        }
        System.out.println("getStatusCode ==>" + response.getStatusCode());
        Assert.assertEquals(200, response.getStatusCode());
    }

        @Test
    public void TC02_GetMethodMultipleParam() throws Exception {
        RestUtil.setPath("/adapter/api/Province/GetAddressCodeList");
        // Define the query parameters
        Map<String, String> queryParams = new HashMap<>();
        RestUtil.addPairValues(queryParams,"provinceName", "Tỉnh Hoà Bình",
                "districtName", "Huyện Đà Bắc", "wardName", "Thị trấn Đà Bắc");
        Response response = RestUtil.getRequest(null, queryParams, null);
        System.out.println("getStatusCode ==>" + response.getStatusCode());
        Assert.assertEquals(200, response.getStatusCode());
        Helper.SprintOutResponseBody(response);
    }

    @Test
    public void TC03_PostMethod() throws Exception {
        RestAssured.baseURI = "https://google.serper.dev";//https://serper.dev/dashboard
        RestUtil.setPath("/search");
        String requestBody = "{\"q\":\"Where are you now?\"}";
        Map<String, String> headers = new HashMap<>();
        RestUtil.addPairValues(headers,"X-API-KEY"
                , "5b93ff8dd9e2faff1b91ece3e6126e1ba0ad7253", "Content-Type", "application/json");
        Response response = RestUtil.postRequest(headers, null, requestBody);
        System.out.println("getStatusCode ==>" + response.getStatusCode());
        Assert.assertEquals(200, response.getStatusCode());
        Helper.SprintOutResponseBody(response);
    }

    @AfterClass
    public void afterClass() {
        RestUtil.resetBaseURI();
        RestUtil.resetEndPoint();
    }


}


