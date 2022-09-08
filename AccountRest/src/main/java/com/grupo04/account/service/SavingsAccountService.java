package com.grupo04.account.service;

import com.grupo04.account.models.CustomerBusiness;
import com.grupo04.account.models.CustomerPersonal;
import com.grupo04.account.models.SavingsAccount;
import com.grupo04.account.repository.ISavingsAccountRepository;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SavingsAccountService implements ISavingsAccountService {

	@Value("${customer.personal.url}")
	private String urlp;

	@Value("${customer.business.url}")
	private String urlb;

	@Autowired
	private RestTemplate clientRest;
	private ISavingsAccountRepository savingsAccountRepository;

	public SavingsAccountService(ISavingsAccountRepository savingsAccountRepository) {
		this.savingsAccountRepository = savingsAccountRepository;
	}

	@Override
	public List<SavingsAccount> findAll() {
		return savingsAccountRepository.findAll();
	}

	@Override
	public Optional<SavingsAccount> findById(Long id) {
		return savingsAccountRepository.findById(id);
	}

	@Override
	public List<SavingsAccount> findByCustomerId(String customerId) {
		return savingsAccountRepository.findByCustomerId(customerId);
	}

	@Override
    public SavingsAccount save(SavingsAccount account) {

        //buscar el tipo de cliente
        CustomerPersonal p = clientRest.getForObject(urlp.concat(account.getCustomerId()), CustomerPersonal.class);
        log.info(p.toString());
        CustomerBusiness b = clientRest.getForObject(urlb.concat(account.getCustomerId()), CustomerBusiness.class);
        log.info(b.toString());

        //si no es cliente del banco
        if (p.getId() == null && b.getId() == null)
            return new SavingsAccount();

        //Si es cliente personal
        if (p.getId() != null) {
           List<SavingsAccount> s= this.findByCustomerId(account.getCustomerId());
                   
                    if(s.get(0) == null)
                            return savingsAccountRepository.save(account);
                    else

                        return new SavingsAccount();
        }
        //Si es cliente empresarial
        else
            return new SavingsAccount();
    }

	@Override
	public void delete(SavingsAccount savingsAccount) {
		savingsAccountRepository.delete(savingsAccount);
	}
}
