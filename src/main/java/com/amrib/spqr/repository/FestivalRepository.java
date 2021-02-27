package com.amrib.spqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amrib.spqr.domain.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Integer> {

}
