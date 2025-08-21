package com.bryano.f1_stats.driver_archive;

import com.bryano.f1_stats.pojo.SelectOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DriverArchiveService {
    private final DriverArchiveRepository driverArchiveRepository;

    @Autowired
    public DriverArchiveService(DriverArchiveRepository driverArchiveRepository) {
        this.driverArchiveRepository = driverArchiveRepository;
    }

    public List<DriverArchive> getDrivers() {
        return driverArchiveRepository.findAll();
    }

    public List<DriverArchive> getDriversByYear(Integer year) {
        return driverArchiveRepository.findAll().stream()
                .filter(driverArchive -> Objects.equals(driverArchive.getYear(), year))
                .collect(Collectors.toList());
    }

    public List<DriverArchive> getDriversByDriver(String driver) {
        return driverArchiveRepository.findAll().stream().filter(driverArchive -> driverArchive
                .getDriver().toLowerCase().contains(driver.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<DriverArchive> getDriversByNationality(String nationality) {
        return driverArchiveRepository
                .findAll().stream().filter(driverArchive -> driverArchive.getNationality()
                        .toLowerCase().contains(nationality.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<DriverArchive> getDriversByCar(String car) {
        return driverArchiveRepository.findAll().stream()
                .filter(driverArchive -> driverArchive.getCar() != null
                        && driverArchive.getCar().toLowerCase().contains(car.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<SelectOption> getDriverNames() {
        return driverArchiveRepository.findAll().stream().map(DriverArchive::getDriver)
                .collect(Collectors.toSet()).stream().sorted(String::compareTo)
                .map(name -> new SelectOption(name, name)).collect(Collectors.toList());
    }
}
