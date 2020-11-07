import com.apollo.timewreak.networking.ServerConfig;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerConfigTest {
    @Test
    public void testServerConfig() {
        ServerConfig sc = new ServerConfig();
        sc.setProperty("port", "5542");

        assertEquals(sc.getProperty("port"), "5542");
    }
}
