package com.lgcms.upload.common.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaEvent<T> {

    private String eventId;
    private String eventType;
    private String eventTime;
    private Object data;
}
