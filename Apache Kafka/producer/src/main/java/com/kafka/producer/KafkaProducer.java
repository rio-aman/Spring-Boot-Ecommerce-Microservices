package com.kafka.producer;
//
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class KafkaProducer {
//
//    // kafka template here use for kafka-topics as seen in CLI
////    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final KafkaTemplate<String, RiderLocation> kafkaTemplate; // for rider location
//
//    public KafkaProducer(KafkaTemplate<String, RiderLocation> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    @PostMapping("/send")
//    public String sendMessage(@RequestParam String message){
//
////        kafkaTemplate.send("my-topic-new",message);
////        return "Message sent : " + message;
//        // before using the rider location
//
//        // now going to send the object instead of the simple string message
//        RiderLocation location = new RiderLocation("rider123",28.61,77.23);
//
////        kafkaTemplate.send("my-topic-new", location); // this shows the error because the actual can't be sent directly serialization error
//
//        kafkaTemplate.send("my-topic-new", location);
//        return "Message sent : " + location.getRiderId();
//    }
//}
