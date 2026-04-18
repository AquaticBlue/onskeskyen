package com.example.oenskeseddel.controllers;

import com.example.oenskeseddel.services.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Frontpage {

    private final WishlistService wishlistService;

    public Frontpage(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("myWishlists", wishlistService.getWishlistsForUser(1L));
        model.addAttribute("sharedWishlists", List.of());
        return "index";
    }
}
