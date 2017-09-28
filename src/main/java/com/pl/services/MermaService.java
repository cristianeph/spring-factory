package com.pl.services;

import com.pl.model.MermaModel;
import com.pl.model.MermaTrabajoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface MermaService {
    MermaModel save(MermaModel merma);
    MermaTrabajoModel saveComposite(MermaTrabajoModel mermaTrabajo);
    MermaModel getById(Integer id);
    Page<MermaModel> getAll(PageRequest page);
}
