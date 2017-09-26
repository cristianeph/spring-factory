package com.pl.repository;

import com.pl.model.MermaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface MermaRepository extends Repository<MermaModel, Long> {
    MermaModel save(MermaModel merma);
    MermaModel deleteById(Integer id);
    MermaModel findById(Integer id);
    Page<MermaModel> findAll(Pageable page);
}
