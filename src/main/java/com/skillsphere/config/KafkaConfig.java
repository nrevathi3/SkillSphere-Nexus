package com.skillsphere.config;

import com.skillsphere.event.CertificationRenewedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaAdmin kafkaAdmin() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                "bootstrap.servers",
                "localhost:9092"
        );

        return new KafkaAdmin(config);
    }

    @Bean
    public ProducerFactory<String, CertificationRenewedEvent>
    certificationRenewalProducerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092"
        );

        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );

        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JacksonJsonSerializer.class
        );

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, CertificationRenewedEvent>
    certificationRenewalKafkaTemplate() {

        return new KafkaTemplate<>(
                certificationRenewalProducerFactory()
        );
    }

    @Bean
    public NewTopic certificationRenewedTopic() {

        System.out.println(
                ">>> CREATING KAFKA TOPIC: certification-renewed"
        );

        return new NewTopic(
                "certification-renewed",
                1,
                (short) 1
        );
    }
}