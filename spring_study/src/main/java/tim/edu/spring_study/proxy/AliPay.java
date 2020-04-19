package tim.edu.spring_study.proxy;

public class AliPay implements Payment{
    private Payment payment;

    public AliPay(Payment payment) {
        this.payment = payment;
    }

    private void beforePay(){
        System.out.println("安全检查");
    }

    @Override
    public void pay() {
        beforePay();
        payment.pay();
        afterPay();
    }

    private void afterPay(){
        System.out.println("消费记录");
    }
}
