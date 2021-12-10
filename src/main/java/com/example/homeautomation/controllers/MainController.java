package com.example.homeautomation.controllers;
import com.example.homeautomation.models.Devices;
import com.example.homeautomation.models.User;
import com.example.homeautomation.pojo.JsonResponse;
import com.example.homeautomation.pojo.RelayOnOffPojo;
import com.example.homeautomation.pojo.RemoveShare;
import com.example.homeautomation.repositories.DeviceRepo;
import com.example.homeautomation.repositories.RelayRepo;
import com.example.homeautomation.repositories.UserRepo;
import com.example.homeautomation.services.DeviceRelayService;
import com.example.homeautomation.services.GeneralService;
import com.google.zxing.WriterException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    RelayRepo relayRepo;



    @GetMapping("/")
    public String hello(){
        return "Hello  ghjyrtrtr All people !";
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

    @PostMapping("/removeShare")
    public String removeShare(@RequestBody RemoveShare removeShare){
        deviceRepo.removeShareFunc(removeShare.getMac_address(), removeShare.getEmail());
        return "success";
    }
    @PostMapping("/relay-on-off")
    public void relayOnOff(@RequestBody RelayOnOffPojo relay){
       System.out.println(relay.toString());
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("time: "+formatter.format(date));
        relayRepo.setRelayOnOff(relay.getMac(),relay.getRelay_name(),relay.getStatus(), formatter.format(date));
    }
    @PostMapping("/test")
    public void restTest(@RequestBody JSONObject object){
        System.out.println(object.toString());
    }

}
