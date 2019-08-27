package root.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class DeviceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String ip;
    private Boolean status;
    private LocalDateTime updated_at;
    @Version
    private Long version;

    public DeviceStatus() {
    }

    public DeviceStatus(String ip, Boolean status, LocalDateTime updated_at) {
        this.ip = ip;
        this.status = status;
        this.updated_at = updated_at;
    }
}
