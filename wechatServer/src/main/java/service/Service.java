package service;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Created by sdww on 2016/4/29.
 */
public class Service {

    public static String accessToken = "yyy";
    public static final String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx670a420a7e8fbaa1&secret=c83316595a42319d21ce59101ebea32c";

    public void getAccessToken() {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(accessTokenUrl);
        try {
            int i = httpClient.executeMethod(getMethod);
            if(i == 200) {
                JSONObject jsonObject = JSONObject.parseObject(getMethod.getResponseBodyAsString());
                accessToken = (String)jsonObject.get("access_token");
            }
            System.out.println(accessToken);
        } catch (Exception e) {
            System.out.print(e.getStackTrace());
        }
    }

    public static void main(String[] args) {
        new Service().getAccessToken();
    }
}
