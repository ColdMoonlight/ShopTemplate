package com.atguigu.bean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {
	
	public static final String ClientId = "AQyXf-N2nNr8QwJsFt7IudPRL-CMGYEXCCzgqOHIA037JLhSFOEchb2kGa_z_BqzKY4CmUPFiGqG_uNj";  
    public static final String ClientSecret = "EO5N6EtaEiIQXF18UWWZJGGeB8VL4qMxC-jR4tvHoXJD0RBdZGzcCguUBuRgWNBR8Lk-ge8XRK379NCl";  
    public static final String Mode = "sandbox";
	
	//@Value("${paypal.client.clientId}")
	private String clientId="AQyXf-N2nNr8QwJsFt7IudPRL-CMGYEXCCzgqOHIA037JLhSFOEchb2kGa_z_BqzKY4CmUPFiGqG_uNj";
    //private String clientId="AQyXf-N2nNr8QwJsFt7IudPRL-CMGYEXCCzgqOHIA037JLhSFOEchb2kGa_z_BqzKY4CmUPFiGqG_uNj";
    //真实private String clientId="Ad0_fWFpJ2XCHI4xZY3mywHctvdm0rNIvltKnN3bxE_1j56ZK7b-HOzyhrw07ZZWFZRIBzUPJajU-CGW";
    
    //@Value("${paypal.client.secret}")
    private String clientSecret="EO5N6EtaEiIQXF18UWWZJGGeB8VL4qMxC-jR4tvHoXJD0RBdZGzcCguUBuRgWNBR8Lk-ge8XRK379NCl";
    //private String clientSecret="EO5N6EtaEiIQXF18UWWZJGGeB8VL4qMxC-jR4tvHoXJD0RBdZGzcCguUBuRgWNBR8Lk-ge8XRK379NCl";
    //真实private String clientSecret="ECTB6nSnyAo0S7W7rNiZCsiKMTG5qEOCRYO6wYDEO7sBsVU5rpAHDqVXwzqKhPriWGn39JfFXcmq1biq";
    
    //@Value("${paypal.mode}")
    private String mode="sandbox";
    //真实private String mode="sandbox";
    //真实private String mode="live";

    @Bean
    public Map<String, String> paypalSdkConfig(){
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", mode);
        return sdkConfig;
    }

    @Bean
    public OAuthTokenCredential authTokenCredential(){
        return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException{
        APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
        apiContext.setConfigurationMap(paypalSdkConfig());
        return apiContext;
    }
}
