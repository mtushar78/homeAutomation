package com.example.homeautomation.services;
import com.example.homeautomation.models.Devices;
import com.example.homeautomation.models.Relay;
import com.example.homeautomation.models.RelayComposite;
import com.example.homeautomation.pojo.DevicePOJO;
import com.example.homeautomation.pojo.JsonResponse;
import com.example.homeautomation.repositories.DeviceRepo;
import com.example.homeautomation.repositories.RelayRepo;
import com.example.homeautomation.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class DeviceRelayService {
    @Autowired
    RelayRepo relayRepo;
    @Autowired
    DeviceRepo deviceRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<JsonResponse> pythonApiCall(String mac){
        DevicePOJO devicePOJO = new DevicePOJO(mac);
//        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", "password"));
        System.out.println(devicePOJO.toString());

        HttpHeaders header = new HttpHeaders();
        header.setAccept(Arrays.asList(MediaType.ALL));
        header.setBasicAuth("automate", "123456789");
        ResponseEntity<JsonResponse> obj =null;
        HttpEntity<DevicePOJO> entity = new HttpEntity<>(devicePOJO,header);
        try{
            obj = restTemplate.postForEntity("http://localhost:4000/",entity, JsonResponse.class);
        }catch (Exception e){

            System.out.println(e.getClass());
        }

        return obj;
    }

    public ResponseEntity<String> saveDevice_relay(Devices device){

        ResponseEntity<JsonResponse> res = pythonApiCall(device.getDeviceComposite().getMac_address());
        ResponseEntity<String> return_val = null;
        System.out.println(res.getBody().getStatus());
        System.out.println(res.getBody().getMessage());
        System.out.println(res.getStatusCode());
        System.out.println(res.getStatusCodeValue());

        if(res.getBody().getStatus().trim().equals("Success")){
            System.out.println("Entered to the If block [got Success] ");
            int user_id = userRepo.getUserId(device.getEmail());
            device.getDeviceComposite().setUser_id(user_id);
            if(deviceRepo.existsById(device.getDeviceComposite().getMac_address(), user_id) == 0){
                device.setStatus(1);
                device.setParent(null);
                device.setId(deviceRepo.maxId());
                deviceRepo.save(device);
                this.addRelay(device.getDeviceComposite().getMac_address());

                return_val = new ResponseEntity<String>("Successfully Inserted", new HttpHeaders(), HttpStatus.OK);
            }else {
                return_val = new ResponseEntity<String>("Device is already registered with the same user.", new HttpHeaders(), HttpStatus.FOUND);
            }
            return return_val;
        }
        else {
            System.out.println("Entered to the else block [Did not get Success] ");
            return_val = new ResponseEntity<String>(res.getBody().getMessage(), new HttpHeaders(), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            return return_val;
        }

    }

    public String addRelay(String mac_address){
        Relay relay = new Relay();
        RelayComposite relayComposite = new RelayComposite();
        relayComposite.setDevice_mac(mac_address);
        relay.setStatus(0);
        relay.setMod_time(null);
        for(int i=1; i<=3;i++){
            relayComposite.setRelay_name("R"+i);
            relay.setRelayComposite(relayComposite);
            relayRepo.save(relay);
        }
        return "success";
    }

}
