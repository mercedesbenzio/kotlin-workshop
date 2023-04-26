package io.mb.dealers.repository;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import static io.mb.dealers.repository.RepositoryConstants.*;

// ****** Lombok Annotations to avoid boilerplate code
@Getter
@Setter
// ******
@Entity
@Table(schema = DB_SCHEMA, name = DEALER_TABLE)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DealerEntity implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.UUID, generator = DEALER_SEQUENCE)
    @SequenceGenerator(name = DEALER_SEQUENCE)
    @Column(nullable = false)
    private String id;

    @Column(nullable = false, unique = true)
    private String dealerId;

    @Column(nullable = false)
    private String name;

    private String description;

    // ****** Lombok Annotations to avoid boilerplate code
    @Builder
    // ******
    private static DealerEntity of(final String dealerId, final String name, final String description) {
        return new DealerEntity(null, dealerId, name, description);
    }
}
