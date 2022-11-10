package com.musicstore.catalog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "album")
public class Album {
    @Id
    @Column(name = "album_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "albumId")
    private List<Track> tracks = new ArrayList<>();

    private String title;

    private int artistId;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private int labelId;

    private BigDecimal listPrice;

    public Album() {
    }

    public Album(Integer id, List<Track> tracks, String title, int artistId, LocalDate releaseDate, int labelId, BigDecimal listPrice) {
        this.id = id;
        this.tracks = tracks;
        this.title = title;
        this.artistId = artistId;
        this.releaseDate = releaseDate;
        this.labelId = labelId;
        this.listPrice = listPrice;
    }

    public Album(int i, String bless_me, int i1, String s, int i2, double v) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return getArtistId() == album.getArtistId() && getLabelId() == album.getLabelId() && Objects.equals(getId(), album.getId()) && Objects.equals(getTracks(), album.getTracks()) && Objects.equals(getTitle(), album.getTitle()) && Objects.equals(getReleaseDate(), album.getReleaseDate()) && Objects.equals(getListPrice(), album.getListPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTracks(), getTitle(), getArtistId(), getReleaseDate(), getLabelId(), getListPrice());
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", tracks=" + tracks +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", releaseDate=" + releaseDate +
                ", labelId=" + labelId +
                ", listPrice=" + listPrice +
                '}';
    }
}
