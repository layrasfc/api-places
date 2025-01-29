package com.api.lugares.domain;

import com.api.lugares.api.PlaceRequest;
import com.api.lugares.api.PlaceResponse;
import com.github.slugify.Slugify;

public class PlaceMapper {
    private static Slugify slg;

    static {
        slg = Slugify.builder().build();
    }

    public static PlaceResponse fromPlaceToResponse(Place place){
        return new PlaceResponse(
                place.name(),
                place.slug(),
                place.state(),
                place.createdAt(),
                place.updatedAt()
        );
    }

    public static Place updatePlaceFromDTO(PlaceRequest placeRequest, Place place){
        return new Place(
                place.id(),
                placeRequest.name() != null ? placeRequest.name() : place.name(),
                placeRequest.name() != null ? slg.slugify(placeRequest.name()) : place.slug(),
                placeRequest.state() != null ? placeRequest.state() : place.state(),
                place.createdAt(),
                null
        );
    }
}