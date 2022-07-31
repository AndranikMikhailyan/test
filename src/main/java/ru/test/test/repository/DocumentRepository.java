package ru.test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.test.test.entity.Document;
import ru.test.test.entity.TestEntity;

import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
}
