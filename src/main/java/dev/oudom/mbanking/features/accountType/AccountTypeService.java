package dev.oudom.mbanking.features.accountType;

import dev.oudom.mbanking.features.accountType.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {

    List<AccountTypeResponse> findAllAccountTypes();

    AccountTypeResponse findAccountTypeByAlias(String alias);
}
