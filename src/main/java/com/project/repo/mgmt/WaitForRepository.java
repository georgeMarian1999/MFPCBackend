package com.project.repo.mgmt;

import com.project.model.mgmt.WaitFor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitForRepository extends JpaRepository<WaitFor, Integer> {


    WaitFor findByTransactionHasLockId(Integer transactionHasLockId);

    WaitFor findByTransactionWaitsForLockId(Integer transactionWaitsForLockId);

}
