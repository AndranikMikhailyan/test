package ru.test.test.service;

import ru.test.test.entity.TestEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TestEntityService {
    Optional<TestEntity> getById(UUID id);

    List<TestEntity> getAllTestEntity();

    TestEntity createTestEntity(String name, UUID documentId, UUID dictionaryId);

    TestEntity updateTestEntity(UUID id, String patch);

    void deleteEntity(UUID id);
}
