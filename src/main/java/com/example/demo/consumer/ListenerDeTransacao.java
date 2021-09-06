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
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

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
        Optional<Cartao> cartaoOptional = cartaoRepository
                .findByIdCartao(transacaoRequest.getCartao().getId());
        Cartao cartao = null;
        if (cartaoOptional.isEmpty()){
            cartao = transacaoRequest.getCartao().toModel();
            cartaoRepository.save(cartao);
        }else {
            cartao = cartaoOptional.get();
        }

        Optional<Estabelecimento> estabelecimentoOptional = estabelecimentoRepository
                .findByNome(transacaoRequest.getEstabelecimento().getNome());
        Estabelecimento estabelecimento = null;
        if (estabelecimentoOptional.isEmpty()){
            estabelecimento = transacaoRequest.getEstabelecimento().toModel();
            estabelecimentoRepository.save(estabelecimento);
        }
        else {
            estabelecimento = estabelecimentoOptional.get();
        }
        Transacao transacao = transacaoRequest.toModel(estabelecimento, cartao);
        transacaoRepository.save(transacao);
    }
}
