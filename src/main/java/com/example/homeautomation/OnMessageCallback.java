package com.example.homeautomation;

import com.example.homeautomation.pojo.RelayOnOffPojo;
import com.example.homeautomation.services.GeneralService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
public class OnMessageCallback implements MqttCallback {
    @Autowired
    RestTemplate template;


    public void connectionLost(Throwable cause) {
        // After the connection is lost, it usually reconnects here
        System.out.println("disconnect，you can reconnect");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // The messages obtained after subscribe will be executed here

        String[] parts = topic.split("outTopic/switching/");
        String part1 = parts[0]; // 004
        String part2 = parts[1]; // device mac
        String rcv_message = new String(message.getPayload());

        String[] relay_status = rcv_message.split("_");
        String relay = relay_status[0];
        String status = relay_status[1];
        int sts = 100;
        if (status.equals("on"))
            sts = 1;
        else
            sts = 0;

        try {
            System.out.println("mac:" + part2+ " relay:"+ relay+" status: "+ sts);
            RelayOnOffPojo relayOnOffPojo = new RelayOnOffPojo(part2, relay, sts);
            GeneralService gs = new GeneralService();
            gs.relayOnOffService(relayOnOffPojo);

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

    }


    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}
