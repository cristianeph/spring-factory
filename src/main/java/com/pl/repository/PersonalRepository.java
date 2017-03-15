package com.pl.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.PersonalModel;

public interface PersonalRepository extends Repository<PersonalModel, Long>{
	
	PersonalModel save(PersonalModel personal);
	PersonalModel deleteById(Integer id);
	PersonalModel findById(Integer id);
	Iterable<PersonalModel> findAll(Pageable page);

}
