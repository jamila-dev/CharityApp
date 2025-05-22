package com.example.charityapp.services;

import com.example.charityapp.dtos.DonationDTO;
import com.example.charityapp.entities.Campaign;
import com.example.charityapp.entities.Donation;
import com.example.charityapp.entities.User;
import com.example.charityapp.repositories.CampaignRepository;
import com.example.charityapp.repositories.DonationRepository;
import com.example.charityapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationService {
    private final DonationRepository donationRepository;
    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;

    public DonationService(DonationRepository donationRepository, CampaignRepository campaignRepository, UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
    }

    public Donation makeDonation(DonationDTO dto, String username) {
        Campaign campaign = campaignRepository.findById(dto.getCampaignId())
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Donation donation = new Donation();
        donation.setAmount(dto.getAmount());
        donation.setCampaign(campaign);
        donation.setUser(user);
        donation.setCreatedAt(LocalDateTime.now());

        campaign.setRaisedAmount(campaign.getRaisedAmount() + dto.getAmount());
        campaign.setUpdatedAt(LocalDateTime.now());
        campaignRepository.save(campaign);

        return donationRepository.save(donation);
    }

    public List<Donation> getDonationsByCampaign(Long campaignId) {
        return campaignId != null ? donationRepository.findByCampaignId(campaignId) : donationRepository.findAll();
    }
}