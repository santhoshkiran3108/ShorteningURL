package com.project.urlShort.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.project.urlShort.model.Url;
import com.project.urlShort.model.UrlDto;
import com.project.urlShort.model.UrlErrorResponseDto;
import com.project.urlShort.model.UrlResponseDto;
import com.project.urlShort.repository.UrlRepository;
import com.project.urlShort.service.UrlService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class UrlShorteningController {
	@Autowired
	private UrlService urlService;
	@Autowired
	private UrlRepository urlRepository;
	@GetMapping("index.html")
	public String index() {

		return "index";
	}

	@PostMapping("/shortenurl")
	public ResponseEntity<?> generateShortLink(@RequestBody UrlDto urlDto) {

		Url urlToRet = urlService.generateShortLink(urlDto);

		if (urlToRet != null) {
			UrlResponseDto urlResponseDto = new UrlResponseDto();
			urlResponseDto.setOriginalUrl(urlToRet.getOriginalUrl());
			urlResponseDto.setExpirationDate(urlToRet.getExpirationDate());
			urlResponseDto.setShortLink(urlToRet.getShortLink());
			urlResponseDto.setUrlShorten(urlService.getFrequencyOfUrl(urlToRet.getCreationDate(),
					urlToRet.getRecentUsedDate(), urlToRet.getTotalConvert()));
			return new ResponseEntity<UrlResponseDto>(urlResponseDto, HttpStatus.OK);
		}

		UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
		urlErrorResponseDto.setStatus("404");
		urlErrorResponseDto.setError("There was an error processing your request. please try again.");
		return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);

	}

	@GetMapping("/{shortLink}/{userId}")
	public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, @PathVariable String userId,
			HttpServletResponse response) throws IOException {

		if (StringUtils.isEmpty(shortLink)) {
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Invalid Url");
			urlErrorResponseDto.setStatus("400");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}
		List<Url> urlToRet = urlService.getEncodedUrl(shortLink, userId);
		UrlResponseDto urlResponseDto = new UrlResponseDto();
		
		if (urlToRet == null) {
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Url does not exist or it might have expired!");
			urlErrorResponseDto.setStatus("400");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}

		for (Url url : urlToRet) {

			if (userId.equalsIgnoreCase(url.getUserId())) {

				if (url.getExpirationDate().isBefore(LocalDateTime.now())) {
					urlService.deleteShortLink(url);
					UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
					urlErrorResponseDto.setError("Url Expired. Please try generating a fresh one.");
					urlErrorResponseDto.setStatus("200");
					return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
				}

				Url urlToPersist = url;
				urlToPersist.setTotalRedirect(url.getTotalRedirect() + 1);
				urlService.persistShortLink(urlToPersist);

				urlResponseDto.setOriginalUrl(url.getOriginalUrl());
				urlResponseDto.setUrlRedirect(url.getTotalRedirect());

				// response.sendRedirect(urlToRet.getOriginalUrl());
			}
		}
		return new ResponseEntity<UrlResponseDto>(urlResponseDto, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/{userId}")
	public List<Url> printData( @PathVariable String userId, HttpServletResponse response) throws IOException {
		
		
		
		if(userId.equalsIgnoreCase("Admin")) {
			List<Url> urlToRet = urlRepository.findAll();
		return urlToRet;
		}
		
		List<Url> urlToRet = urlRepository.findById(userId);		
		return urlToRet;
	}
}