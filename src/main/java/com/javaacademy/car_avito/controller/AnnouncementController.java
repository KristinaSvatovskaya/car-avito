package com.javaacademy.car_avito.controller;

import com.javaacademy.car_avito.announcement.Announcement;
import com.javaacademy.car_avito.service.AnnouncementStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementStorage announcementStorage;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAnnouncement(@RequestBody Announcement announcement) {
        announcementStorage.save(announcement);
    }

    @GetMapping("/{id}")
    public Announcement getAnnouncementById(@PathVariable Integer id) {
        return announcementStorage.getById(id).orElseThrow();
    }

    @GetMapping
    public List<Announcement> getAnnouncementsByBrand(@RequestParam(required = false) String brandName) {
        return announcementStorage.getAll().stream()
                .filter(a -> brandName == null || a.getBrandName().equalsIgnoreCase(brandName))
                .toList();
    }

    @GetMapping("/search")
    public List<Announcement> getAnnouncementsByParam(
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) String colour,
            @RequestParam(required = false) BigDecimal price) {

        return announcementStorage.getAll().stream()
                .filter(a -> (brandName == null || a.getBrandName().equalsIgnoreCase(brandName))
                        && (colour == null || a.getColour().equalsIgnoreCase(colour))
                        && (price == null || a.getPrice().compareTo(price) == 0))
                .toList();
    }

    @DeleteMapping("/{id}")
    public boolean deleteAnnouncementById(@PathVariable Integer id) {
        return announcementStorage.deleteById(id);
    }
}
