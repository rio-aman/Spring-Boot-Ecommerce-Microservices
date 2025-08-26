package com.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "my-topic", groupId = "my-new-group")
    public void listen1(String message){
        System.out.println("Received Message 1 : " + message);
    }

//    @KafkaListener(topics = "my-topic", groupId = "my-new-group-1") // this is for multiple consumer working with one request sending
    @KafkaListener(topics = "my-topic", groupId = "my-new-group")  // if the group id same then the first consumer will listen and rest not
    public void listen2(String message){
        System.out.println("Received Message 2 : " + message);
    }

    @KafkaListener(topics = "my-topic-new", groupId = "my-new-group-rider")
    public void listenRiderLocation(RiderLocation location){
        System.out.println("Received Location : " + location.getRiderId() +
                " : " + location.getLatitude() + " : " + location.getLongitude() );
    }

}
