package root.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import root.entity.DeviceStatus;

import java.util.UUID;

@Repository
public interface DeviceStatusRepo extends JpaRepository<DeviceStatus, UUID> {

}
