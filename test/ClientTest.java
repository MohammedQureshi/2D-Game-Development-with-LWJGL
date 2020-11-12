import com.apollo.timewreak.networking.Client;
import com.apollo.timewreak.networking.Server;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {
//    private Client c;

    static Client c;
//    // TODO: correctly implement the client and Server, currently using main to test the connections.
    public static void main(String[] args) {
        c = new Client();
        c.connect("127.0.0.1", 5542);
    }
//    @Before
//    public void initialize() {
//        c = new Client();
//    }
//
//    @Test
//    public void testConnect() {
//        c.connect("127.0.0.1", Server.DefaultConfig.HOST_PORT);
//
//    }
}
