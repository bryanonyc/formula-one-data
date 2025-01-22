package com.bryano.f1_stats.driver_archive;

import com.bryano.f1_stats.pojo.SelectOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/driver/archive")
public class DriverArchiveController {
    private final DriverArchiveService driverArchiveService;

    @Autowired
    public DriverArchiveController(DriverArchiveService driverArchiveService) {
        this.driverArchiveService = driverArchiveService;
    }

    @GetMapping
    public List<DriverArchive> getDrivers(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String driver,
            @RequestParam(required = false) String nationality,
            @RequestParam(required = false) String car
    ) {
        if (year != null) {
            return driverArchiveService.getDriversByYear(year);
        } else if (driver != null) {
            return driverArchiveService.getDriversByDriver(driver);
        } else if (nationality != null) {
            return driverArchiveService.getDriversByNationality(nationality);
        } else if (car != null) {
            return driverArchiveService.getDriversByCar(car);
        } else {
            return driverArchiveService.getDrivers();
        }
    }

    @GetMapping(path = "/names")
    public List<SelectOption> getDriverNames() {
        return driverArchiveService.getDriverNames();
    }
}
