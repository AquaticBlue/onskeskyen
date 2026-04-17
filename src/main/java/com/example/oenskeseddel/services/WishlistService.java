package com.example.oenskeseddel.services;

import com.example.oenskeseddel.models.User;
import com.example.oenskeseddel.models.Wishlist;
import com.example.oenskeseddel.repositories.UserRepository;
import com.example.oenskeseddel.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    public WishlistService(WishlistRepository wishlistRepository, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
    }

    public Wishlist createWishlist(long userId, String name) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Bruger ikke fundet"));

        return wishlistRepository.save(new Wishlist(user, name));
    }

    public List<Wishlist> getWishlistsForUser(Long userId) {
        return wishlistRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
    }

    public Wishlist findById(Integer wishlistId) {
        return wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Ønskeliste ikke fundet"));
    }

    //Creation of the sharing URL for sharing wishlists.
    public String generateShareLink(Integer wishlistId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        return "https://onskesedlen-gphsgcahg3d6eae6.westeurope-01.azurewebsites.net/wishlist/"
                + wishlist.getUser().getUsername()
                + "/share/"
                + wishlist.getWishlistId();
    }

    public Wishlist getWishlistByUsernameAndId(String username, Integer wishlistId) {
        return wishlistRepository.findByUserUsernameAndWishlistId(username, wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));
    }

}
