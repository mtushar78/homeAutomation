package com.example.homeautomation;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class HomeautomationApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HomeautomationApplication.class, args);

        /*String subTopic = "outTopic/switching/#";
        String pubTopic = "testtopic/1";
        String content = "Hello World";
        int qos = 2;
        String broker = "tcp://103.95.209.103:1883";
        String clientId = "client1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);

            // MQTT connection option
            MqttConnectOptions connOpts = new MqttConnectOptions();
*//*            connOpts.setUserName("emqx_test");
            connOpts.setPassword("emqx_test_password".toCharArray());*//*
            // retain session
            connOpts.setCleanSession(true);

            // set callback
            client.setCallback(new OnMessageCallback());

            // establish a connection
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);

            System.out.println("Connected");

            // Subscribe
            client.subscribe(subTopic);

            // Required parameters for message publishing
            *//*MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            client.publish(pubTopic, message);
            System.out.println("Message published");

            client.disconnect();
            System.out.println("Disconnected");*//*
            *//*client.close();
            System.exit(0);*//*
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }*/
    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {

        return builder.build();
    }





}
