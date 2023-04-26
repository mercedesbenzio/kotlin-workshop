package io.mb.store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static io.mb.store.resource.Constants.*;

@Getter
@Setter
@Entity
@Table(schema = DB_SCHEMA, name = ITEM_TABLE)
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ITEM_SEQUENCE)
    @SequenceGenerator(name = ITEM_SEQUENCE)
    private Long id = null;

    @Column(unique = true, nullable = false)
    private String vin;

    @Column(nullable = false)
    private String dealerId;
}
