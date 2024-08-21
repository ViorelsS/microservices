package io.viorelss.rating_data_service.controllers;

import io.viorelss.rating_data_service.models.Rating;
import io.viorelss.rating_data_service.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("users/{userId}")
    public UserRating getRatings(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(new Rating("1234", 4), new Rating("5678", 3));

        UserRating userRating = new UserRating();
        userRating.setUserRatings(ratings);

        return userRating;


    }


}
