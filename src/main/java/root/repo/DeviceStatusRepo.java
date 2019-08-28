package root.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import root.entity.DeviceStatus;

import java.util.UUID;

@Repository
@Cacheable
public interface DeviceStatusRepo extends JpaRepository<DeviceStatus, UUID> {

}
