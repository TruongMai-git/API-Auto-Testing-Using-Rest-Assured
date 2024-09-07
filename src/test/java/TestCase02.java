import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.restUtilObject.PostRegister;
import utils.restUtilObject.SendOTP;


public class TestCase02 {

    private String phoneNumber;
    private String emailAddress;


    @BeforeClass
    public void setUp(){
        phoneNumber = Helper.RandomPhoneNumber10Char();
        emailAddress = Helper.RandomYopmail();
        System.out.println("Phone and Email ==> \n" + phoneNumber + "\n" +emailAddress);


    }
    @Test
    public void testSendOtp() throws Exception {
        Response otpResponse = SendOTP.sendOtpPostRequest(phoneNumber);
        Helper.SprintOutResponseBody(otpResponse);
        Assert.assertEquals(200, Helper.getHttpStatus(otpResponse));
//        Assert.assertEquals(true, Helper.getItem(otpResponse, "otpCheck.isVerified"));



    }
    @Test
    public void testSendRegister() throws Exception {
        Response otpResponse = PostRegister.sendRegisterPostRequest(emailAddress, phoneNumber);
        Helper.SprintOutResponseBody(otpResponse);
        Assert.assertEquals(200, otpResponse.getStatusCode());
    }
}

