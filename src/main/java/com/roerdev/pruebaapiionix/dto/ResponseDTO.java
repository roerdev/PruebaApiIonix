package com.roerdev.pruebaapiionix.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.roerdev.pruebaapiionix.utils.DateUtils;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private String status;
    private Object data;
    private String message;
    private List<?> list;
    private Page<?> page;
    private Date timestamp;
    private UUID id;

    private Integer responseCode;
    private String description;
    private Long elapsedTime;
    private ResultDto result;

    public ResponseDTO() {
        super();
    }

    public ResponseDTO(Integer responseCode, String description, Long elapsedTime, ResultDto result) {
        this.responseCode = responseCode;
        this.description = description;
        this.elapsedTime = elapsedTime;
        this.result = result;
    }

    public ResponseDTO(String status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.timestamp = timestamp != null ? timestamp : DateUtils.getToFullDay();
    }

    public ResponseDTO(String status, Page<?> page, String message) {
        this.status = status;
        this.page = page;
        this.message = message;
        this.timestamp = timestamp != null ? timestamp : DateUtils.getToFullDay();
    }

    public ResponseDTO(String status, String message, UUID id) {
        this.status = status;
        this.message = message;
        this.id = id;
    }


}
