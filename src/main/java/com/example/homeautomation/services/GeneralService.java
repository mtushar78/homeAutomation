package com.example.homeautomation.services;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeneralService {
    @Autowired
    AESEncryptionDecryption aesEncryptionDecryption;
    @Autowired
    GenerateQRCode generateQRCode;

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

}
