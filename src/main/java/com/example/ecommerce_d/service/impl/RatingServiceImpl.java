package com.example.ecommerce_d.service.impl;

import com.example.ecommerce_d.model.Rating;
import com.example.ecommerce_d.model.User;
import com.example.ecommerce_d.repository.RatingRepository;
import com.example.ecommerce_d.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating save(Rating rating, User user) {
        return null;
    }
}
