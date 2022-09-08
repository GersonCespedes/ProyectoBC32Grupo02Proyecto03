package com.grupo04.account.repository;

import com.grupo04.account.models.SavingsAccount;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISavingsAccountRepository extends JpaRepository<SavingsAccount,Long> {
    List<SavingsAccount> findByCustomerId(String customerId);
}
