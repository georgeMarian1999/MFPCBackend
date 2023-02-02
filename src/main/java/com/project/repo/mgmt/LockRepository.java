package com.project.repo.mgmt;

import com.project.model.mgmt.Lock;
import com.project.model.mgmt.Transaction;
import com.project.model.mgmt.enums.LockType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LockRepository extends JpaRepository<Lock, Integer>, CrudRepository<Lock, Integer> {

    public Lock findLockByTableNameAndObjectId(String table, Integer objectId);

    public Lock findByTransaction(Transaction transaction);

    List<Lock> findAllByTransaction(Transaction transaction);

    List<Lock> findAllByTransactionNot(Transaction transaction);

    public boolean existsByLockTypeAndTableNameAndObjectId(LockType lockType, String table, Integer objectId);

    void deleteAllByTransactionId(Integer transactionId);
}
