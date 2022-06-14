package com.example.demo.consumer;

import com.example.demo.dto.TransacaoRequest;
import com.example.demo.model.Cartao;
import com.example.demo.model.Estabelecimento;
import com.example.demo.model.Transacao;
import com.example.demo.repository.CartaoRepository;
import com.example.demo.repository.EstabelecimentoRepository;
import com.example.demo.repository.TransacaoRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@Component
public class ListenerDeTransacao {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;


    @KafkaListener(topics = "${spring.kafka.topic.transactions}",
            containerFactory = "kafkaListenerContainerFactory")
    public void ouvir(TransacaoRequest transacaoRequest) {
        Cartao cartao = cartaoRepository.findByIdCartao(transacaoRequest
                .getCartao().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cartão inexistente"));

        Estabelecimento estabelecimento = estabelecimentoRepository
                .findByNome((transacaoRequest.getEstabelecimento().getNome()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Estabelecimento inexistente"));


        Transacao transacao = transacaoRequest.toModel(estabelecimento, cartao);
        transacaoRepository.save(transacao);
    }
}
