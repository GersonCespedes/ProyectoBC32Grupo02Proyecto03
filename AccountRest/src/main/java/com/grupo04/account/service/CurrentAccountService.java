package com.grupo04.account.service;

import com.grupo04.account.models.CurrentAccount;
import com.grupo04.account.models.CustomerBusiness;
import com.grupo04.account.models.CustomerPersonal;
import com.grupo04.account.models.SavingsAccount;
import com.grupo04.account.repository.ICurrentAccountRepository;
import com.grupo04.account.repository.ISavingsAccountRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CurrentAccountService implements ICurrentAccountService {
	

    @Value("${customer.personal.url}")
    private String urlp;
    
    @Value("${customer.business.url}")
    private String urlb;
	
    private ICurrentAccountRepository currentAccountRepository;
    
    @Autowired
    private RestTemplate clientRest;

    public CurrentAccountService(ICurrentAccountRepository currentAccountRepository) {
        this.currentAccountRepository = currentAccountRepository;
    }

    @Override
    public List<CurrentAccount> findAll() {
        return currentAccountRepository.findAll();
    }

    @Override
    public Optional<CurrentAccount> findById(Long id) {
        return currentAccountRepository.findById(id);
    }

    @Override
    public List<CurrentAccount> findByCustomerId(Long customerId) {
        return currentAccountRepository.findByCustomerId(customerId);
    }

    @Override
    public CurrentAccount save(CurrentAccount account) {
        log.info(account.toString());

        //buscar el tipo de cliente
        CustomerPersonal p = clientRest.getForObject(urlp.concat(account.getCustomerId()), CustomerPersonal.class);
        log.info(p.toString());
        CustomerBusiness b = clientRest.getForObject(urlb.concat(account.getCustomerId()), CustomerBusiness.class);
        log.info(b.toString());

        //si no es cliente del banco
        if (p.getId() == null && b.getId() == null)
            return new CurrentAccount();

        //Si es cliente personal
        if (p.getId() != null) {
            return new CurrentAccount();
        }
        //Si es cliente empresarial
        else
            return  currentAccountRepository.save(account);
           
    }

    @Override
    public void delete(CurrentAccount currentAccount) {
        currentAccountRepository.delete(currentAccount);
    }
}
