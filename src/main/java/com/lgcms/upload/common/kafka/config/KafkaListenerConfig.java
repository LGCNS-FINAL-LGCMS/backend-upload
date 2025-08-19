package com.lgcms.upload.common.kafka.config;


import com.lgcms.upload.common.kafka.dto.LectureEncodeDto;
import com.lgcms.upload.common.kafka.dto.KafkaEvent;
import com.lgcms.upload.common.kafka.dto.LectureUploadDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
public class KafkaListenerConfig {

    private final KafkaConfig kafkaConfig;

    public KafkaListenerConfig(KafkaConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaEvent> defaultFactory() {
        return kafkaConfig.kafkaListenerContainerFactory(KafkaEvent.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LectureEncodeDto> anotherValueFactory() {
        return kafkaConfig.kafkaListenerContainerFactory(LectureEncodeDto.class);
    }
}