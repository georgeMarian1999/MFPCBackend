package com.project.model.mgmt;

import com.project.model.mgmt.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp timestamp;


    private TransactionStatus status;

    public Transaction() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = TransactionStatus.ACTIVE;
    }

    @OneToMany(mappedBy = "transaction")
    private List<Operation> operations;

    @OneToMany(mappedBy = "transaction")
    private List<Lock> locks;
}
