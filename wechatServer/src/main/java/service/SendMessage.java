package service;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * Created by sdww on 2016/4/29.
 */
public class SendMessage {
    private static final String baseUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    private static final String userOpenId = "oZIE1wjDVibVnXstHH_F4TnGMmyM";

    public void send(String message) {
        String url = baseUrl + Service.accessToken;
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
//        postMethod.addParameter("access_token", Service.accessToken);
//
        String baseBody = "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"Hello World\"}}";
        JSONObject jsonObject = JSONObject.parseObject(baseBody);
        jsonObject.replace("touser", userOpenId);
        JSONObject text = jsonObject.getJSONObject("text");
        text.replace("content", message);

        String str = jsonObject.toJSONString();
//        postMethod.addParameter("body", jsonObject.toJSONString());


        try {
            RequestEntity requestEntity = new StringRequestEntity(str, "text/xml","UTF-8");
            postMethod.setRequestEntity(requestEntity);
            int status = httpClient.executeMethod(postMethod);
            if(status == 200) {
                //JSONObject resp = JSONObject.parseObject(postMethod.getResponseBodyAsString());
                System.out.print(postMethod.getResponseBodyAsString());
            }
        }catch (Exception e) {
            System.out.print(e.getStackTrace());
        }
    }

    public void getOpenId() {
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + Service.accessToken + "&next_openid=";
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        try {
            int i = httpClient.executeMethod(getMethod);
            if(i == 200) {
                //JSONObject jsonObject = JSONObject.parseObject(getMethod.getResponseBodyAsString());
                //jsonObject.get("")
            }
            System.out.println(getMethod.getResponseBodyAsString());
        } catch (Exception e) {
            System.out.print(e.getStackTrace());
        }
    }

    public static void main(String[] args) {
        new Service().getAccessToken();
        new SendMessage().send("wan shang ce shi");
        //new SendMessage().getOpenId();
    }
}
