package com.project.urlShort.model;

public class UrlDto
{
    private String url;
    private String userId;
    private String expirationDate;  //optional

    public UrlDto(String url, String expirationDate, String userId) {
        this.url = url;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    public UrlDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UrlDto [url=" + url + ", userId=" + userId + ", expirationDate=" + expirationDate + "]";
	}

	
}