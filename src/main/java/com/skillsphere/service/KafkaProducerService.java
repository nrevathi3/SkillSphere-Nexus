package com.skillsphere.service;

import com.skillsphere.event.CertificationRenewedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String TOPIC = "certification-renewed";

    private final KafkaTemplate<String, CertificationRenewedEvent> kafkaTemplate;

    public void publishCertificationRenewed(
            CertificationRenewedEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.getCertificationId().toString(),
                event
        );
    }
}