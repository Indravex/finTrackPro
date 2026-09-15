package indravex.FinTrack.Pro.AccountManagement.service;

import indravex.FinTrack.Pro.AccountManagement.dto.AccountRequest;
import indravex.FinTrack.Pro.AccountManagement.entity.Account;

import java.util.List;

public interface AccountService {

    Account createAccount(AccountRequest request);

    List<Account> getAccountByCompanyId(Long companyID);

    Account updateAccount(Long id, AccountRequest request);

    void deactivateAccount(Long id);

}
