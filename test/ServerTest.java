package server;

import com.apollo.timewreak.networking.Server;
import com.apollo.timewreak.networking.ServerConfig;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ServerTest {
    @Before
    public void initalize() {

    }
    public static void main(String[] args){
        ServerConfig sc = new ServerConfig();
        sc.setProperty("port", "5542");
        Server s = new Server(sc);
        s.start();
        s.stop();
    }
}
