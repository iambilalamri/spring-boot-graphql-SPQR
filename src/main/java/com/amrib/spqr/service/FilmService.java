package com.amrib.spqr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amrib.spqr.domain.Actor;
import com.amrib.spqr.domain.Film;
import com.amrib.spqr.repository.FilmRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class FilmService {

	@Autowired
	FilmRepository filmRepository;
	
	@GraphQLQuery
	public List<Film> getAllFilms() {
		return filmRepository.findAll();
	}
	
	@GraphQLQuery
	public Film getFilmById(@GraphQLArgument(name = "id") Integer id) {
		return filmRepository.findById(id).get();
	}

}
