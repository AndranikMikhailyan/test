package ru.test.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.test.controllers.v1.dto.TestEntityDTO;
import ru.test.test.entity.Dictionary;
import ru.test.test.entity.Document;
import ru.test.test.entity.TestEntity;
import ru.test.test.repository.DictionaryRepository;
import ru.test.test.repository.DocumentRepository;
import ru.test.test.repository.TestEntityRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestEntityServiceImpl implements TestEntityService {

    private final TestEntityRepository testEntityRepository;
    private final DocumentRepository documentRepository;
    private final DictionaryRepository dictionaryRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Optional<TestEntity> getById(UUID id) {
        return testEntityRepository.findById(id);
    }

    @Override
    public List<TestEntity> getAllTestEntity() {
        return testEntityRepository.findAll();
    }

    @Override
    public TestEntity createTestEntity(String name, UUID documentId, UUID dictionaryId) {
        TestEntity entity = new TestEntity();
        entity.setName(name);
        if (documentId != null) {
            Document document = documentRepository.findById(documentId)
                    .orElseThrow(() -> new RuntimeException(String.format("Document not found by id: %s", documentId)));
            entity.setDocument(document);
        }
        if (dictionaryId != null) {
            Dictionary dictionary = dictionaryRepository.findById(dictionaryId)
                    .orElseThrow(() -> new RuntimeException(String.format("Dictionary not found by id: %s", dictionaryId)));
            entity.setDictionary(dictionary);
        }

        return testEntityRepository.save(entity);
    }

    @Override
    public TestEntity updateTestEntity(UUID id, String patch) {
        TestEntity entity = testEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("TestEntity not found by id: %s", id)));
        TestEntityDTO entityDTO = TestEntityDTO.from(entity);
        try {
            entityDTO = objectMapper.readerForUpdating(entityDTO).readValue(patch);
        } catch (IOException e) {
            log.error("Error while reading JSON", e);
        }
        TestEntity updated = TestEntityDTO.toTestEntity(entityDTO);
        updated.setId(entity.getId());
        updated.setDocument(entity.getDocument());
        updated.setDictionary(entity.getDictionary());
        return testEntityRepository.save(updated);
    }

    @Override
    public void deleteEntity(UUID id) {
        testEntityRepository.deleteById(id);
    }
}
