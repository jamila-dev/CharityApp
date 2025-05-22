package com.example.charityapp.controllers;

import com.example.charityapp.services.CampaignService;
import com.example.charityapp.services.DonationService;
import com.example.charityapp.services.OrganizationService;
import com.example.charityapp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.charityapp.dtos.CampaignDTO;
import com.example.charityapp.dtos.OrganizationDTO;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CampaignService campaignService;
    private final DonationService donationService;
    private final UserService userService;
    private final OrganizationService organizationService;

    public AdminController(CampaignService campaignService, DonationService donationService, UserService userService, OrganizationService organizationService) {
        this.campaignService = campaignService;
        this.donationService = donationService;
        this.userService = userService;
        this.organizationService = organizationService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("campaigns", campaignService.getAllCampaigns());
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        return "admin/dashboard";
    }

    @GetMapping("/campaigns")
    public String manageCampaigns(Model model) {
        model.addAttribute("campaigns", campaignService.getAllCampaigns());
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        model.addAttribute("campaignDTO", new CampaignDTO());
        return "admin/campaigns";
    }

    @GetMapping("/donations")
    public String manageDonations(Model model) {
        model.addAttribute("donations", donationService.getDonationsByCampaign(null));
        return "admin/donations";
    }

    @GetMapping("/users")
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("/organizations")
    public String manageOrganizations(Model model) {
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        model.addAttribute("organizationDTO", new OrganizationDTO());
        return "admin/organizations";
    }
}

