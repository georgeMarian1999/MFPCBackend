package com.project.model.mgmt;

import com.project.model.mgmt.enums.LockType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "locks")
public class Lock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    public Lock() {
    }
    public Lock(LockType lockType, Integer objectId, String tableName, Transaction transaction) {
        this.lockType = lockType;
        this.objectId = objectId;
        this.tableName = tableName;
        this.transaction = transaction;
    }

    private LockType lockType;

    private Integer objectId;

    private String tableName;

    @ManyToOne
    @JoinColumn(name = "id_transaction")
    private Transaction transaction;
}
