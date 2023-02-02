package com.project.repo.mgmt;

import com.project.model.mgmt.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Integer> {

    Operation findOperationByTransactionId(Integer id);
}
