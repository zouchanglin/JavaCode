package xpu.edu.tim;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class NormalObjectWeakReference  extends WeakReference<NormalObject> {
    public String name;

    public NormalObjectWeakReference(NormalObject normalObject, ReferenceQueue<NormalObject> q) {
        super(normalObject, q);
        this.name = normalObject.name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalizing NormalObjectWeakReference "+ name);
    }
}
