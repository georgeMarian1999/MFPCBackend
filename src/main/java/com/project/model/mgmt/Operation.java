package com.project.model.mgmt;

import com.project.model.mgmt.enums.OperationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "operations")
public class Operation {
    @Id
    private Integer id;

    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "id_transaction")
    private Transaction transaction;



}
