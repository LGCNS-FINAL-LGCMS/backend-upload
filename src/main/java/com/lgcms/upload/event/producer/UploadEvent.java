package com.lgcms.upload.event.producer;

import com.lgcms.upload.common.kafka.dto.KafkaEvent;
import com.lgcms.upload.common.kafka.dto.LectureUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadEvent {
    private final KafkaTemplate kafkaTemplate;

    public void lectureUploadEvent(LectureUploadDto lectureUploadDto) {
        KafkaEvent kafkaEvent = KafkaEvent.builder()
                .eventId("backend-upload"+ UUID.randomUUID().toString())
                .eventTime(LocalDateTime.now().toString())
                .eventType("LECTURE_UPLOAD")
                .data(lectureUploadDto)
                .build();
        System.out.println(lectureUploadDto.getLectureId());
        System.out.println(lectureUploadDto.getBookKey());
        System.out.println(lectureUploadDto.getThumbnailKey());
        kafkaTemplate.send("LECTURE_UPLOAD", kafkaEvent);
    }
}
