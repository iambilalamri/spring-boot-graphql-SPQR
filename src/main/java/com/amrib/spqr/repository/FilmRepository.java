package com.amrib.spqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amrib.spqr.domain.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

}
