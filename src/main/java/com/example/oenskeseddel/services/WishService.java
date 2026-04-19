package com.example.oenskeseddel.services;

import com.example.oenskeseddel.models.Wish;
import com.example.oenskeseddel.models.Wishlist;
import com.example.oenskeseddel.repositories.WishRepository;
import com.example.oenskeseddel.repositories.WishlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;

@Service
public class WishService {

    private final WishRepository wishRepository;
    private final WishlistRepository wishlistRepository;

    public WishService(WishRepository wishRepository, WishlistRepository wishlistRepository) {
        this.wishRepository = wishRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Transactional
    public Wish createWish(Integer wishlistId, String name, String brand,
                           String description, BigDecimal price, String link,
                           boolean favorite) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist ikke fundet: " + wishlistId));

        Wish wish = new Wish(wishlist, name);
        wish.setBrand(brand);
        wish.setDescription(description);
        wish.setPrice(price);
        wish.setLink(link);
        wish.setFavorite(favorite);

        wishlist.addWish(wish);
        wishlistRepository.save(wishlist);
        return wish;
    }

    @Transactional
    public Wish toggleFavorite(Integer wishId) {
        Wish wish = wishRepository.findById(wishId)
                .orElseThrow(() -> new EntityNotFoundException("Wish ikke fundet: " + wishId));
        wish.setFavorite(!wish.isFavorite());
        return wishRepository.save(wish);
    }
}
