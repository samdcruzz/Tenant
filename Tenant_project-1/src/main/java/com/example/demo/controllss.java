package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenants")
public class controllss {
	
	
	 @Autowired
	    private Tenant_connector tencon;

	    @PostMapping("/addtenantdata")
	    public ResponseEntity<String> addTenant(@RequestBody(required = true) Tenant_entity tenant) {
	    	if (tenant == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body is missing!");
	        }
	    	if (tenant.getName() == null || tenant.getContact() == null || 
	                tenant.getPropertyname() == null || tenant.getRent() <= 0) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data!");
	            }
	            tencon.save(tenant);
	            return ResponseEntity.status(HttpStatus.CREATED).body("Tenant added successfully!");
	        }

	    @GetMapping("/all")
	    public List<Tenant_entity> getAllTenants() {
	        return (List<Tenant_entity>) tencon.findAll();
	    }

	    @GetMapping("/find/{id}")
	    public Tenant_entity findTenant(@PathVariable int id) {
	        return tencon.findById(id).orElse(null);
	    }
	    
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteTenant(@PathVariable int id) {
	        Optional<Tenant_entity> optionalTenant = tencon.findById(id);
	        if (optionalTenant.isPresent()) {
	            tencon.deleteById(id);
	            return ResponseEntity.ok("Tenant deleted successfully!");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tenant not found!");
	        }
	    }


	    @PutMapping("/update/{id}")
	    public String updateTenant(@PathVariable int id, @RequestBody Tenant_entity tenant) {
	        Optional<Tenant_entity> optionalTenant = tencon.findById(id);
	        if (optionalTenant.isPresent()) {
	            Tenant_entity tenants = optionalTenant.get();
	            tenants.setName(tenants.getName());
	            tenants.setContact(tenants.getContact());
	            tenants.setPropertyname(tenants.getPropertyname());
	            tenants.setRent(tenants.getRent());
	            tencon.save(tenants);
	            return "Tenant updated successfully!";
	        } else {
	            return "Tenant not found!";
	        }
	    }
	    

	    
	    	

}
