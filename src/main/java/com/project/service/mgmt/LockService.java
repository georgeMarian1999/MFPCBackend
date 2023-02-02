package com.project.service.mgmt;


import com.project.model.mgmt.Lock;
import com.project.model.mgmt.Transaction;
import com.project.model.mgmt.enums.LockType;
import com.project.repo.mgmt.LockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LockService {

    @Autowired
    private LockRepository lockRepository;


    public List<Lock> findAllLocks () {
        return lockRepository.findAll();
    }

    public Lock findLockByTableAndObjectId(String table, Integer objectId) {
        return lockRepository.findLockByTableNameAndObjectId(table, objectId);
    }

    public Lock findByTransaction(Transaction transaction){
        return lockRepository.findByTransaction(transaction);
    }

    public boolean isLocked(String table, Integer objectId, Transaction transaction) {
        int numberOfOtherLocks = lockRepository.findAllByTransactionNot(transaction).size();
        return numberOfOtherLocks != 0;
    }

    public void saveLock(Lock lock) {
        lockRepository.save(lock);
    }

    public void deleteLock(Lock lock) {
        lockRepository.delete(lock);
    }

    public void deleteAllByTransaction(Transaction transaction) {
        // ockRepository.removeAllByTransaction(transaction);
        // lockRepository.deleteAllByTransactionId(transaction.getId());
        List<Lock> locks = lockRepository.findAllByTransaction(transaction);
        lockRepository.deleteAll(locks);
    }
}
