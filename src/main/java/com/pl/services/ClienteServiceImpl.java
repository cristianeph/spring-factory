package com.pl.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.pl.model.ClienteModel;
import com.pl.model.ClienteModel_;
import com.pl.repository.ClienteRepository;

@Component("clienteService")
public class ClienteServiceImpl implements ClienteService{

	@PersistenceContext
	private EntityManager entityManager;
	
	private final ClienteRepository clienteRepository;
	
	public ClienteServiceImpl(ClienteRepository clienteRepository){
		
		this.clienteRepository = clienteRepository;
		
	}

	@Override
	public ClienteModel save(ClienteModel cliente) {
		
		return this.clienteRepository.save(cliente);
		
	}

	@Override
	public ClienteModel deleteById(ClienteModel cliente) {
		
		return this.clienteRepository.deleteById(cliente.getId());
		
	}

	@Override
	public ClienteModel findById(ClienteModel cliente) {
		
		return this.clienteRepository.findById(cliente.getId());
		
	}

	@Override
	public Page<ClienteModel> findAll(ClienteModel cliente) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ClienteModel> query = builder.createQuery(ClienteModel.class);
		Root<ClienteModel> root = query.from(ClienteModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(cliente.getId() != 0){
			System.out.println(cliente.getId());
			predicates.add(builder.equal(root.get(ClienteModel_.id), cliente.getId()));
		}
		
		if(cliente.getRazonsocial() != ""){
			System.out.println(cliente.getRazonsocial());
			predicates.add(builder.like(root.get(ClienteModel_.razonsocial), "%" + cliente.getRazonsocial() + "%"));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + cliente.getRows());
			
			if(cliente.getRows() > 0){
				return (Page<ClienteModel>) this.clienteRepository.findAll(new PageRequest(0, cliente.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<ClienteModel>) this.clienteRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<ClienteModel> clientes = new PageImpl<ClienteModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return clientes;
			
		}
		
	}

	@Override
	public Page<ClienteModel> getAll(PageRequest page) {
		return this.clienteRepository.findAll(page);
	}

	@Override
	public ClienteModel getById(Integer id) {
		return this.clienteRepository.findById(id);
	}

}
