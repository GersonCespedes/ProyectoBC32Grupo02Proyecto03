package com.grupo04.account;
import com.grupo04.account.repository.ICurrentAccountRepository;
import com.grupo04.account.repository.IFixedTermSavingsAccountRepository;
import com.grupo04.account.repository.ISavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableEurekaClient
@SpringBootApplication
public class AccountApplication {
   
    @Autowired
    private ISavingsAccountRepository sarepository;
    @Autowired
    private ICurrentAccountRepository carepository;
    @Autowired
    private IFixedTermSavingsAccountRepository ftrepository;

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}