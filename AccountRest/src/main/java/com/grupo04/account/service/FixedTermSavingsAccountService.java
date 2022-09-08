package com.grupo04.account.service;

import com.grupo04.account.models.FixedTermSavingsAccount;
import com.grupo04.account.repository.IFixedTermSavingsAccountRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class FixedTermSavingsAccountService implements IFixedTermSavingsAccountService {
	private IFixedTermSavingsAccountRepository ftsavingsAccountRepository;

	public FixedTermSavingsAccountService(IFixedTermSavingsAccountRepository ftsavingsAccountRepository) {
		this.ftsavingsAccountRepository = ftsavingsAccountRepository;
	}

	@Override
	public List<FixedTermSavingsAccount> findAll() {
		return ftsavingsAccountRepository.findAll();
	}

	@Override
	public Optional<FixedTermSavingsAccount> findById(Long id) {
		return ftsavingsAccountRepository.findById(id);
	}

	@Override
	public List<FixedTermSavingsAccount> findByCustomerId(Long customerId) {
		return ftsavingsAccountRepository.findByCustomerId(customerId);
	}

	@Override
	public FixedTermSavingsAccount save(FixedTermSavingsAccount reg) {
		return ftsavingsAccountRepository.save(reg);
	}

	@Override
	public void delete(FixedTermSavingsAccount reg) {
		ftsavingsAccountRepository.delete(reg);
	}
}
