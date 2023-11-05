import org.coursesjava.model.ServerConfiguration;
import org.coursesjava.services.ConfigService;
import org.junit.Assert;
import org.junit.Test;

public class ConfigServiceTest {
    private final ConfigService config = new ConfigService();
    private final ServerConfiguration expected = new ServerConfiguration();
    @Test
    public void create() {
        config.create();
        ServerConfiguration actual = config.read();

        Assert.assertEquals(expected.getPort(), actual.getPort());
        Assert.assertEquals(expected.getRestriction(), actual.getRestriction());
    }

    @Test
    public void read() {
        ConfigService config = new ConfigService();

        ServerConfiguration actual = config.read();

        Assert.assertEquals(expected.getPort(), actual.getPort());
        Assert.assertEquals(expected.getRestriction(), actual.getRestriction());
    }
}
