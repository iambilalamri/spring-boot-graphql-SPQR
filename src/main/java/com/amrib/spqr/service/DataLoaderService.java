package com.amrib.spqr.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amrib.spqr.domain.Actor;
import com.amrib.spqr.domain.Film;
import com.amrib.spqr.repository.ActorRepository;
import com.amrib.spqr.repository.FilmRepository;

@Service
public class DataLoaderService {

	@Autowired
	FilmRepository filmRepository;

	@Autowired
	ActorRepository actorRepository;

	@PostConstruct
	public void loadData() {
		String[] actors = { "Shahrukh khan", "Tom Cruise", "Rami Malek", "Joaquin phoenix" };

		Map<String, String> films = new HashMap<String, String>();
		films.put("Shahrukh khan", "My name is khan");
		films.put("Tom Cruise", "Jack Reacher");
		films.put("Rami Malek", "Mr Robot");
		films.put("joaquin phoenix", "Joker");

		for (String actorName : actors) {
			String[] names = actorName.split(" ");

			Date dateOfBirth = DataLoaderService.between(new Date(1960, 01, 01), new Date(1980, 01, 01));
			Date dateOfLaunch = DataLoaderService.between(new Date(1990, 01, 01), new Date(2000, 01, 01));
			Actor actor = new Actor(names[0], names[1], dateOfBirth, "Mumbai India");
			actorRepository.save(actor);
			Film film = new Film(films.get(actorName), dateOfLaunch, Arrays.asList(actor));
			filmRepository.save(film);
		}

	}

	public static Date between(Date startInclusive, Date endExclusive) {
		long startMillis = startInclusive.getTime();
		long endMillis = endExclusive.getTime();
		long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(startMillis, endMillis);

		return new Date(randomMillisSinceEpoch);
	}
}
