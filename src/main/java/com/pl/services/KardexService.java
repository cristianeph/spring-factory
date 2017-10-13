package com.pl.services;

import com.pl.model.InsumoKardexProjection;
import org.springframework.data.domain.Page;

import com.pl.model.KardexInsumoModel;
import com.pl.model.KardexModel;
import com.pl.model.KardexRelacionModel;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface KardexService {
	
	KardexModel save(KardexModel kardex);
	KardexModel findById(KardexModel kardex);
	KardexModel findByIdInsumo(Integer id);
	Page<KardexModel> getAll(PageRequest page);
	Page<InsumoKardexProjection> getAsParameters(PageRequest page);
	Page<KardexRelacionModel> sumKardexByRelacion(Integer relacion);
	Page<KardexInsumoModel> sumStockByRelacion(Integer relacion);

}
