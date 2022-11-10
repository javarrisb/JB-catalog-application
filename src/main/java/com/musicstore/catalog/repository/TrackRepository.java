package com.musicstore.catalog.repository;

import com.musicstore.catalog.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Integer> {
    List<Track> findByAlbumId(int albumId);
}
