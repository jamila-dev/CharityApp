package com.example.charityapp.repositories;

import com.example.charityapp.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByCampaignId(Long campaignId);
}