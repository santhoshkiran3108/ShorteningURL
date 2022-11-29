package com.project.urlShort.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.urlShort.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long>
{
    public List<Url> findByShortLink(String shortLink);
    public List<Url> findByoriginalUrl(String originalUrl);
    public List<Url> findAll();
    public List<Url> findById(String userId);
}