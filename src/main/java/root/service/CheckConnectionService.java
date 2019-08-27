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

    @Value("${check_connection_timeout}")
    private int timeout;

    @Setter
    private boolean stop;

    @Autowired
    private DeviceStatusRepo repo;

    private List<DeviceStatus> checkDevice(List<String> ips) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        List<Future<DeviceStatus>> futures = new ArrayList<>();
        List<DeviceStatus> devices = new ArrayList<>();
        for (String ip : ips) {
            futures.add(pool.submit(new IpChecker(ip)));
        }
        for (Future<DeviceStatus> future : futures) {
            devices.add(future.get());
        }
        return repo.saveAll(devices);
    }

    public void updateStatus(List<String> ips) throws ExecutionException, InterruptedException {
        while (!stop) {
            checkDevice(ips);
            Thread.sleep(timeout);
        }
    }
}
