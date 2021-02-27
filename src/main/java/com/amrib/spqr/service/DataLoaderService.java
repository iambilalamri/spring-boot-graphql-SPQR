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
import com.amrib.spqr.repository.FestivalRepository;
import com.amrib.spqr.repository.FilmRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataLoaderService {

	private final FilmRepository filmRepository;
	private final ActorRepository actorRepository;
	private final FestivalRepository festivalRepository;

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

//	@PostConstruct
//	public void loadData() {
//		Actor actor1 = new Actor(1, "Rami", "Malek", new Date(), "LA, USA");
//		actorRepository.save(actor1);
//		Actor actor2 = new Actor(2, "Tom", "Cruise", new Date(), "LA, USA");
//		Actor actor3 = new Actor(3, "Amir", "Khan", new Date(), "LA, USA");
//		Actor actor4 = new Actor(4, "Jeniffer", "Aniston", new Date(), "LA, USA");
//		//actorRepository.saveAll(Arrays.asList(actor1, actor2, actor3, actor4));
//
//		Film film1 = new Film(1, "Mr Robot", new Date(), Arrays.asList(actor1, actor2));
//		Film film2 = new Film(2, "Love story", new Date(), Arrays.asList(actor3, actor4));
//		Film film3 = new Film(3, "Joker", new Date(), Arrays.asList(actor1, actor4));
//		//filmRepository.saveAll(Arrays.asList(film1, film2, film3));
//
//		Festival festival = new Festival(1, "Oscar", new Date(), Arrays.asList(film1, film2, film3));
//		//festivalRepository.save(festival);
//	}
}
