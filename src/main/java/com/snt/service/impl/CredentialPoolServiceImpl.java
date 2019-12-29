package com.snt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snt.entity.CredentialPool;
import com.snt.repository.CredentialPoolRepository;
import com.snt.service.CredentialPoolService;

@Service
public class CredentialPoolServiceImpl implements CredentialPoolService{

	@Autowired
	private CredentialPoolRepository credentialPoolRepository;

	@Override
	public CredentialPool getUser(CredentialPool credential) {
		List<CredentialPool> records = this.credentialPoolRepository.findByUsernameAndPassword(credential.getUsername(), credential.getPassword());
		
		if (records.isEmpty())
		{
			return null;
		}
		
		return records.get(0);
	}

}
