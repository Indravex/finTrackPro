package indravex.FinTrack.Pro.AccountManagement.service;

import indravex.FinTrack.Pro.AccountManagement.dto.AccountRequest;
import indravex.FinTrack.Pro.AccountManagement.entity.Account;
import indravex.FinTrack.Pro.AccountManagement.repository.AccountRepository;
import indravex.FinTrack.Pro.CompanyManagement.entity.Company;
import indravex.FinTrack.Pro.CompanyManagement.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CompanyRepository companyRepository;
    private final JsonMapper.Builder builder;

    @Override
    public Account createAccount(AccountRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException(
                        "Company not found with id: " + request.getCompanyId()));

        Account account = Account.builder()
                .company(company)
                .accountName(request.getAccountName())
                .accountType(request.getAccountType())
                .openingBalance(request.getOpeningBalance())
                .currentBalance(request.getOpeningBalance())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccountByCompanyId(Long companyId){

        return accountRepository.findByCompanyId(companyId);
    }

    @Override
    public Account updateAccount(Long id, AccountRequest request) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Account not found with id: " + id));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException(
                        "Company not found with id: " + request.getCompanyId()));

        account.setCompany(company);
        account.setAccountName(request.getAccountName());
        account.setAccountType(request.getAccountType());
        account.setOpeningBalance(request.getOpeningBalance());
        account.setUpdatedAt(java.time.LocalDateTime.now());

        return accountRepository.save(account);
    }

    @Override
    public void deactivateAccount(Long id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Account not found with id: " + id));

        account.setStatus(false);
        account.setUpdatedAt(java.time.LocalDateTime.now());

        accountRepository.save(account);
    }
}