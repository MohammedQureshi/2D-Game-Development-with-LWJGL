import com.apollo.timewreak.networking.Client;
import org.junit.Before;
import org.junit.Test;

public class ClientTest {
    static Client c;

    // TODO: correctly implement the client and Server, currently using main to test the connections.
    public static void main(String[] args) {
        c = new Client();
        c.connect("127.0.0.1", 5542);
    }
}
