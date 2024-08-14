import org.apache.logging.log4j.core.Logger;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Timeout;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Disabled
    @Test
    @Timeout(value = 22)
    public void failsIfLongerThanSetTime() throws Exception {
        Main.main(null);
    }
}
