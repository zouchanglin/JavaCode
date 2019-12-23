package xpu.edu.elong.request;

public class HttpRequestUtilTest {

    public static void main(String[] args) {
        testRequest();
    }
    public static void testRequest(){
        //http://member.vip.elong.com:8080/member/getCardListByMobileAndEmail?mobile=
        String request = HttpRequestUtil.sendPostRequest("http://www.baidu.com");
        System.out.println(request);
    }
}