package com.amrib.spqr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amrib.spqr.domain.Actor;
import com.amrib.spqr.domain.Film;
import com.amrib.spqr.repository.ActorRepository;
import com.amrib.spqr.repository.FilmRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;

@Service
@GraphQLApi
@RequiredArgsConstructor
public class FilmService {

	private final FilmRepository filmRepository;
	private final ActorRepository actorRepository;

	@GraphQLQuery
	public List<Film> getAllFilms() {
		return filmRepository.findAll();
	}

	@GraphQLQuery
	public Film getFilmById(@GraphQLArgument(name = "id") Integer id) {
		return filmRepository.findById(id).get();
	}

	@GraphQLMutation
	public void deleteFilm(@GraphQLArgument(name = "id") Integer id) {
		filmRepository.deleteById(id);
	}

	@GraphQLMutation
	public Film createFilm(Film newFilm) {
		return filmRepository.save(newFilm);
	}
}
