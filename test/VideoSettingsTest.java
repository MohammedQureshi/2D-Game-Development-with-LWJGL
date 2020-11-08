import com.apollo.timewreak.networking.ServerConfig;
import com.apollo.timewreak.settings.VideoSettings;
import org.junit.Test;

import java.util.EnumSet;

import static org.junit.Assert.assertEquals;

public class VideoSettingsTest {
    @Test
    public void testVideoSettingsConstructor() {
        VideoSettings vs = new VideoSettings();
        vs.setPropertyIfFound("WIDTH", 720);
        vs.save();

    }
}
