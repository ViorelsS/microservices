package io.viorelss.movie_catalog_service.controller;

import io.viorelss.movie_catalog_service.models.CatalogItem;
import io.viorelss.movie_catalog_service.models.Movie;
import io.viorelss.movie_catalog_service.models.Rating;
import io.viorelss.movie_catalog_service.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratings/users/" +userId, UserRating.class);

        return ratings.getUserRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("localhost:8082/movies/" + rating.getMovieId(), Movie.class);

            return new CatalogItem(movie.getName(), "Desc", rating.getRating());
        }).collect(Collectors.toList());

    }
}
