package com.grupo04.account.controllers;

import com.grupo04.account.models.FixedTermSavingsAccount;
import com.grupo04.account.service.IFixedTermSavingsAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
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
@RequestMapping("/api/ftsavings")
@Slf4j
public class FixedTermSavingsAccountController{

	//@Autowired
	//private CircuitBreakerFactory cbFactory;

	@Autowired
	private IFixedTermSavingsAccountService service;

	@GetMapping
	public List<FixedTermSavingsAccount> listar(Model model) {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Optional<FixedTermSavingsAccount> detail(@PathVariable Long id) {
		return service.findById(id);
	}

	public Optional<FixedTermSavingsAccount> detailA(Long id) {
		return service.findById(id);
	}

	public Optional<FixedTermSavingsAccount> detailB(Long id) {
		return service.findById(id);
	}

	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody FixedTermSavingsAccount monoCurrent) {
		Map<String, Object> result = new HashMap<String, Object>();
		log.info(monoCurrent.toString());

		FixedTermSavingsAccount p = service.save(monoCurrent);
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
	public List<FixedTermSavingsAccount> findByCustomer(@PathVariable Long customerid) {
		List<FixedTermSavingsAccount> accounts = service.findByCustomerId(customerid);
		return accounts;
	}

	@PutMapping("/{id}")
	public FixedTermSavingsAccount edit(@RequestBody FixedTermSavingsAccount savings, @PathVariable Long id) {
		FixedTermSavingsAccount p = service.findById(id).get();
		p.setAvailableBalance(savings.getAvailableBalance());
		p.setUpdatedAt(LocalDate.now());
		return service.save(p);
	}

	@DeleteMapping("/{id}")
	public void edit(@PathVariable Long id) {
		FixedTermSavingsAccount p = service.findById(id).get();
		service.delete(p);
	}

}