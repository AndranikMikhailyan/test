package ru.test.test.controllers.v1.dto;

import lombok.Data;
import ru.test.test.entity.TestEntity;

import java.util.UUID;

@Data
public class DocumentDTO {

    private UUID id;
    private String name;

    public static DocumentDTO from(TestEntity testEntity) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(testEntity.getId());
        dto.setName(testEntity.getName());
        return dto;
    }
}
