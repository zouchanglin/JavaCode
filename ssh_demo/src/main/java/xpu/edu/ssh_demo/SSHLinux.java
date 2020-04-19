package xpu.edu.ssh_demo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.io.IOUtils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHLinux {
    public static void main(String[] args) throws IOException, JSchException {
        String host = "192.168.2.2";
        int port = 22;
        String user = "root";
        String password = "123456";
        String command = "sh deploy.sh 192.168.2.2 v1.38 6060";
        exeCommand(host, port, user, password, command);

    }

    public static void exeCommand(String host, int port, String user, String password, String command) throws JSchException, IOException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect();
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        channelExec.setErrStream(byteArrayOutputStream);
        channelExec.connect();
        byte[] bytes = new byte[1024];
        while (in.read(bytes) != -1){
            String s = new String(bytes);
            if(s.contains("http://:8080")) return;
        }
        channelExec.disconnect();
        session.disconnect();
    }
}