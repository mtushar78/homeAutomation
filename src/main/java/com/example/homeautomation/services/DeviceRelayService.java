package com.example.homeautomation.services;
import com.example.homeautomation.models.Devices;
import com.example.homeautomation.models.Relay;
import com.example.homeautomation.models.RelayComposite;
import com.example.homeautomation.models.User;
import com.example.homeautomation.pojo.DevicePOJO;
import com.example.homeautomation.pojo.JsonResponse;
import com.example.homeautomation.repositories.DeviceRepo;
import com.example.homeautomation.repositories.RelayRepo;
import com.example.homeautomation.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<JsonResponse> saveDevice_relay(Devices device){

        ResponseEntity<JsonResponse> res = pythonApiCall(device.getDeviceComposite().getMac_address());
        ResponseEntity<JsonResponse> return_val = null;
        JsonResponse responseBody = new JsonResponse();

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
                responseBody.setStatus(String.valueOf(HttpStatus.OK));
                responseBody.setMessage("Successfully Inserted");
                return_val = new ResponseEntity<JsonResponse>(responseBody, new HttpHeaders(), HttpStatus.OK);
            }else {
                responseBody.setStatus(String.valueOf(HttpStatus.FOUND));
                responseBody.setMessage("Device is already registered with the same user.");
                return_val = new ResponseEntity<JsonResponse>(responseBody, new HttpHeaders(), HttpStatus.FOUND);
            }
            return return_val;
        }
        else {
            responseBody.setStatus(String.valueOf(HttpStatus.NON_AUTHORITATIVE_INFORMATION));
            responseBody.setMessage(res.getBody().getMessage());
            return_val = new ResponseEntity<JsonResponse>(responseBody, new HttpHeaders(), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
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

    public User getUserInfo(String email){
        int userId = userRepo.getUserId(email);
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            List<Devices> devices = deviceRepo.getDevices_forParent(userId);
            List<Object[]> queryRecords = deviceRepo.sharedDevices(userId);


            user.get().setDevices(devices);
            for (int i=0; i<devices.size();i++) {
//                    System.out.println(devices1.toString());
                List<String> sharedTo = new ArrayList<>();
                String mac_address= devices.get(i).getDeviceComposite().getMac_address();
                devices.get(i).setRelays(relayRepo.getDevices_relays(mac_address));
                for(Object[] objects : queryRecords){
                    String mac = (String) objects[0];
                    String email_add = (String) objects[1];
                    System.out.println("dev mac: "+ devices.get(i).getDeviceComposite().getMac_address()+ "shared: "+mac);
//                    System.out.println(email_add);
                    if(devices.get(i).getDeviceComposite().getMac_address().equals(mac) ){
                        sharedTo.add(email_add);
                    }
                }
                devices.get(i).setSharedTo(sharedTo);
            }
            return user.get();

        }
        return user.get();
    }

}
