package com.example.miniempiresbe.Player.PlayerModel;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PLayer")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id", nullable = false)
    private Long id;

    @Column(nullable=false, unique=true)
    private String email;


    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "player_rank")
    private Rank rank;
    private Integer level;

    @Column(name = "XP")
    private Integer experiencePoints;

    @ElementCollection
    @CollectionTable(name = "player_match_history", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "match_id") // or whatever the string represents
    private List<String> matchHistory = new ArrayList<>();
    private String achievement;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", rank=" + rank +
                ", level=" + level +
                ", experiencePoints=" + experiencePoints +
                ", matchHistory=" + matchHistory +
                ", achievement='" + achievement + '\'' +
                '}';
    }



    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(Integer experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public List getMatchHistory() {
        return matchHistory;
    }

    public void setMatchHistory(List matchHistory) {
        this.matchHistory = matchHistory;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }
}
