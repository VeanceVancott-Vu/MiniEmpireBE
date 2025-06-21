package com.example.miniempiresbe.Player.PlayerRepository;

import com.example.miniempiresbe.Player.PlayerModel.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository  extends JpaRepository<Player,Long> {
}

