package com.maitri.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.maitri.model.Role;
@EnableJpaRepositories
@Repository
/*
 * Interface that extends the JPAReposiry for Role table.JPA Repository has all the CRUD methods.
 */
public interface RoleDao extends JpaRepository<Role, String> {

}
