package com.maitri.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maitri.model.User;
/*
 * Interface that extends the JPAReposiry for User table.JPA Repository has all the CRUD methods.
 */
@Repository
public interface UserDao extends JpaRepository<User,String> {

}
