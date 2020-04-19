package tim.edu.spring_study.proxy;

public class RealPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("作为用户，我只关心支付");
    }
}
