package com.example.charityapp.controllers;

import com.example.charityapp.dtos.CampaignDTO;
import com.example.charityapp.services.CampaignService;
import com.example.charityapp.services.OrganizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {
    private final CampaignService campaignService;
    private final OrganizationService organizationService;

    public CampaignController(CampaignService campaignService, OrganizationService organizationService) {
        this.campaignService = campaignService;
        this.organizationService = organizationService;
    }

    // Display all campaigns (public view)
    @GetMapping
    public String getAllCampaigns(Model model) {
        model.addAttribute("campaigns", campaignService.getAllCampaigns());
        return "campaigns"; // Resolves to templates/campaigns.html
    }

    // Admin view for managing campaigns
    @GetMapping("/admin")
    public String getAdminCampaigns(Model model) {
        model.addAttribute("campaigns", campaignService.getAllCampaigns());
        return "admin/campaigns"; // Resolves to templates/admin/campaigns.html
    }

    // Form for creating a new campaign (admin only)
    @GetMapping("/new")
    public String newCampaign(Model model) {
        model.addAttribute("campaignDTO", new CampaignDTO());
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        return "admin/campaigns"; // Resolves to templates/admin/campaigns.html
    }

    // Handle new campaign creation (admin only)
    @PostMapping("/new")
    public String createCampaign(@ModelAttribute @Valid CampaignDTO campaignDTO) {
        campaignService.createCampaign(campaignDTO);
        return "redirect:/campaigns/admin"; // Redirects to admin view
    }

    // Handle campaign deletion (admin only)
    @PostMapping("/delete/{id}")
    public String deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return "redirect:/campaigns/admin"; // Redirects to admin view
    }
}