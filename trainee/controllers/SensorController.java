package com.task.trainee.controllers;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.task.trainee.models.Sensor;
import com.task.trainee.repo.SensorRepository;
import com.task.trainee.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;
    private SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }



    @PutMapping("/readfile")
    public Iterable<Sensor>  readfile() {
        File file = new File("D:\\TraineeTask\\trainee\\src\\main\\resources\\sensors\\door_sensor_0a4b2386.json");
        ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                try {
                    List<Sensor> sensors = objectMapper.readValue(file, new TypeReference<List<Sensor>>() {
                    });
                    sensorRepository.saveAll(sensors);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        return sensorRepository.findAll();
    }

}
