package io.mb.vehicles.model

import io.mb.vehicles.resource.DB_SCHEMA
import io.mb.vehicles.resource.VEHICLE_SEQUENCE
import io.mb.vehicles.resource.VEHICLE_TABLE
import jakarta.persistence.*

@Entity
@Table(schema = DB_SCHEMA, name = VEHICLE_TABLE)
data class Vehicle(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = VEHICLE_SEQUENCE
    )
    @SequenceGenerator(name = VEHICLE_SEQUENCE)
    val id: Long? = null,
    @Column(unique = true, nullable = false)
    val vin: String,
    @Column(nullable = false)
    val brand: String,
    @Column(nullable = false)
    val model: String,
    @Column(nullable = false)
    val fuelType: String,
    @Column(nullable = false)
    val mileageUnit: String,
    @Column(nullable = true)
    val kw: Int? = null,
    @Column(nullable = true)
    val mileage: Int? = null,
)