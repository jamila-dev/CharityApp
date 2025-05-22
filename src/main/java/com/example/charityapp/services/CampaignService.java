package com.example.charityapp.services;
import com.example.charityapp.dtos.CampaignDTO;
import com.example.charityapp.entities.Campaign;
import com.example.charityapp.entities.Organization;
import com.example.charityapp.repositories.CampaignRepository;
import com.example.charityapp.repositories.OrganizationRepository;
import org.springframework.stereotype.Service;
import com.example.charityapp.entities.Organization;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final OrganizationRepository organizationRepository;

    public CampaignService(CampaignRepository campaignRepository, OrganizationRepository organizationRepository) {
        this.campaignRepository = campaignRepository;
        this.organizationRepository = organizationRepository;
    }



    public Campaign createCampaign(CampaignDTO dto) {
        Organization organization = organizationRepository.findById(dto.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        Campaign campaign = new Campaign();
        campaign.setTitle(dto.getTitle());
        campaign.setDescription(dto.getDescription());
        campaign.setGoalAmount(dto.getGoalAmount());
        campaign.setOrganization(organization);
        campaign.setUpdatedAt(LocalDateTime.now());
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }
}
