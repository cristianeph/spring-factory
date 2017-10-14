package com.pl.services.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pl.services.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.pl.model.PedidoModel;
import com.pl.model.metamodel.PedidoModel_;
import com.pl.repository.PedidoRepository;

@Component("pedidoService")
public class PedidoServiceImpl implements PedidoService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private final PedidoRepository pedidoRepository;
	
	public PedidoServiceImpl(PedidoRepository pedidoRepository){
		
		this.pedidoRepository = pedidoRepository;
		
	}

	@Override
	public PedidoModel save(PedidoModel pedido) {
		
		return this.pedidoRepository.save(pedido);
		
	}

	@Override
	public PedidoModel deleteById(PedidoModel pedido) {
		
		return this.pedidoRepository.deleteById(pedido.getId());
		
	}

	@Override
	public PedidoModel findById(PedidoModel pedido) {
		
		return this.pedidoRepository.findById(pedido.getId());
		
	}

	@Override
	public Page<PedidoModel> findAll(PedidoModel pedido) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PedidoModel> query = builder.createQuery(PedidoModel.class);
		Root<PedidoModel> root = query.from(PedidoModel.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(pedido.getId() != 0){
			System.out.println(pedido.getId());
			predicates.add(builder.equal(root.get(PedidoModel_.id), pedido.getId()));
		}
		
		if(pedido.getCodigo() != ""){
			System.out.println(pedido.getCodigo());
			predicates.add(builder.like(root.get(PedidoModel_.codigo), "%" + pedido.getCodigo() + "%"));
		}
		
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		
		query.where(builder.and(predicates.toArray(predicatesArray)));
		query.orderBy(builder.desc(root.get("id")));
		
		if(predicates.size() == 0){
			
			System.out.println("NO se mandaron parametros para la busqueda");
			System.out.println("CANTIDAD de resultados: " + pedido.getRows());
			
			if(pedido.getRows() > 0){
				return (Page<PedidoModel>) this.pedidoRepository.findAll(new PageRequest(0, pedido.getRows(), Direction.DESC, "id"));
			}else{
				return (Page<PedidoModel>) this.pedidoRepository.findAll(new PageRequest(0, Integer.MAX_VALUE, Direction.DESC, "id"));
			}
			
		}else{
			
			System.out.println("SI se mandaron parametros para la busqueda");
			
			Page<PedidoModel> pedidos = new PageImpl<PedidoModel>(entityManager.createQuery(query.select(root)).getResultList()); 
			return pedidos;
			
		}
		
	}

	@Override
	public Page<PedidoModel> findAllBetweenFecha(Date start, Date end) {
		
		return this.pedidoRepository.findAllBetweenFecha(new PageRequest(0, Integer.MAX_VALUE), start, end);
		
	}

	@Override
	public Page<PedidoModel> getAll(PageRequest page) {
		return this.pedidoRepository.findAll(page);
	}

}
