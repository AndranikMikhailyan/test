package ru.test.test.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.test.test.controllers.v1.dto.CreateEntityRequestDTO;
import ru.test.test.controllers.v1.dto.TestEntityDTO;
import ru.test.test.entity.TestEntity;
import ru.test.test.service.TestEntityService;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/test-entities")
@RequiredArgsConstructor
public class TestEntityController {

    private final TestEntityService testEntityService;

    @GetMapping("/{id}")
    public ResponseEntity<TestEntityDTO> getTestEntity(
            @PathVariable UUID id
    ) {
        return testEntityService.getById(id)
                .map(testEntity ->
                        ResponseEntity.ok(TestEntityDTO.from(testEntity)))
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping
    public ResponseEntity<List<TestEntityDTO>> getAll( ) {
        List<TestEntity> testEntities = testEntityService.getAllTestEntity();
        return ResponseEntity.ok(
                testEntities.stream()
                        .map(TestEntityDTO::from)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<TestEntityDTO> createTestEntity(
            @RequestBody CreateEntityRequestDTO requestDTO
    ) {
        TestEntity testEntity = testEntityService.createTestEntity(
                requestDTO.getName(),
                requestDTO.getDocumentId(),
                requestDTO.getDictionaryId()
        );
        URI location = UriComponentsBuilder.newInstance().path(testEntity.getId().toString()).build().toUri();
        return ResponseEntity
                .created(location)
                .body(TestEntityDTO.from(testEntity));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TestEntityDTO> updateTestEntity(
            @PathVariable UUID id,
            @RequestBody String patch
    ) {
        TestEntity entity = testEntityService.updateTestEntity(id, patch);
        return ResponseEntity.ok(TestEntityDTO.from(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TestEntityDTO> deleteEntity(
            @PathVariable UUID id
    ) {
        testEntityService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }
}
