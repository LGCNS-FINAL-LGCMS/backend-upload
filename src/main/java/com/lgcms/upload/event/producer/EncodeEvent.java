package com.lgcms.upload.event.producer;

import com.lgcms.upload.common.kafka.dto.KafkaEvent;
import com.lgcms.upload.common.kafka.dto.LectureEncodeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EncodeEvent {

    private final KafkaTemplate kafkaTemplate;

    public void LectureEncodeEvent(LectureEncodeDto lectureEncodeDto) {
        KafkaEvent kafkaEvent = KafkaEvent.builder()
                .eventType("ENCODING")
                .eventId("backend-upload" + UUID.randomUUID())
                .eventTime(LocalDateTime.now().toString())
                .data(lectureEncodeDto)
                .build();

        kafkaTemplate.send("ENCODING", kafkaEvent);
    }
}
