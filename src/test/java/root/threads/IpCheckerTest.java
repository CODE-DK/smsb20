package root.threads;

import org.junit.Test;
import root.entity.DeviceStatus;

import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class IpCheckerTest {
    private static final String IP = "127.1.1.1";
    private static final String NOT_VALID_IP = "not valid IP";

    @Test
    public void callPositive() throws Exception {
        IpChecker spy = new IpChecker(IP);
        DeviceStatus status = spy.call();
        assertEquals(IP, status.getIp());
        assertTrue(status.getStatus());
        assertNotNull(status.getUpdated_at());
    }

    @Test (expected = UnknownHostException.class)
    public void callNegative() throws Exception {
        IpChecker spy = new IpChecker(NOT_VALID_IP);
        spy.call();
    }
}