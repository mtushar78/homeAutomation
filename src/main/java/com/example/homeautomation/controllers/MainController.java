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

import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/getUserInfo/{email}")
    public ResponseEntity<User> getUserInfo(@PathVariable String email){
        User user = deviceRelayService.getUserInfo(email);
        return new ResponseEntity<User>(user, new HttpHeaders(),HttpStatus.OK);
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
    public ResponseEntity<JsonResponse> saveDevices(@RequestBody Devices device){
        ResponseEntity<JsonResponse> return_entity = null;
        if(userRepo.existsByEmail(device.getEmail()) == 1){
            return_entity = deviceRelayService.saveDevice_relay(device);
        }else {
            JsonResponse returned_val = new JsonResponse("No user found with "+device.getEmail(), String.valueOf(HttpStatus.NON_AUTHORITATIVE_INFORMATION));
            System.out.println(returned_val.toString());
            return_entity = new ResponseEntity<>(returned_val,new HttpHeaders(), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }

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
