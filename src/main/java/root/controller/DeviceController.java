package root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import root.entity.DeviceStatus;
import root.service.CheckConnectionService;

import java.net.UnknownHostException;
import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private CheckConnectionService service;

    @RequestMapping("/stat")
    public List<DeviceStatus> getAllDevices(@RequestBody List<String> ips) throws UnknownHostException {
        return service.checkDevice(ips);
    }
}
