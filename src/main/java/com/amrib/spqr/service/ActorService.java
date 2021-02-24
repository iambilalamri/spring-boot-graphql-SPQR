package com.amrib.spqr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amrib.spqr.domain.Actor;
import com.amrib.spqr.repository.ActorRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class ActorService {

	@Autowired
	private ActorRepository actorRepository;

	@GraphQLQuery
	public Actor getActorById(@GraphQLArgument(name = "id") Integer id) {
		return actorRepository.findById(id).get();
	}

	@GraphQLQuery
	public List<Actor> getAllActors() {
		return actorRepository.findAll();
	}
}
