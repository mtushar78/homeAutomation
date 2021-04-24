package com.example.homeautomation.controllers;
import com.example.homeautomation.models.Devices;
import com.example.homeautomation.models.User;
import com.example.homeautomation.pojo.DevicePOJO;
import com.example.homeautomation.pojo.JsonResponse;
import com.example.homeautomation.repositories.DeviceRepo;
import com.example.homeautomation.repositories.UserRepo;
import com.example.homeautomation.services.DeviceRelayService;
import com.example.homeautomation.services.GeneralService;
import com.google.gson.JsonObject;
import com.google.zxing.WriterException;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    DeviceRepo deviceRepo;
    @Autowired
    GeneralService generalService;
    @Autowired
    DeviceRelayService deviceRelayService;



    @GetMapping("/")
    public String hello(){
        return "Hello people!";
    }
    @PostMapping("/userReg")
    public String addUsers(@RequestBody User user){
        int is_exists = userRepo.findBySso_id(user.getSso_id());
        if(is_exists <1){
            userRepo.save(user);
            return "Successfully inserted!";
        }
        else{
            return "User Already exists";
        }
    }

    @PostMapping("/saveDevice")
    public ResponseEntity<String> saveDevices(@RequestBody Devices device){
        String return_val;
        ResponseEntity<String> return_entity = null;
        return_entity = deviceRelayService.saveDevice_relay(device);

        return return_entity;
    }
    @GetMapping("/getAllDevices")
    public List<Devices> getAllDevices(){
        return deviceRepo.findAll();
    }
    /*@PostMapping("/setRelay")
    public String setRelayForDevices(@RequestBody Relay relay){

    }*/
    @PostMapping("/qrcode")
    public String generateQR(@RequestBody String mac) throws IOException, WriterException {
        String x = generalService.generateQrWithEncryption(mac);
        return x;
    }

}
