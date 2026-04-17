package com.example.oenskeseddel.repositories;

import com.example.oenskeseddel.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findByUserUserIdOrderByCreatedAtDesc(Long userId);

    Optional<Wishlist> findByUserUsernameAndWishlistId(String username, Integer wishlistId);
}
