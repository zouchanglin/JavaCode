package xpu.tim.test;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;

public class HTTPClientTest {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder(URI.create("http://zouchanglin.cn"))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(req, HttpResponse.BodyHandler.asString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.statusCode());
        System.out.println(response.version().name());
        System.out.println(response.body());
    }
}
