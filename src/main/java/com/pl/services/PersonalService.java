package com.pl.services;

import org.springframework.data.domain.Page;

import com.pl.model.PersonalModel;

public interface PersonalService {

	PersonalModel save(PersonalModel personal);
	PersonalModel deleteById(PersonalModel personal);
	PersonalModel findById(PersonalModel personal);
	Page<PersonalModel> findAll(PersonalModel personal);

}
