package com.example.ecommerce_d.service;

import com.example.ecommerce_d.model.Rating;
import com.example.ecommerce_d.model.User;

public interface RatingService {
    public Rating save(Rating rating, User user);
}
