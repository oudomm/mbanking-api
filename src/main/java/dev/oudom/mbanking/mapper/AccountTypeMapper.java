package dev.oudom.mbanking.mapper;

import dev.oudom.mbanking.domain.AccountType;
import dev.oudom.mbanking.features.accountType.dto.AccountTypeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

    AccountTypeResponse toAccountTypeResponse(AccountType accountType);
}
