package com.pl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.MaquinaModel;

public interface MaquinaRepository extends Repository<MaquinaModel, Long>{
	
	MaquinaModel save(MaquinaModel maquina);
	MaquinaModel deleteById(Integer id);
	MaquinaModel findById(Integer id);
	Page<MaquinaModel> findAll(Pageable page);

}
