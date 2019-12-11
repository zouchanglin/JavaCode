package xpu.demo;

public class SingletonFactory {
    private volatile static SingletonFactory myInstance;

    private static SingletonFactory getMyInstance(){
        if(myInstance == null){
            synchronized (SingletonFactory.class){
                if(myInstance == null){
                    myInstance = new SingletonFactory();
                }
            }
        }
        return myInstance;
    }
}
