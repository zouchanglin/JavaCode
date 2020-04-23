package xpu.tim;

import javax.crypto.KeyAgreement;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.spec.NamedParameterSpec;
import java.security.spec.XECPublicKeySpec;
import java.util.ArrayList;
import java.util.List;

class Garbage {
    int n = (int)(Math.random() * 100);
    @Override
    public void finalize() {
        System.out.println(this + " : " + n + " is dying");
    }
}
public class EpsilonTest {
    public static void main(String[] args) throws Exception{
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("XDH");
        NamedParameterSpec paramSpec = new NamedParameterSpec("X25519");
        kpg.initialize(paramSpec);
        KeyPair kp = kpg.generateKeyPair();

        KeyFactory kf = KeyFactory.getInstance("XDH");
        BigInteger u = new BigInteger("123456");
        XECPublicKeySpec pubSpec = new XECPublicKeySpec(paramSpec, u);
        PublicKey pubKey = kf.generatePublic(pubSpec);

        KeyAgreement ka = KeyAgreement.getInstance("XDH");
        ka.init(kp.getPrivate());
        ka.doPhase(pubKey, true);
        byte[] secret = ka.generateSecret();
    }
    public static void main2(String[] args) {
        boolean flag = true;
        List<Garbage> list = new ArrayList<>();
        long count = 0;
        while (flag) {
            list.add(new Garbage());
            if (list.size() == 1000000 && count == 0) {
                list.clear();
                count++;
            }
        }
        System.out.println("程序结束");
    }
}