package com.project.service.mgmt;

import com.project.model.mgmt.Operation;
import com.project.repo.mgmt.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public List<Operation> findAllOperations () {
        return operationRepository.findAll();
    }

    public Operation findOperationById (Integer id) {
        return operationRepository.findById(id).get();
    }

    public void saveOperation (Operation operation) {
        operationRepository.save(operation);
    }

    public void deleteOperation (Operation operation) {
        operationRepository.delete(operation);
    }

    public void deleteOperationById (Integer id) {
        operationRepository.deleteById(id);
    }

    public Operation findOperationByTransactionId (Integer id) {
        return operationRepository.findOperationByTransactionId(id);
    }
}
