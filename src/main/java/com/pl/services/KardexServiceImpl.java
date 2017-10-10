package com.pl.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.pl.model.KardexInsumoModel;
import com.pl.model.KardexModel;
import com.pl.model.KardexRelacionModel;
import com.pl.repository.KardexRepository;

@Component("kardexService")
public class KardexServiceImpl implements KardexService{
	
	private final KardexRepository kardexRepository;
	
	public KardexServiceImpl(KardexRepository kardexRepository){
		
		this.kardexRepository = kardexRepository;
		
	}

	@Override
	public KardexModel save(KardexModel kardex) {
		
		return this.kardexRepository.save(kardex);
		
	}

	@Override
	public KardexModel findById(KardexModel kardex) {
		
		return this.kardexRepository.findById(kardex.getId());
		
	}

	@Override
	public KardexModel findByIdInsumo(Integer id) {
		
		return this.kardexRepository.findByIdInsumo(id);
		
	}

	@Override
	public Page<KardexModel> getAll(PageRequest page) {
		return this.kardexRepository.findAll(page);
	}

	@Override
	public Page<KardexRelacionModel> sumKardexByRelacion(Integer relacion) {
		System.out.println("entro: " + relacion);
		return this.kardexRepository.findKardexByRelacion(new PageRequest(0, Integer.MAX_VALUE), relacion);
		
	}

	@Override
	public Page<KardexInsumoModel> sumStockByRelacion(Integer relacion) {
		
		return this.kardexRepository.findStockByRelacion(new PageRequest(0, Integer.MAX_VALUE), relacion);
		
	}

}
