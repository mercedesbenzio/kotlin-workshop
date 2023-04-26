package io.mb.store.model

import io.mb.store.resource.DB_SCHEMA
import io.mb.store.resource.ITEM_SEQUENCE
import io.mb.store.resource.ITEM_TABLE
import jakarta.persistence.*

@Entity
@Table(schema = DB_SCHEMA, name = ITEM_TABLE)
data class Item(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = ITEM_SEQUENCE
    )
    @SequenceGenerator(name = ITEM_SEQUENCE)
    val id: Long? = null,
    @Column(unique = true, nullable = false)
    val vin: String,
    @Column(nullable = false)
    val dealerId: String
)