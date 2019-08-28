package root.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import root.entity.DeviceStatus;
import root.repo.DeviceStatusRepo;
import root.threads.IpChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class CheckConnectionService {
    private static final int maxPoolSize = 100;
    @Value("${check_connection_timeout}")
    private int timeout;
    @Setter
    private boolean stop;
    private final DeviceStatusRepo repo;

    public CheckConnectionService(DeviceStatusRepo repo) {
        this.repo = repo;
    }

    private void checkDevice(List<String> ips) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(maxPoolSize);
        List<DeviceStatus> devices = getDeviceStatuses(getFutures(ips, pool));
        repo.saveAll(devices);
    }

    private List<DeviceStatus> getDeviceStatuses(List<Future<DeviceStatus>> futures) throws InterruptedException, ExecutionException {
        List<DeviceStatus> devices = new ArrayList<>();
        for (Future<DeviceStatus> future : futures) {
            devices.add(future.get());
        }
        return devices;
    }

    private List<Future<DeviceStatus>> getFutures(List<String> ips, ExecutorService pool) {
        List<Future<DeviceStatus>> futures = new ArrayList<>();
        for (String ip : ips) {
            futures.add(pool.submit(new IpChecker(ip)));
        }
        return futures;
    }

    public void updateStatus(List<String> ips) throws ExecutionException, InterruptedException {
        while (!stop) {
            checkDevice(ips);
            Thread.sleep(timeout);
        }
    }
}
