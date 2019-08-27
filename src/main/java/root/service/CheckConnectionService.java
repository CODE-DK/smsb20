package root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import root.entity.DeviceStatus;
import root.repo.DeviceStatusRepo;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CheckConnectionService {

    @Autowired
    private DeviceStatusRepo repo;

    public List<DeviceStatus> checkDevice(List<String> ips) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (String ip : ips) {
            // TODO: 27.08.2019 добавить обработку в потоке
        }
        return repo.findAll();
    }

    @Transactional
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public void save(DeviceStatus status){
        repo.save(status);
    }
}
