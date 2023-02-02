package com.project.model.mgmt;

import com.project.model.mgmt.enums.LockType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wait_for")
public class WaitFor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LockType lockType;

    private String lockTable;

    private Integer lockObjectId;


    private Integer transactionHasLockId;


    private Integer transactionWaitsForLockId;


    public WaitFor() {

    }

    public WaitFor(LockType lockType, String lockTable, Integer lockObjectId, Integer transactionHasLockId, Integer transactionWaitsForLockId) {
        this.lockType = lockType;
        this.lockTable = lockTable;
        this.lockObjectId = lockObjectId;
        this.transactionHasLockId = transactionHasLockId;
        this.transactionWaitsForLockId = transactionWaitsForLockId;
    }
}
