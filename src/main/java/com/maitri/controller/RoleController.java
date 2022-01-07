package com.maitri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maitri.model.Role;
import com.maitri.service.RoleService;
/*
 * This controller implements the function that register the new Role.
 */
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    //Whenever createNewRole rest endpoint is hit,then it creates new Role.
    
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }
}
