package com.nng.gps.repository;

import com.nng.gps.domain.GPS;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGPSRepository extends PagingAndSortingRepository<GPS, Long> {
    public GPS getById(Long id);
}
