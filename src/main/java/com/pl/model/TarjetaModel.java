package com.pl.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(
	name="tarjeta"
)
public class TarjetaModel {
	
	@Transient
	private Integer rows;
	@Transient
	private Integer resume;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tarjeta", orphanRemoval=true, cascade = CascadeType.ALL)
	private Collection<TrabajoModel> tarjetaTrabajos = new ArrayList<TrabajoModel>();
	
	
	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getResume() {
		return resume;
	}

	public void setResume(Integer resume) {
		this.resume = resume;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<TrabajoModel> getTarjetaTrabajos() {
		return tarjetaTrabajos;
	}

	public void setTarjetaTrabajos(Collection<TrabajoModel> tarjetaTrabajos) {
		this.tarjetaTrabajos = tarjetaTrabajos;
	}
	
	public void clearTrabajoModel(){
		for (TrabajoModel trabajo : tarjetaTrabajos) {
			trabajo.releaseTrabajoModel();
		}
		tarjetaTrabajos.clear();
	}

}
