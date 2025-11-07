package dev.oudom.mbanking.features.accountType;

import dev.oudom.mbanking.features.accountType.dto.AccountTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @GetMapping
    public List<AccountTypeResponse> getAllAccountTypes() {
        return accountTypeService.findAllAccountTypes();
    }

    @GetMapping("/alias/{alias}")
    public AccountTypeResponse getAccountTypeByAlias(@PathVariable String alias) {
        return accountTypeService.findAccountTypeByAlias(alias);
    }
}
