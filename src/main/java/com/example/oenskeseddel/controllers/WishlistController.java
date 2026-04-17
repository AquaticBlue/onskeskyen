package com.example.oenskeseddel.controllers;

import com.example.oenskeseddel.models.Wish;
import com.example.oenskeseddel.models.Wishlist;
import com.example.oenskeseddel.services.WishService;
import com.example.oenskeseddel.services.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;
    private final WishService wishService;

    public WishlistController(WishlistService wishlistService, WishService wishService) {
        this.wishlistService = wishlistService;
        this.wishService = wishService;
    }

    @ResponseBody
    @PostMapping("/create")
    public Wishlist createWishlist(@RequestParam Integer userId,
                                   @RequestParam String name) {
        return wishlistService.createWishlist(userId, name);
    }

    @PostMapping("/{wishlistId}/wishes")
    public Wish createWish(@PathVariable Integer wishlistId, @RequestParam String name) {
        return wishService.createWish(wishlistId, name);
    }

    @GetMapping("/{wishlistId}")
    public String getWishlistPage(@PathVariable Integer wishlistId, Model model) {
        model.addAttribute("wishlistId", wishlistId);
        return "wishlist";
    }
}
