package com.amrib.spqr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amrib.spqr.domain.Festival;
import com.amrib.spqr.repository.FestivalRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.RequiredArgsConstructor;

@Service
@GraphQLApi
@RequiredArgsConstructor
public class FestivalService {

	private final FestivalRepository festivalRepository;

	@GraphQLQuery
	public List<Festival> getAllFestivals() {
		return festivalRepository.findAll();
	}

	@GraphQLQuery
	public Festival getFestivalById(@GraphQLArgument(name = "id") Integer id) {
		return festivalRepository.findById(id).get();
	}
}
