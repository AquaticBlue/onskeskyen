package com.example.oenskeseddel.controllers;

import com.example.oenskeseddel.models.Wish;
import com.example.oenskeseddel.models.Wishlist;
import com.example.oenskeseddel.services.WishService;
import com.example.oenskeseddel.services.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
    public String createWish(@PathVariable Integer wishlistId,
                             @RequestParam String name,
                             @RequestParam(required = false) String brand,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) BigDecimal price,
                             @RequestParam(required = false) String link,
                             @RequestParam(defaultValue = "false") boolean is_favorite) {
        wishService.createWish(wishlistId, name, brand, description, price, link, is_favorite);
        return "redirect:/api/wishlists/" + wishlistId;
    }

    @GetMapping("/{wishlistId}")
    public String getWishlistPage(@PathVariable Integer wishlistId, Model model) {
        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);
        model.addAttribute("wishlist", wishlist);
        return "wishlist";
    }

    @PostMapping("/{wishlistId}/wishes/{wishId}/favorite")
    public String toggleFavorite(@PathVariable Integer wishlistId,
                                 @PathVariable Integer wishId) {
        wishService.toggleFavorite(wishId);
        return "redirect:/api/wishlists/" + wishlistId;
    }
}
