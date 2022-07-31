package ru.test.test.controllers.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEntityRequestDTO {
    private String name;
    private UUID documentId;
    private UUID dictionaryId;
}
