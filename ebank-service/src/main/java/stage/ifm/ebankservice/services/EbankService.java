package stage.ifm.ebankservice.services;

import org.springframework.stereotype.Service;
import stage.ifm.ebankservice.entities.BankAccount;
import stage.ifm.ebankservice.feign.CustomerRestClient;
import stage.ifm.ebankservice.repository.BankAccountRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public EbankService(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    public List<BankAccount> getAllBankAccount() {
        return bankAccountRepository.findAll();
    }

    public BankAccount getBankAccountById(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException("Bank Account Not Found"));
        bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
        return bankAccount;
    }

    public BankAccount save(BankAccount bankAccount) {
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreateAt(new Date());
        return bankAccountRepository.save(bankAccount);
    }
}
