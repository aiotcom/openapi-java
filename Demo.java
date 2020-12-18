import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * @Description JavaHttp请求例子
 * @Author www.stepiot.com
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        String url = "https://protobuf.stepiot.com/api/operate";
        String requestBody = "填入 http body";
        String response = Demo.post(url, requestBody);
        System.out.println(response);
    }

    private static String post(String requestUrl, String requestBody) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(requestBody);
        out.flush();
        out.close();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String lines;
        StringBuffer sb = new StringBuffer("");
        while ((lines = reader.readLine()) != null) {
            lines = URLDecoder.decode(lines, "utf-8");
            sb.append(lines);
        }
        reader.close();
        connection.disconnect();
        return sb.toString();
    }
}
