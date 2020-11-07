import com.apollo.timewreak.networking.Client;
import com.apollo.timewreak.networking.Server;
import com.apollo.timewreak.networking.ServerConfig;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ServerTest {
    static ServerConfig sc;

    // TODO: correctly implement the client and Server, currently using main to test the connections.
    public static void main(String args[]) {
        sc = new ServerConfig();
        sc.setProperty("port", "5542");

        Server s = new Server(sc);
        s.start();
    }
}
