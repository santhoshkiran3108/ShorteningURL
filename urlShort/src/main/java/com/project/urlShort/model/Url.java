package com.project.urlShort.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Entity
public class Url
{
    @Id
    @GeneratedValue
    private long id;
    @Lob
    private String originalUrl;
    private String shortLink;
    private String userId;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
    private LocalDateTime recentUsedDate;
    private long totalConvert;
    private long totalRedirect;

    public Url(long id, String originalUrl, String shortLink, LocalDateTime creationDate, LocalDateTime expirationDate, LocalDateTime recentUsedDate, long totalConvert,long totalRedirect, String userId) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.recentUsedDate = recentUsedDate;
        this.totalConvert = totalConvert;
        this.totalRedirect = totalRedirect; 
        this.userId = userId;
    }

    public Url() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
    

    public LocalDateTime getRecentUsedDate() {
		return recentUsedDate;
	}

	public void setRecentUsedDate(LocalDateTime recentUsedDate) {
		this.recentUsedDate = recentUsedDate;
	}
	
	

	public long getTotalConvert() {
		return totalConvert;
	}

	public void setTotalConvert(long totalConvert) {
		this.totalConvert = totalConvert;
	}

	public long getTotalRedirect() {
		return totalRedirect;
	}

	public void setTotalRedirect(long totalRedirect) {
		this.totalRedirect = totalRedirect;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", originalUrl=" + originalUrl + ", shortLink=" + shortLink + ", userId=" + userId
				+ ", creationDate=" + creationDate + ", expirationDate=" + expirationDate + ", recentUsedDate="
				+ recentUsedDate + ", totalConvert=" + totalConvert + ", totalRedirect=" + totalRedirect + "]";
	}

	

	
}