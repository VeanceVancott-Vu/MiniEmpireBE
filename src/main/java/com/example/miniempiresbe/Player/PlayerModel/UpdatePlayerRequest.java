package com.example.miniempiresbe.Player.PlayerModel;

public class UpdatePlayerRequest {
    private String name;
    private Integer level;
    private Integer xp;
    private Rank rank;

    public UpdatePlayerRequest(String name, Integer level, Integer xp, Rank rank) {
        this.name = name;
        this.level = level;
        this.xp = xp;
        this.rank = rank;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public Integer getXp() { return xp; }
    public void setXp(Integer xp) { this.xp = xp; }

    public Rank getRank() { return rank; }
    public void setRank(Rank rank) { this.rank = rank; }
}
