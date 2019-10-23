package com.nng.gps.repository;

import com.nng.gps.domain.GPS;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface GPSRepository extends CrudRepository<GPS, String> {

}
