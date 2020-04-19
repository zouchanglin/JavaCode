package tim.edu.spring_study.proxy;

public class Main {
    public static void main(String[] args) {
        Payment payment = new AliPay(new RealPayment());
        payment.pay();
    }
}
