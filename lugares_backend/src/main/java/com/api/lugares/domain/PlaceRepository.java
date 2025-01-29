package com.api.lugares.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface PlaceRepository extends ReactiveCrudRepository<Place, Long> {

    Flux<Place> findByNameContainingIgnoreCase(String name);
}
