package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface agent extends CrudRepository<Tenant_entity, Integer>{

	List<Tenant_entity>findByName(String name);
	List<Tenant_entity> findByPropertyname(String propertyname);
}
