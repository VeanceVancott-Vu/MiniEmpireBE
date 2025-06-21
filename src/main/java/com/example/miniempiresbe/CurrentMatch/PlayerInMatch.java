package com.example.miniempiresbe.CurrentMatch;

import com.example.miniempiresbe.Player.PlayerModel.Player;
import org.hibernate.mapping.List;

public class PlayerInMatch {

    private final Player player;
    private List Resources;

    private String DiplomacyStatus;
    private String 	CurrentMatchId;

   // private	CurrentTile currentTile;

  //  private  Command command;
    // private Army army;
   // private TechnologyTreeProgress technologyTreeProgress;

    public PlayerInMatch(Player player) {
        this.player = player;
    }
}
