package dev.oudom.mbanking.features.accountType;

import dev.oudom.mbanking.domain.AccountType;
import dev.oudom.mbanking.features.accountType.dto.AccountTypeResponse;
import dev.oudom.mbanking.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;

    @Override
    public List<AccountTypeResponse> findAllAccountTypes() {
        List<AccountType> accountTypes = accountTypeRepository.findAllByIsDeletedFalse();

        return accountTypes.stream().map(accountTypeMapper::toAccountTypeResponse).toList();
    }

    @Override
    public AccountTypeResponse findAccountTypeByAlias(String alias) {
        AccountType accountType = accountTypeRepository
                .findByAliasAndIsDeletedFalse(alias)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account type not found"));

        return accountTypeMapper.toAccountTypeResponse(accountType);
    }
}
