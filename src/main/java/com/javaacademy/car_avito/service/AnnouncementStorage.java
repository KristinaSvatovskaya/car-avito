package com.javaacademy.car_avito.service;

import com.javaacademy.car_avito.announcement.Announcement;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AnnouncementStorage {
    private final Map<Integer, Announcement> data = new HashMap<>();
    private Integer count = 0;

    public void save(Announcement announcement) {
        count++;
        announcement.setId(count);
        data.put(count, announcement);
    }

    public List<Announcement> getAll() {
        return data.values().stream().toList();
    }

    public Optional<Announcement> getById(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    public boolean deleteById(Integer id) {
        return data.remove(id) != null;
    }
}
