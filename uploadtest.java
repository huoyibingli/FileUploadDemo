package upload;
import java.io.*;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class uploadtest {
    public static void main(String[] args) {
        String url = "http://192.168.15.182/EcaHtmlWebServer/EcaServices/EcaFileManagerServer/Upload";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("User-Agent","SOHUWapRebot");
        httpPost.setHeader("Accept-Language","zh-cn,zh;q=0.5");
        httpPost.setHeader("Accept-Charset","UTF_8");
        httpPost.setHeader("Connection","keep-alive");
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.setMode(HttpMultipartMode.RFC6532);
        try {
            //填充参数
            File file = new File("G:\\TestDocument\\松鼠高清.png");
            entityBuilder.addPart("file", new FileBody(file));
            entityBuilder.addPart("remark", new StringBody("测试",Charset.forName(HTTP.UTF_8)));
            entityBuilder.addPart("groupGuid", new StringBody("3820f94b-c08c-42eb-ba36-4d31294c20ab"));
            entityBuilder.addPart("toRowGuid", new StringBody("{582a8961-73c5-47b4-b4ac-72925164381d}"));

            httpPost.setEntity(entityBuilder.build());
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            System.out.println(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
