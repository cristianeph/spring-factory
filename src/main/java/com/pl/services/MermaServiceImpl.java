package com.pl.services;

import com.pl.model.MermaModel;
import com.pl.model.MermaTrabajoModel;
import com.pl.model.OrdenTrabajoModel;
import com.pl.repository.MermaRepository;
import com.pl.repository.TrabajoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component("mermaService")
public class MermaServiceImpl implements MermaService {
    @PersistenceContext
    private EntityManager entityManager;
    private final MermaRepository mermaRepository;
    private final TrabajoRepository trabajoRepository;

    public MermaServiceImpl(MermaRepository mermaRepository, TrabajoRepository trabajoRepository) {
        this.mermaRepository = mermaRepository;
        this.trabajoRepository = trabajoRepository;
    }

    @Override
    public MermaModel save(MermaModel merma) {
        return this.mermaRepository.save(merma);
    }

    @Override
    public MermaTrabajoModel saveComposite(MermaTrabajoModel mermaTrabajo) {
        MermaModel merma = this.mermaRepository.save(mermaTrabajo.getMerma());
        OrdenTrabajoModel trabajo = this.trabajoRepository.findById(mermaTrabajo.getTrabajo().getId());
        trabajo.setMerma(merma);
        this.trabajoRepository.save(trabajo);
        mermaTrabajo.setMerma(merma);
        mermaTrabajo.setTrabajo(trabajo);
        return mermaTrabajo;
    }

    @Override
    public MermaModel getById(Integer id) {
        return this.mermaRepository.findById(id);
    }

    @Override
    public Page<MermaModel> getAll(PageRequest page) {
        return this.mermaRepository.findAll(page);
    }
}
