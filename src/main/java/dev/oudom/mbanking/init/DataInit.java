package dev.oudom.mbanking.init;

import dev.oudom.mbanking.domain.AccountType;
import dev.oudom.mbanking.domain.CardType;
import dev.oudom.mbanking.domain.Role;
import dev.oudom.mbanking.features.accountType.AccountTypeRepository;
import dev.oudom.mbanking.features.cardType.CardTypeRepository;
import dev.oudom.mbanking.features.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final CardTypeRepository cardTypeRepository;

    @PostConstruct
    void initRole() {

       if (roleRepository.count() < 1) {
           Role user = new Role();
           user.setName("USER");

           Role customer = new Role();
           customer.setName("CUSTOMER");

           Role staff = new Role();
           staff.setName("STAFF");

           Role admin = new Role();
           admin.setName("ADMIN");

           roleRepository.saveAll(
                   List.of(user, customer, staff, admin)
           );
       }

    }

    @PostConstruct
    void initAccountType() {
        if (accountTypeRepository.count() < 1) {
            AccountType savingActType = new AccountType();
            savingActType.setName("Saving Account");
            savingActType.setAlias("saving-account");
            savingActType.setDescription("A basic deposit account designed for saving money with interest and easy access to funds. Ideal for long-term saving and general financial management.");
            savingActType.setIsDeleted(false);
            accountTypeRepository.save(savingActType);

            AccountType payrollActType = new AccountType();
            payrollActType.setName("Payroll Account");
            payrollActType.setAlias("payroll-account");
            payrollActType.setDescription("An account used for salary deposits and everyday financial transactions. Optimized for receiving payroll and managing monthly expenses.");
            payrollActType.setIsDeleted(false);
            accountTypeRepository.save(payrollActType);

            AccountType cardActType = new AccountType();
            cardActType.setName("Card Account");
            cardActType.setAlias("card-account");
            cardActType.setDescription("An account linked to debit or ATM cards, used for cash withdrawals, purchases, and daily transactions. Provides convenient access to funds.");
            cardActType.setIsDeleted(false);
            accountTypeRepository.save(cardActType);
        }
    }

    @PostConstruct
    void initCardType() {
        if (cardTypeRepository.count() < 1) {
            CardType visaCard = new CardType();
            visaCard.setName("VISA Card");
            visaCard.setIsDeleted(false);
            cardTypeRepository.save(visaCard);

            CardType masterCard = new CardType();
            masterCard.setName("Master Card");
            masterCard.setIsDeleted(false);
            cardTypeRepository.save(masterCard);
        }
    }
}
