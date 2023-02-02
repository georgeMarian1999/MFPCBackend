package com.project.service.mgmt;

import com.project.model.mgmt.WaitFor;
import com.project.repo.mgmt.WaitForRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaitForService {

    @Autowired
    private WaitForRepository waitForRepository;


    public void saveWaitFor(WaitFor waitFor) {
        waitForRepository.save(waitFor);
    }

    public void deleteWaitFor(WaitFor waitFor) {
        waitForRepository.delete(waitFor);
    }

    public WaitFor findByTransactionHasLockId(Integer transactionHasLockId) {
        return waitForRepository.findByTransactionHasLockId(transactionHasLockId);
    }

    public WaitFor findByTransactionWaitsForLockId(Integer transactionWaitsForLockId) {
        return waitForRepository.findByTransactionWaitsForLockId(transactionWaitsForLockId);
    }


}
