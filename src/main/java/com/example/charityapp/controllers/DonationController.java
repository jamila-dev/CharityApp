package com.example.charityapp.controllers;

import com.example.charityapp.dtos.DonationDTO;
import com.example.charityapp.services.DonationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/donations")
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/{campaignId}")
    public String donationForm(@PathVariable Long campaignId, Model model) {
        DonationDTO donationDTO = new DonationDTO();
        donationDTO.setCampaignId(campaignId);
        model.addAttribute("donationDTO", donationDTO);
        return "donation";
    }

    @PostMapping
    public String makeDonation(@ModelAttribute @Valid DonationDTO donationDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        donationService.makeDonation(donationDTO, username);
        return "redirect:/campaigns";
    }
}

