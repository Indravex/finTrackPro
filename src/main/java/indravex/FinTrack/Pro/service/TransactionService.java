package indravex.FinTrack.Pro.service;

import indravex.FinTrack.Pro.dto.TransactionRequest;
import indravex.FinTrack.Pro.entity.Transaction;

public interface TransactionService {

    Transaction createTransaction(TransactionRequest request);
}
