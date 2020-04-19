package xpu.tim;

interface MyInterface {
    //JDK7
    void method1();

    //JDK8: 静态方法
    static void method2(){
        System.out.println("method2");
    }

    //JDK8：默认方法
    default void method3(){
        System.out.println("method3");
        method4();
    }

    //JDK9：私有方法
    private void method4(){
        System.out.println("method");
    }
}

class MyInterfaceImpl implements MyInterface{

    @Override
    public void method1() {

    }
}

public class MyInterfaceTest{
    public static void main(String[] args) {
        MyInterface myInterface = new MyInterfaceImpl();
        myInterface.method3();
        //myInterface.method4(); Error
    }
}