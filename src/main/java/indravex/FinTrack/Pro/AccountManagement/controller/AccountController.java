package indravex.FinTrack.Pro.AccountManagement.controller;

import indravex.FinTrack.Pro.AccountManagement.dto.AccountRequest;
import indravex.FinTrack.Pro.AccountManagement.dto.AccountResponse;
import indravex.FinTrack.Pro.AccountManagement.entity.Account;
import indravex.FinTrack.Pro.AccountManagement.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest request){

        Account account = accountService.createAccount(request);

        AccountResponse response = AccountResponse.builder()
                .accountId(account.getId())
                .companyId(account.getCompany().getId())
                .accountName(account.getAccountName())
                .accountType(request.getAccountType())
                .openingBalance(account.getOpeningBalance())
                .currentBalance(account.getCurrentBalance())
                .status(account.isStatus())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAccounts(
            @RequestParam Long companyId) {

        List<Account> accounts = accountService.getAccountByCompanyId(companyId);

        List<AccountResponse> responses = accounts.stream()
                .map(account -> AccountResponse.builder()
                        .accountId(account.getId())
                        .companyId(account.getCompany().getId())
                        .accountName(account.getAccountName())
                        .accountType(account.getAccountType())
                        .openingBalance(account.getOpeningBalance())
                        .currentBalance(account.getCurrentBalance())
                        .status(account.isStatus())
                        .createdAt(account.getCreatedAt())
                        .updatedAt(account.getUpdatedAt())
                        .build())
                .toList();

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(
            @PathVariable Long id,
            @Valid @RequestBody AccountRequest request) {

        Account account = accountService.updateAccount(id, request);

        AccountResponse response = AccountResponse.builder()
                .accountId(account.getId())
                .companyId(account.getCompany().getId())
                .accountName(account.getAccountName())
                .accountType(account.getAccountType())
                .openingBalance(account.getOpeningBalance())
                .currentBalance(account.getCurrentBalance())
                .status(account.isStatus())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateAccount(@PathVariable Long id) {

        accountService.deactivateAccount(id);

        return ResponseEntity.noContent().build();
    }
}
