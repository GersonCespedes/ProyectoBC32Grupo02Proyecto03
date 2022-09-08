package com.grupo04.account.controllers;

import com.grupo04.account.models.SavingsAccount;
import com.grupo04.account.service.ISavingsAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/savings")
@Slf4j
public class SavingsAccountController {
	//@Autowired
	//private CircuitBreakerFactory cbFactory;

	@Autowired
	private ISavingsAccountService service;

	@GetMapping
	public List<SavingsAccount> listar(Model model) {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Optional<SavingsAccount> detail(@PathVariable Long id) {
		return service.findById(id);
	}

	public Optional<SavingsAccount> detailA(Long id) {
		return service.findById(id);
	}

	public Optional<SavingsAccount> detailB(Long id) {
		return service.findById(id);
	}

	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody SavingsAccount monoCurrent) {
		Map<String, Object> result = new HashMap<String, Object>();
		log.info(monoCurrent.toString());

		SavingsAccount p = service.save(monoCurrent);
		if (p.getId() == null) {
			result.put("customer", monoCurrent.getCustomerId());
			result.put("account", null);
			result.put("message", "No se genero la cuenta");
			result.put("status", false);
			return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(result);
		} else {
			result.put("customer", monoCurrent.getCustomerId());
			result.put("account", p);
			result.put("status", true);
			result.put("message", "Cuenta corriente guardada con éxito");
			return ResponseEntity.created(URI.create("/api/savings/".concat(p.getId().toString())))
					.contentType(MediaType.APPLICATION_JSON).body(result);
		}

	}

	@GetMapping("/find/{customerid}")
	public List<SavingsAccount> findByCustomer(@PathVariable String customerid) {
		List<SavingsAccount> accounts = service.findByCustomerId(customerid);
		return accounts;
	}

	@PutMapping("/{id}")
	public SavingsAccount edit(@RequestBody SavingsAccount savings, @PathVariable Long id) {
		SavingsAccount p = service.findById(id).get();
		p.setAvailableBalance(savings.getAvailableBalance());
		p.setUpdatedAt(LocalDate.now());
		return service.save(p);
	}

	@DeleteMapping("/{id}")
	public void edit(@PathVariable Long id) {
		SavingsAccount p = service.findById(id).get();
		service.delete(p);
	}

}