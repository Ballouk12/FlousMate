package ma.mhdsny.transactionservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.mhdsny.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class TransactionsPublishBridgeService {

    @Autowired
    private StreamBridge streamBridge;
    @Autowired
    private TransactionRepository transactionRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    public void publishTransactions() {
        try{
        String transctionsJson = objectMapper.writeValueAsString(transactionRepository.findAll());
        publish(transctionsJson);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    private void publish(String message) {
        boolean sent = streamBridge.send("output-out-0",message);
        if(sent) {
            System.out.println("Message successfully sent to Kafka topic: " + message);
        } else {
            System.err.println("Failed to send message to Kafka topic.");
        }
    }


}
