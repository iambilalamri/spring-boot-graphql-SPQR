package com.amrib.spqr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amrib.spqr.domain.Actor;
import com.amrib.spqr.domain.Film;
import com.amrib.spqr.repository.FilmRepository;

import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class FilmService {

	@Autowired
	FilmRepository filmRepository;

	@GraphQLQuery
	public Film getFilm(@GraphQLContext Actor actor) {
		return filmRepository.findById(actor.getFilmId()).get();
	}

}
