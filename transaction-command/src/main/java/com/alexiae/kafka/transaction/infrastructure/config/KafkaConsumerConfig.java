package com.alexiae.kafka.transaction.infrastructure.config;

import com.alexiae.kafka.transaction.domain.event.DepositTransactionResultEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.topic.transaction-deposit-result}")
    private String transactionDepositResultTopic;


    private Map<String, Object> consumerConfigs() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "customer-group-id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        return config;
    }

    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> eventType) {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(eventType, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DepositTransactionResultEvent> transactionDepositResultKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DepositTransactionResultEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(DepositTransactionResultEvent.class));
        return factory;
    }

    @Bean
    public NewTopic transactionDepositResultTopic() {
        return TopicBuilder.name(transactionDepositResultTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
