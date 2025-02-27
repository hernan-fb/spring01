package com.example.obspringbootjwt.service;

import com.example.obspringbootjwt.domain.Car;
import com.example.obspringbootjwt.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {
    private static final Integer MIN_DOORS = 3;
    private final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @Override
    public List<Car> findAll() {
        log.info("Executing findAll cars");
        return this.carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(Long id) {
        log.info("Executing findById cars");
        return this.carRepository.findById(id);
    }

    @Override
    public List<Car> findByDoors(Integer doors) {
        log.info("Searching cars by doors");
        if (doors < MIN_DOORS) {
            log.warn("Trying to search less than allowed doors");
            return new ArrayList<>();
        }
        return this.carRepository.findByDoors(doors);
    }
    @Override
    public Long count() {
        log.info("Get total number of cars");
        return this.carRepository.count();
    }

    @Override
    public Car save(Car car) {
        log.info("Creating / updating a car");
        // pre
        if (!this.validateCar(car)) {
            return null;
        }
        // actions
        // find the template from db
        Car carDB = this.carRepository.save(car);

        // post
        // enviar notificacion
        // this.notificationService(NotificationType.CREATION, car);

        return carDB;
    }
    private boolean validateCar(Car car) {
        // car null validation
        if (car == null) {
            log.warn("Trying to create null car");
            return false;
        }
        // num doors validation
        if (car.getDoors() == null || car.getDoors() < MIN_DOORS) {
            log.warn("Trying to create a car with less doors than allowed");
            return false;
        }
        // color validation
        // ...
        return true;
    }
    @Override
    public void deleteById(Long id) {
        log.info("Deleting car by id");
        if (id == null || id <= 0) {
            log.warn("Incorrect id when trying to delete car by Id");
            return;
        }
        try {
            this.carRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error trying to delete car by Id {}", id, e);
        }
    }

    @Override
    public void deleteAll(List<Car> cars) {
        log.info("Deleting all cars by list of cars");
        if (CollectionUtils.isEmpty(cars)) {
            log.warn("Trying to delete an empty or null car list");
            return;
        }
        this.carRepository.deleteAll(cars);
    }

    @Override
    public void deleteAllById(List<Long> ids) {
        log.info("Deleting all cars by list of ids");
        if (CollectionUtils.isEmpty(ids)) {
            log.warn("Trying to delete an empty or null car list");
            return;
        }
        this.carRepository.deleteAllById(ids);
    }

    @Override
    public List<Car> findByManufacturerAndModel(String manufacturer, String model) {
        if (!StringUtils.hasLength(manufacturer) || !StringUtils.hasLength(model)) {
            log.warn("Trying to find with void manufacturer or model");
            return new ArrayList<>();
        }
        return this.carRepository.findByManufacturerAndModel(manufacturer,model);
    }

    @Override
    public List<Car> findByDoorsGreaterThanEqual(Integer doors) {
        if (doors == null || doors <= MIN_DOORS) {
            log.warn("Trying to find by doors with doors null or less than min doors");
            return new ArrayList<>();
        }
        return this.carRepository.findByDoorsGreaterThanEqual(doors);
    }

    @Override
    public List<Car> findByModelContaining(String model) {
        log.info("find by model containing");
        return this.carRepository.findByModelContaining(model)
    }

    @Override
    public List<Car> findByYearIn(List<Integer> years) {
        log.info("find by year in list of years");
        return this.carRepository.findByYearIn(years);
    }

    @Override
    public List<Car> findByYearBetween(Integer startYear, Integer endYear) {
        log.info("find by year between min max");
        return this.carRepository.findByYearBetween(startYear,endYear);
    }

    @Override
    public List<Car> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate) {
        log.info("find by release date between two dates");
        return this.carRepository.findByReleaseDateBetween(startDate,endDate);
    }

    @Override
    public List<Car> findByAvailableTrue() {
        log.info("find by available true");
        return this.carRepository.findByAvailableTrue();
    }

    @Override
    public Boolean deleteAllByAvailableFalse() {
        log.info("delete all by available false");
        return this.carRepository.deleteAllByAvailableFalse();
    }
}
