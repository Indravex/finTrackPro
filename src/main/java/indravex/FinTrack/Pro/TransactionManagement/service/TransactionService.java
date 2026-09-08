package indravex.FinTrack.Pro.TransactionManagement.service;

import indravex.FinTrack.Pro.TransactionManagement.dto.TransactionRequest;
import indravex.FinTrack.Pro.TransactionManagement.entity.Transaction;

public interface TransactionService {

    Transaction createTransaction(TransactionRequest request);
}
