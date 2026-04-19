package com.example.oenskeseddel.controllers;


import com.example.oenskeseddel.models.Wishlist;
import com.example.oenskeseddel.services.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class ShareController {

    private final WishlistService wishlistService;

    public ShareController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{username}/share/{wishlistId}")
    public String showSharedWishlist(
            @PathVariable String username,
            @PathVariable Integer wishlistId,
            Model model) {

        Wishlist wishlist = wishlistService.getWishlistByUsernameAndId(username, wishlistId);

        model.addAttribute("wishlist", wishlist);
        model.addAttribute("username", username);

        return "shared";
    }

    @GetMapping("/{wishlistId}/share-link")
    public String showShareLinkPage(@PathVariable Integer wishlistId, Model model) {
        String shareLink = wishlistService.generateShareLink(wishlistId);

        model.addAttribute("shareLink", shareLink);
        model.addAttribute("wishlistId", wishlistId);

        return "share-link";
    }

}
