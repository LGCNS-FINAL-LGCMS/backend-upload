package com.lgcms.upload.event.producer;

import com.lgcms.upload.common.kafka.dto.KafkaEvent;
import com.lgcms.upload.common.kafka.dto.LectureUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UploadEvent {
    private final KafkaTemplate kafkaTemplate;

    public void lectureUploadEvent(LectureUploadDto lectureUploadDto) {
        KafkaEvent kafkaEvent = KafkaEvent.builder()
                .eventId("업로드1")
                .eventTime(LocalDateTime.now().toString())
                .eventType("강의교안 강의썸네일 업로드 완료")
                .data(lectureUploadDto)
                .build();
        kafkaTemplate.send("UPLOAD-01", kafkaEvent);
    }
}
