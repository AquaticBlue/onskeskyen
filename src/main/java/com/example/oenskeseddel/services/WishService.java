package com.example.oenskeseddel.services;

import com.example.oenskeseddel.models.Wish;
import com.example.oenskeseddel.models.Wishlist;
import com.example.oenskeseddel.repositories.WishRepository;
import com.example.oenskeseddel.repositories.WishlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WishService {

    private final WishRepository wishRepository;
    private final WishlistRepository wishlistRepository;

    public WishService(WishRepository wishRepository, WishlistRepository wishlistRepository) {
        this.wishRepository = wishRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Transactional
    public Wish createWish(Integer wishlistId, String name) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist ikke fundet: " + wishlistId));
        Wish wish = new Wish(wishlist, name);

        wishlist.addWish(wish);
        wishlistRepository.save(wishlist);
        return wish;
    }
}
