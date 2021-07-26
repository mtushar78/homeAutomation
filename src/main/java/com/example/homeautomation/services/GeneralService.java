package com.example.homeautomation.services;

import com.example.homeautomation.pojo.RelayOnOffPojo;
import com.example.homeautomation.repositories.RelayRepo;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GeneralService {
    @Autowired
    AESEncryptionDecryption aesEncryptionDecryption;
    @Autowired
    GenerateQRCode generateQRCode;
    @Autowired
    RelayRepo relayRepo;


    public String generateQrWithEncryption(String mac) throws IOException, WriterException {
        String encrypted = aesEncryptionDecryption.encrypt(mac);
        System.out.println("Encrypted value: "+encrypted);
        String path = "D:\\projects\\"+mac+".png";
//invoking the user-defined method that creates the QR code
        generateQRCode.func_generateQRcode(encrypted,path,200, 200);//increase or decrease height and width accodingly
//prints if the QR code is generated
        System.out.println("QR Code created successfully.");
        String decrypted = aesEncryptionDecryption.decrypt(encrypted);
        System.out.println("Decrypted value: "+decrypted);
        return decrypted;
    }
    public void relayOnOffService(RelayOnOffPojo relay){
        System.out.println(relay.toString());
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println("time: "+formatter.format(date));
        try{
            relayRepo.setRelayOnOff(relay.getMac(),relay.getRelay_name(),relay.getStatus(), formatter.format(date));
        }catch (Exception e){
            System.out.println(e.getMessage()+"   "+e.getCause());
        }
    }
    public String test(String mac){
        System.out.println("Mac!!"+mac);
        return "test";
    }

}
