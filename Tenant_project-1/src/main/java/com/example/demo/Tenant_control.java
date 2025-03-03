package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tenant")
public class Tenant_control {

	@Autowired
    private agent tencon;
    	
    @RequestMapping("Tenant_entry")
   public String tenenty() {
    	return "Tenant_entry";
    }
    
    @RequestMapping("adddata")
   public String addten(@RequestParam String name, String contact, String propertyname, int rent) {
    	Tenant_entity tents = new Tenant_entity();
    	tents.setName(name);
    	tents.setContact(contact);
    	tents.setPropertyname(propertyname);
    	tents.setRent(rent);
    	tencon.save(tents);
    	return "Tenant_entry";
    }
    @RequestMapping("Tenantdata_view")
   public String dataview(Model mode) {
    	List<Tenant_entity> items = (List<Tenant_entity>)tencon.findAll();
    	mode.addAttribute("items", items);
    	return "Tenantdata_view";
    }
    @RequestMapping("Findall_data")
   public String findall() {
    	return "Findall_data";
    }
    @RequestMapping("Tenantdata_find")
   public String tenantfind()
    {
    	return "Tenantdata_find";
    }
    @RequestMapping("Tenant_list")
    public String tenantlist()
    {
    	return "Tenant_list";
    }
    @RequestMapping("Tenant_name")
   public String name(@RequestParam String name,Model mode)
    {
    	List<Tenant_entity>items = (List<Tenant_entity>)tencon.findByName(name);
    	mode.addAttribute("items",items);
    	return "Tenant_list";
    }
    @RequestMapping("Tenant_property")
    public String findproperty(@RequestParam String propertyname, Model mode)
    {
    	List<Tenant_entity> items = (List<Tenant_entity>)tencon.findByPropertyname(propertyname);
    	mode.addAttribute("items",items);
    	return "Tenant_list";
    }
    @RequestMapping("update")
	String update(@RequestParam int id, String name, Model mod)
	{
		Tenant_entity tenant = tencon.findById(id).orElse(null);
		tenant.setName(name);
		tencon.save(tenant);
		mod.addAttribute("msg", "Your Name is updated");
		
		List<Tenant_entity> items = (List<Tenant_entity>)tencon.findAll();
		mod.addAttribute("items", items);
		return "Tenantdata_view";
	}
    @GetMapping("updatepage/{id}")
    public String UpdatePage(@PathVariable int id, Model mode) {
        Tenant_entity tenant = tencon.findById(id).orElse(null);
        mode.addAttribute("items", tenant);
        return "edit_tenant";
    }
    
    
    @RequestMapping("update_rent")
    public String updateRent( @RequestParam("id") int id, int rent, Model mode) {
        Tenant_entity tenant = tencon.findById(id).orElse(null);
        if (tenant != null) {
            tenant.setRent(rent);
            tencon.save(tenant);
            mode.addAttribute("msg", "Rent updated successfully!");
        } else {
            mode.addAttribute("msg", "Tenant not found!");
        }
        mode.addAttribute("tenants", tencon.findAll());
        return "Tenant_list";
    }
    @RequestMapping("edit_tenant")
    public String edit()
    {
    	return "edit_tenant";
    }

    @RequestMapping("edit")
    public String editTenant(@RequestParam int id, String name, String contact, String propertyname, int rent, Model mode) {
        Tenant_entity tenant = tencon.findById(id).orElse(null);
            tenant.setName(name);
            tenant.setContact(contact);
            tenant.setPropertyname(propertyname);
            tenant.setRent(rent);
            tencon.save(tenant);
            mode.addAttribute("msg", "Tenant details updated successfully!");
        List<Tenant_entity> items = (List<Tenant_entity>)tencon.findAll();
        mode.addAttribute("items", tencon.findAll());
        return "Tenant_list";
    }

}
