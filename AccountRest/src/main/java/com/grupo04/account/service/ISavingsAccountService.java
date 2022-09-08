package com.grupo04.account.service;

import java.util.List;
import java.util.Optional;

import com.grupo04.account.models.SavingsAccount;

public interface ISavingsAccountService {

    public List<SavingsAccount> findAll();
    public Optional<SavingsAccount> findById(Long id);
    public List<SavingsAccount> findByCustomerId(String customerId);
    public SavingsAccount save(SavingsAccount savingsAccount);
    public void delete(SavingsAccount savingsAccount);
}
