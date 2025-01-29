package com.api.lugares.service;
import com.api.lugares.api.PlaceRequest;
import com.api.lugares.api.PlaceResponse;
import com.api.lugares.domain.Place;
import com.api.lugares.domain.PlaceMapper;
import com.api.lugares.domain.PlaceRepository;
import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final Slugify slg;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.slg = Slugify.builder().build();
    }


    @Transactional
    public Mono<Place> create(PlaceRequest placeRequest){
        Place place = new Place(
                null,
                placeRequest.name(),
                slg.slugify(placeRequest.name()),
                placeRequest.state(),
                null,
                null
                );
        return placeRepository.save(place);
    }

    @Transactional
    public Mono<PlaceResponse> detail(Long id){
        return placeRepository.findById(id)
                .map(PlaceMapper::fromPlaceToResponse);
    }

    @Transactional
    public Flux<PlaceResponse> listAllByName(String name) {
        return placeRepository.findByNameContainingIgnoreCase(name)
                .map(PlaceMapper::fromPlaceToResponse);
    }

    @Transactional
    public Flux<PlaceResponse> listAll() {
        return placeRepository.findAll()
                .map(PlaceMapper::fromPlaceToResponse);
    }


    @Transactional
    public Mono<Place> edit(Long id, PlaceRequest placeRequest){
        return placeRepository.findById(id)
                .flatMap(
                        place -> {
                            var updatedPlace = PlaceMapper.updatePlaceFromDTO(placeRequest, place);
                            placeRepository.delete(place);
                            return placeRepository.save(updatedPlace);
                        });
    }

    @Transactional
    public Mono<Void> delete(Long id){
        return placeRepository.deleteById(id).then();
    }
}
