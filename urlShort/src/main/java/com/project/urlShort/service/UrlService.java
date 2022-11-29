package com.project.urlShort.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.urlShort.model.Url;
import com.project.urlShort.model.UrlDto;

@Service
public interface UrlService
{
    public Url generateShortLink(UrlDto urlDto);
    public Url persistShortLink(Url url);
    public List<Url> getEncodedUrl(String url,String userId);
    public  void  deleteShortLink(Url url);
	public List<Url> getLongUrl(String url, String userId);
	public long getFrequencyOfUrl(LocalDateTime date1, LocalDateTime date2, long totalHits);
}