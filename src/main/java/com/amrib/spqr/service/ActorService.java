package com.amrib.spqr.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amrib.spqr.domain.Actor;
import com.amrib.spqr.domain.AddressInputType;
import com.amrib.spqr.domain.Film;
import com.amrib.spqr.repository.ActorRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Service
@GraphQLApi
public class ActorService {

	@Autowired
	private ActorRepository actorRepository;

	private ConcurrentHashMap<Integer, FluxSink<Actor>> subscribers = new ConcurrentHashMap<>();

	@GraphQLQuery
	public Actor getActorById(@GraphQLArgument(name = "id") Integer id) {
		return actorRepository.findById(id).get();
	}

	@GraphQLQuery
	public List<Actor> getActor(@GraphQLContext Film film) {
		return actorRepository.findAll();
	}

	@GraphQLQuery
	public List<Actor> getAllActors() {
		return actorRepository.findAll();
	}

	@GraphQLMutation
	public Actor updateAddress(@GraphQLArgument(name = "id") Integer id,
			@GraphQLArgument(name = "address") String address) {
		Actor actor = actorRepository.findById(id).orElse(null);
		actor.setAddress(address);
		actorRepository.save(actor);
		if (subscribers.get(id) != null) {
			subscribers.get(id).next(actor);
		}
		return actor;
	}

	@GraphQLMutation
	public Actor updateAddressByInputType(@GraphQLInputField(name = "addressInput") AddressInputType input) {
		Actor actor = actorRepository.findById(input.getId()).orElse(null);
		actor.setAddress(input.getAddress());
		actorRepository.save(actor);
		if (subscribers.get(input.getId()) != null) {
			subscribers.get(input.getId()).next(actor);
		}
		return actor;
	}

	@GraphQLSubscription
	public Publisher<Actor> onActorUpdate(@GraphQLArgument(name = "actorId") Integer actorId) {
		return Flux.create(
				subscriber -> subscribers.put(actorId,
						subscriber.onDispose(() -> subscribers.remove(actorId, subscriber))),
				FluxSink.OverflowStrategy.LATEST);
	}
}
