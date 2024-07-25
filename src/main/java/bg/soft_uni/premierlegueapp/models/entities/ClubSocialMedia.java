package bg.soft_uni.premierlegueapp.models.entities;

import jakarta.persistence.*;

@Table(name = "club_social_media")
@Entity
public class ClubSocialMedia extends BaseEntity{
    @Column(name = "tickets_link")
    private String ticketsLink;
    @Column(name = "official_site_link")
    private String officialSiteLink;
    @Column(name = "twitter_link")
    private String twitterLink;
    @Column(name = "instagram_link")
    private String instagramLink;
    @Column(name = "facebook_link")
    private String facebookLink;
    @Column(name = "youtube_link")
    private String youtubeLink;
    @Column(name = "tiktok_link")
    private String tiktokLink;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Team team;

    public ClubSocialMedia(Team team, String ticketsLink, String officialSiteLink, String twitterLink, String instagramLink, String facebookLink, String youtubeLink, String tiktokLink) {
        this.ticketsLink = ticketsLink;
        this.officialSiteLink = officialSiteLink;
        this.twitterLink = twitterLink;
        this.instagramLink = instagramLink;
        this.facebookLink = facebookLink;
        this.youtubeLink = youtubeLink;
        this.tiktokLink = tiktokLink;
        this.team = team;
    }

    public ClubSocialMedia() {

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

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
