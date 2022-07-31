package ru.test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.test.test.entity.Dictionary;
import ru.test.test.entity.Document;

import java.util.UUID;

public interface DictionaryRepository extends JpaRepository<Dictionary, UUID> {
}
