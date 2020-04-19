package xpu.edu.ssh_demo.other;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws JSchException, IOException, InterruptedException {
        String curl = "curl https://ahojcn.gitee.io/deploy.sh > deploy.sh";
        String run = "sh deploy.sh 192.168.2.2 v1.38 6060";
        DestHost destHost = new DestHost("192.168.2.2", "root", "123456");
        //String curlRet = SSHUtils.execCommandByJSch(jSchSession, curl, "UTF-8");
        SSHUtils.execCommandByShellDeploy(SSHUtils.getJSchSession(destHost));
        TimeUnit.SECONDS.sleep(5);
        SSHUtils.execCommandByShell(SSHUtils.getJSchSession(destHost));
        //String runRet = SSHUtils.execCommandByJSch(jSchSession, run, "UTF-8");
        //System.out.println(curlRet);
        //System.out.println(runRet);
    }
}
