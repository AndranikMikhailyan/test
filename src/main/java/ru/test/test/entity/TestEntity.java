package ru.test.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class TestEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    @OneToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;
}
