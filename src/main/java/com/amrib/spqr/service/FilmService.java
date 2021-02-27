package com.amrib.spqr.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amrib.spqr.domain.Actor;
import com.amrib.spqr.domain.Film;
import com.amrib.spqr.repository.ActorRepository;
import com.amrib.spqr.repository.FilmRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
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
	public Film createFilm(@GraphQLArgument(name = "film") Film film, @GraphQLArgument(name = "id") Integer actorId) {
		System.out.println(film.toString());
		Actor actor = actorRepository.findById(actorId).get();
		Film newFilm = new Film(film.getName(), new Date(), Arrays.asList(actor));
		filmRepository.save(newFilm);
		return newFilm;
	}
}
