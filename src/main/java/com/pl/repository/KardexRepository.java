package com.pl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.pl.model.KardexModel;
import com.pl.model.KardexInsumoModel;
import com.pl.model.KardexRelacionModel;

public interface KardexRepository extends Repository<KardexModel, Long>{
	
	KardexModel save(KardexModel kardex);
	KardexModel findById(Integer id);
	KardexModel findByIdInsumo(Integer id);
	
	@Query("SELECT new com.pl.model.KardexRelacionModel(sum(k.stock), k.relacion) FROM KardexModel k WHERE k.relacion = :relacion GROUP BY k.relacion")
	Page<KardexRelacionModel> findKardexByRelacion(Pageable page, @Param("relacion") Integer relacion);
	
	@Query("SELECT new com.pl.model.KardexInsumoModel(i.id, i.descripcion, sum(k.stock)) FROM KardexModel k, InsumoModel i WHERE k.idInsumo = i.id AND k.relacion = :relacion GROUP BY i.id, i.descripcion")
	Page<KardexInsumoModel> findStockByRelacion(Pageable page, @Param("relacion") Integer relacion);

}
