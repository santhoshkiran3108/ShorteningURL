package com.project.urlShort.model;

import java.time.LocalDateTime;

public class UrlResponseDto
{
    private String originalUrl;
    private String shortLink;
    private LocalDateTime expirationDate;
    private long urlShorten;
    private long urlRedirect;

    public UrlResponseDto(String originalUrl, String shortLink, LocalDateTime expirationDate, long urlshorten, long urlRedirect) {
        this.originalUrl = originalUrl;
        this.shortLink = shortLink;
        this.expirationDate = expirationDate;
        this.urlRedirect = urlRedirect;
        this.urlShorten = urlShorten;
    }

    public UrlResponseDto() {
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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
    

    public long getUrlShorten() {
		return urlShorten;
	}

	public void setUrlShorten(long urlShorten) {
		this.urlShorten = urlShorten;
	}

	public long getUrlRedirect() {
		return urlRedirect;
	}

	public void setUrlRedirect(long urlRedirect) {
		this.urlRedirect = urlRedirect;
	}

	@Override
	public String toString() {
		return "UrlResponseDto [originalUrl=" + originalUrl + ", shortLink=" + shortLink + ", expirationDate="
				+ expirationDate + ", urlShorten=" + urlShorten + ", urlRedirect=" + urlRedirect + "]";
	}

	
}