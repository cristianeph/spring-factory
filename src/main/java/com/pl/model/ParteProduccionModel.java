package com.pl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(
	name="parteproduccion"
)
public class ParteProduccionModel {
	
	@Transient
	private Integer rows;
	@Transient
	private Integer resume;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String codigo;
	private Date fecha;
    private Date inicio;
    private Date fin;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idmaquina")
	private MaquinaModel maquina;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idactividad")
	private ActividadModel actividad;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parteproduccion", orphanRemoval=true, cascade = CascadeType.ALL)
	private Collection<OrdenTrabajoModel> ordenTrabajos = new ArrayList<>();
	
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public MaquinaModel getMaquina() {
		return maquina;
	}

	public void setMaquina(MaquinaModel maquina) {
		this.maquina = maquina;
	}

	public ActividadModel getActividad() {
		return actividad;
	}

	public void setActividad(ActividadModel actividad) {
		this.actividad = actividad;
	}

	public Collection<OrdenTrabajoModel> getOrdenTrabajos() {
		return ordenTrabajos;
	}

	public void setOrdenTrabajos(Collection<OrdenTrabajoModel> ordenTrabajos) {
		this.ordenTrabajos = ordenTrabajos;
	}
}
