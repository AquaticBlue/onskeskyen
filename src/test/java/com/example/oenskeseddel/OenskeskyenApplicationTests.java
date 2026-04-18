package com.example.oenskeseddel;

import com.example.oenskeseddel.models.User;
import com.example.oenskeseddel.models.Wishlist;
import com.example.oenskeseddel.repositories.UserRepository;
import com.example.oenskeseddel.repositories.WishlistRepository;
import com.example.oenskeseddel.services.WishlistService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OenskeskyenApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldCreateWishlistForUserId() {
        // Arrange
        WishlistRepository wishlistRepo = mock(WishlistRepository.class);
        UserRepository userRepo = mock(UserRepository.class);

        WishlistService service = new WishlistService(wishlistRepo, userRepo);

        Long userId = 1L;
        User testUser = new User();
        testUser.setUserId(userId);
        testUser.setUsername("testuser");
        testUser.setEmail("testuser@mail.com");
        testUser.setPassword("1234");

        when(userRepo.findById(userId)).thenReturn(Optional.of(testUser));

        // Act
        service.createWishlist(userId, "Fødselsdagsgaver");

        // Assert
        verify(userRepo).findById(userId);
        verify(wishlistRepo).save(any(Wishlist.class));
    }

    @Test
    @Transactional
    void shouldSaveAndRetrieveWishlist() {
        // Arrange
        User user = new User();
        user.setUsername("integrationuser");
        user.setEmail("integration@mail.com");
        user.setPassword("1234");
        user.setName("Integration User");
        userRepository.save(user);

        // Act
        Wishlist wishlist = new Wishlist(user, "Integrationsliste");
        wishlistRepository.save(wishlist);

        // Assert
        List<Wishlist> wishlists = wishlistRepository.findByUserUserIdOrderByCreatedAtDesc(user.getUserId());
        assertFalse(wishlists.isEmpty());
        assertEquals("Integrationsliste", wishlists.get(0).getName());
    }
}
