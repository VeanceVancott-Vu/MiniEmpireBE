package com.example.miniempiresbe.Player.PlayerController;


import com.example.miniempiresbe.Player.PlayerModel.Player;
import com.example.miniempiresbe.Player.PlayerModel.UpdatePlayerRequest;
import com.example.miniempiresbe.Player.PlayerService.PlayerService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/players")
//@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    // 1) Create a new player
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> AddNewPlayer(@RequestBody Player player)
    {
        boolean ok = playerService.AddNewPlayer(player);
        return ok
                ? ResponseEntity.status(HttpStatus.CREATED).build()
                : ResponseEntity.status(HttpStatus.CONFLICT).build();

    }

    // 2) Fetch one player by ID
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        Player p = playerService.GetPlayerById(id);
        return p != null
                ? ResponseEntity.ok(p)
                : ResponseEntity.notFound().build();
    }

    // 3) List all players
    @GetMapping()
    public ResponseEntity<List<Player>> listPlayers() {
        List<Player> all = playerService.GetAllPlayer();
        return ResponseEntity.ok(all);
    }

    // 4) Validate (login) – expects JSON {"email":"…","password":"…"}
//    @PostMapping("/login")
//    public ResponseEntity<Player> login(@RequestBody Map<String,String> creds) {
//        Player p = playerService.ValidatePlayer(
//                creds.get("email"),
//                creds.get("password")
//        );
//        return p != null
//                ? ResponseEntity.ok(p)
//                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }

    // 5) Update a player’s profile
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(
            @PathVariable Long id,
            @RequestBody UpdatePlayerRequest req
    ) {
        Player updated = playerService.UpdatePlayer(id, req);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        boolean deleted = playerService.deleteById(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
