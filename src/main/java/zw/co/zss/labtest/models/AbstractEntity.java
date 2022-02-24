package zw.co.zss.labtest.models;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import zw.co.zss.labtest.enums.EntityStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Version
    protected Long version;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected OffsetDateTime dateCreated;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    protected OffsetDateTime lastUpdated;

    @Enumerated(EnumType.STRING)
    protected EntityStatus status;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
    }

}
