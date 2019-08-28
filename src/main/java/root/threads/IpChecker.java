package root.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import root.entity.DeviceStatus;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class IpChecker implements Callable<DeviceStatus> {
    private final Logger logger = LoggerFactory.getLogger(IpChecker.class);
    private String ip;

    public IpChecker(String ip) {
        this.ip = ip;
    }

    @Override
    public DeviceStatus call() throws Exception {
        InetAddress ip = getIp(this.ip);
        return new DeviceStatus(this.ip, ip.isReachable(1000), LocalDateTime.now());
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
