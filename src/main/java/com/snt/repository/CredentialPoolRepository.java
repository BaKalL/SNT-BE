package com.snt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snt.entity.CredentialPool;

@Repository
public interface CredentialPoolRepository extends JpaRepository<CredentialPool, Long>{
	
	public List<CredentialPool> findByUsernameAndPassword (String username, String password);

}
