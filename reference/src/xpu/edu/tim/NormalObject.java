package xpu.edu.tim;

public class NormalObject {
    public String name;
    public NormalObject(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing obj " + name);
    }
}
