create table document
(
    id   uuid not null,
    date date not null,
    constraint document_pkey primary key (id)
);

create table dictionary
(
    id   uuid not null,
    name varchar null,
    constraint dictionary_pkey primary key (id)
);


create table test_entity
(
    id   uuid not null,
    name varchar null,
    document_id uuid null,
    dictionary_id uuid null,
    constraint test_entity_pkey primary key (id),
    constraint document_fkey FOREIGN KEY (document_id) REFERENCES document (id),
    constraint dictionary_fkey FOREIGN KEY (dictionary_id) REFERENCES dictionary (id)
);