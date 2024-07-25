package bg.soft_uni.premierlegueapp.models.dtos;

import bg.soft_uni.premierlegueapp.models.entities.Team;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class LinksExportDto {
    private String ticketsLink;
    private String officialSiteLink;
    private String twitterLink;
    private String instagramLink;
    private String facebookLink;
    private String youtubeLink;
    private String tiktokLink;

    public String getTicketsLink() {
        return ticketsLink;
    }

    public void setTicketsLink(String ticketsLink) {
        this.ticketsLink = ticketsLink;
    }

    public String getOfficialSiteLink() {
        return officialSiteLink;
    }

    public void setOfficialSiteLink(String officialSiteLink) {
        this.officialSiteLink = officialSiteLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getInstagramLink() {
        return instagramLink;
    }

    public void setInstagramLink(String instagramLink) {
        this.instagramLink = instagramLink;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getTiktokLink() {
        return tiktokLink;
    }

    public void setTiktokLink(String tiktokLink) {
        this.tiktokLink = tiktokLink;
    }
}
