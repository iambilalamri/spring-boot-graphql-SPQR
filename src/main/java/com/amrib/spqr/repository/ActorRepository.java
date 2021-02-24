package com.amrib.spqr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amrib.spqr.domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	public Actor findActorByFirstnameLike(String name);

}
