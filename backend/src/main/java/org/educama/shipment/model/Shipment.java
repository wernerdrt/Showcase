package org.educama.shipment.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "trackingId")})
public class Shipment extends AbstractPersistable<Long> {

    @NotBlank
    public String senderAddress;

    @NotBlank
    public String receiverAddress;

    @Column(unique = true)
    public String trackingId;
}
