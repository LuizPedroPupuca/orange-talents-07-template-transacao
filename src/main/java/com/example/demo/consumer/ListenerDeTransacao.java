package com.example.demo.consumer;

import com.example.demo.dto.TransacaoRequest;
import com.example.demo.model.Transacao;
import com.example.demo.repository.TransacaoRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ListenerDeTransacao {

    @Autowired
    private TransacaoRepository transacaoRepository;


    @KafkaListener(topics = "${spring.kafka.topic.transactions}",
            containerFactory = "kafkaListenerContainerFactory")
    public void ouvir(TransacaoRequest transacaoRequest) {
        Transacao transacao = transacaoRequest.toModel();
        transacaoRepository.save(transacao);
    }
}
