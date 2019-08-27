package root.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import root.entity.DeviceStatus;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class ThreadChecker implements Callable {
    private final Logger logger = LoggerFactory.getLogger(ThreadChecker.class);
    private String ip;
    @Value("${check_connection_timeout}")
    private int timeout;

    public ThreadChecker(String ip) {
        this.ip = ip;
    }

    @Override
    public Object call() throws Exception {
        InetAddress address = getIp(this.ip);
        while (!address.isReachable(timeout)) {
            Thread.sleep(10);
        }
        return new DeviceStatus(this.ip, true, LocalDateTime.now());
    }

    private InetAddress getIp(String ip) throws UnknownHostException {
        try {
            return InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            logger.error("unknown host by given ip={}", ip, e);
            throw e;
        }
    }
}
