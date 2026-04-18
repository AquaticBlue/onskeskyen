package com.example.oenskeseddel.controllers;

import com.example.oenskeseddel.services.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    private final WishlistService wishlistService;

    public PageController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/createWishlist")
    public String showCreateWishlistPage() {
        return "opret";
    }


    @PostMapping("/createWishlist")
    public String createWishlist(@RequestParam Integer userId, @RequestParam String name) {
        wishlistService.createWishlist(userId, name);
        return "redirect:/";
    }


    @GetMapping("/sharedWishlists")
    public String showSharedWishlistsPage(Model model) {
        model.addAttribute("sharedWishlists", wishlistService.getWishlistsForUser(1L));
        return "sharedWishlist";
    }

    @GetMapping("/add-wish/{wishlistId}")
    public String showAddWishPage(@PathVariable Integer wishlistId, Model model) {
        model.addAttribute("wishlistId", wishlistId);
        return "add-wish";
    }
}
