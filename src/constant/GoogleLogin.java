package constant;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import entities.CustomerEntity;
import constant.Iconstant;

public class GoogleLogin {
	
	public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(
                        Form.form()
       .add("client_id", Iconstant.GOOGLE_CLIENT_ID)

                        .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)

                        .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)

                        .add("code", code)

                        .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)

                        .build()
                )
                .execute().returnContent().asString();
        
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }
	
	public static CustomerGoogleDto getCustomerInfo(final String accessToken) throws ClientProtocolException, IOException {

        String link = Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken;

        String response = Request.Get(link).execute().returnContent().asString();

        CustomerGoogleDto googlePojo = new Gson().fromJson(response, CustomerGoogleDto.class);

        return googlePojo;

    }

}
