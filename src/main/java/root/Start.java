package root;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import root.service.CheckConnectionService;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@ComponentScan
@EnableAutoConfiguration
@EnableCaching
public class Start {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Start.class, args);
        CheckConnectionService service = (CheckConnectionService) context.getBean("checkConnectionService");
        new Thread(() -> {
            try {
                service.updateStatus(Collections.singletonList("127.1.1.1"));
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        service.setStop(true);
    }
}
