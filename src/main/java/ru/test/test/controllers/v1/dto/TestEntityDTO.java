package ru.test.test.controllers.v1.dto;

import lombok.Data;
import ru.test.test.entity.TestEntity;

import java.util.UUID;

@Data
public class TestEntityDTO {

    private UUID id;
    private String name;

    public static TestEntityDTO from(TestEntity testEntity) {
        TestEntityDTO dto = new TestEntityDTO();
        dto.setId(testEntity.getId());
        dto.setName(testEntity.getName());
        return dto;
    }

    public static TestEntity toTestEntity(TestEntityDTO dto) {
        TestEntity entity = new TestEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
