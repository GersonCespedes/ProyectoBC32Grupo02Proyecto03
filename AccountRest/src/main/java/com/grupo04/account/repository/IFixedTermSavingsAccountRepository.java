package com.grupo04.account.repository;

import com.grupo04.account.models.FixedTermSavingsAccount;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IFixedTermSavingsAccountRepository extends JpaRepository<FixedTermSavingsAccount,Long> {

    List<FixedTermSavingsAccount> findByCustomerId(Long customerId);
}
