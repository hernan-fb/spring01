package com.example.obspringbootjwt.repository;

import com.example.obspringbootjwt.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findByDoors(Integer Doors);

    List<Car> findByManufacturerAndModel(String manufacturer, String model);

    List<Car> findByDoorsGreaterThanEqual(Integer Doors);

    List<Car> findByModelContaining(String model);

    List<Car> findByYearIn(List<Integer> years);

    List<Car> findByYearBetween(Integer startYear, Integer endYear);

    List<Car> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);

    List<Car> findByAvailableTrue();

    Boolean deleteAllByAvailableFalse();




}
