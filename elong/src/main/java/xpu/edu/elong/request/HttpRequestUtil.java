package xpu.edu.elong.request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequestUtil {
    public static String sendPostRequest(String urlStr){
        StringBuilder sb = new StringBuilder();
        try {
            //打开post通道
            URL url=new URL(urlStr);
            URLConnection urlCon=url.openConnection();
            //读返回
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),"UTF-8"));
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}