package xpu.edu.ssh_demo.other;


import lombok.Data;

@Data
public class DestHost {
    private String host = "";
    private String username = "";
    private String password = "";
    private int port = 22;
    private int timeout = 60 * 60 * 1000;

    public DestHost(String host, String username, String password){
        this(host, username, password, 22, 60*60*1000);
    }

    public DestHost(String host, String username, String password, int timeout){
        this(host, username, password, 22, timeout);
    }

    public DestHost(String host, String username, String password, int port,
                    int timeout) {
        super();
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
        this.timeout = timeout;
    }
}
