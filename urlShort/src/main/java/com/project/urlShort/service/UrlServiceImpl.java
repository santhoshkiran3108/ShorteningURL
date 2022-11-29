package com.project.urlShort.service;

import com.google.common.hash.Hashing;
import com.project.urlShort.model.Url;
import com.project.urlShort.model.UrlDto;
import com.project.urlShort.repository.UrlRepository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class UrlServiceImpl implements UrlService {

	private static final Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);
	@Autowired
	private UrlRepository urlRepository;

	/*
	 * This is the function used to covert the longUrl to encoded short URL This
	 * checks if URL is already present in the DB if not it allows us to add to DB
	 * or else only the conversion count is incremented in DB
	 */

	@Override
	public Url generateShortLink(UrlDto urlDto) {

		if (StringUtils.isNotEmpty(urlDto.getUrl()) && StringUtils.isNotEmpty(urlDto.getUserId())) {
			List<Url> longUrl = getLongUrl(urlDto.getUrl(), urlDto.getUserId());
			

					if (longUrl.isEmpty()) {

						String encodedUrl = encodeUrl(urlDto.getUrl());
						Url urlToPersist = new Url();
						urlToPersist.setCreationDate(LocalDateTime.now());
						urlToPersist.setOriginalUrl(urlDto.getUrl());
						urlToPersist.setShortLink(encodedUrl);
						urlToPersist.setExpirationDate(
								getExpirationDate(urlDto.getExpirationDate(), urlToPersist.getCreationDate()));
						urlToPersist.setRecentUsedDate(LocalDateTime.now());
						urlToPersist.setTotalConvert(1);
						urlToPersist.setUserId(urlDto.getUserId());
						Url urlToRet = persistShortLink(urlToPersist);

						if (urlToRet != null) {
							return urlToRet;
						}

					}
					
					for(Url url : longUrl) {
						if(url.getOriginalUrl().equalsIgnoreCase(urlDto.getUrl())) {
						if(!url.getUserId().equalsIgnoreCase(urlDto.getUserId())) {
							if(url.getShortLink().isEmpty()) {
							
								String encodedUrl = encodeUrl(urlDto.getUrl());
								Url urlToPersist = new Url();
								urlToPersist.setCreationDate(LocalDateTime.now());
								urlToPersist.setOriginalUrl(urlDto.getUrl());
								urlToPersist.setShortLink(encodedUrl);
								urlToPersist.setExpirationDate(
										getExpirationDate(urlDto.getExpirationDate(), urlToPersist.getCreationDate()));
								urlToPersist.setRecentUsedDate(LocalDateTime.now());
								urlToPersist.setTotalConvert(1);
								urlToPersist.setUserId(urlDto.getUserId());
								Url urlToRet = persistShortLink(urlToPersist);

								if (urlToRet != null) {
									return urlToRet;
								}

							}
						}}
					}
					
					for (Url url : longUrl) {
						if (url.getUserId().equalsIgnoreCase(urlDto.getUserId())) {
					
					 if (urlDto.getUrl().equals(url.getOriginalUrl())) {
						Url urlToPersist = url;
						urlToPersist.setRecentUsedDate(LocalDateTime.now());
						urlToPersist.setTotalConvert(url.getTotalConvert() + 1);
						Url urlToRet = persistShortLink(urlToPersist);
						if (urlToRet != null) {
							return urlToRet;
						}
					}

				}
			}
		}
		return null;
	}

	private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
		if (StringUtils.isBlank(expirationDate)) {
			return creationDate.plusDays(1);
		}
		LocalDateTime expirationDateToRet = LocalDateTime.parse(expirationDate);
		return expirationDateToRet;
	}

	private String encodeUrl(String url) {
		String encodedUrl = "";
		// LocalDateTime time = LocalDateTime.now();
		encodedUrl = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
		return encodedUrl;
	}

	@Override
	public Url persistShortLink(Url url) {
		Url urlToRet = urlRepository.save(url);
		return urlToRet;
	}

	@Override
	public List<Url> getEncodedUrl(String url, String userId) {
		List<Url> urlToRet = new ArrayList<>();
		urlToRet = urlRepository.findByShortLink(url);
		return urlToRet;
	}

	@Override
	public List<Url> getLongUrl(String url, String userId) {
		List<Url> longUrl = new ArrayList<>();
		longUrl = urlRepository.findByoriginalUrl(url);
		return longUrl;
	}

	@Override
	public void deleteShortLink(Url url) {

		urlRepository.delete(url);
	}

	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
		return java.sql.Timestamp.valueOf(dateToConvert);
	}

	@Override
	public long getFrequencyOfUrl(LocalDateTime date1, LocalDateTime date2, long totalHits) {

		Date d1 = convertToDateViaSqlTimestamp(date1);
		System.out.println(d1);
		Date d2 = convertToDateViaSqlTimestamp(date2);
		System.out.println(d2);
		long difference_In_Time = d2.getTime() - d1.getTime();

		long difference_In_Hours = TimeUnit.MILLISECONDS.toSeconds(difference_In_Time) % 60;

		long frequencyofUrlConverted = difference_In_Hours / totalHits;
		System.out.println(frequencyofUrlConverted);

		return frequencyofUrlConverted;
	}

}