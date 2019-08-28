package root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import root.service.CheckConnectionService;

@ComponentScan
@EnableAutoConfiguration
public class Start {
    private static final Logger logger = LoggerFactory.getLogger(Start.class);
    @Autowired
    private static CheckConnectionService service;

    public static void main(String[] args) {
//        try {
//            service.updateStatus(new ArrayList<>());
//            Thread.sleep(10000);
//            service.setStop(true);
//        } catch (ExecutionException | InterruptedException e) {
//            logger.error("", e);
//        }
    }
}
