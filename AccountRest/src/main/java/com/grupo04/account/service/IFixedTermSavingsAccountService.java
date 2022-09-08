package com.grupo04.account.service;

import java.util.List;
import java.util.Optional;

import com.grupo04.account.models.FixedTermSavingsAccount;

public interface IFixedTermSavingsAccountService {

    public List<FixedTermSavingsAccount> findAll();
    public Optional<FixedTermSavingsAccount> findById(Long id);
    public List<FixedTermSavingsAccount> findByCustomerId(Long customerId);
    public FixedTermSavingsAccount save(FixedTermSavingsAccount ftsavingsaccount);
    public void delete(FixedTermSavingsAccount ftsavingsaccount);
}
