package org.educama.shipment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "trackingId")})
public class Shipment extends AbstractPersistable<Long> {

    @Column(unique = true)
    public String trackingId;

    @NotBlank
    public String senderAddress;

    @NotBlank
    public String receiverAddress;

    @NotBlank
    public String customer;
}