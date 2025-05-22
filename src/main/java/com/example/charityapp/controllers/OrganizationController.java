package com.example.charityapp.controllers;

import com.example.charityapp.dtos.OrganizationDTO;
import com.example.charityapp.services.OrganizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public String getAllOrganizations(Model model) {
        model.addAttribute("organizations", organizationService.getAllOrganizations());
        model.addAttribute("organizationDTO", new OrganizationDTO());
        return "admin/organizations";
    }

    @PostMapping("/new")
    public String createOrganization(@ModelAttribute @Valid OrganizationDTO organizationDTO) {
        organizationService.createOrganization(organizationDTO);
        return "redirect:/admin/organizations";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
        return "redirect:/admin/organizations";
    }
}

