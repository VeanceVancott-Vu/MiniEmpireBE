package com.example.miniempiresbe.Player.PlayerService;

import com.example.miniempiresbe.Player.PlayerModel.Player;
import com.example.miniempiresbe.Player.PlayerModel.Rank;
import com.example.miniempiresbe.Player.PlayerModel.UpdatePlayerRequest;
import com.example.miniempiresbe.Player.PlayerRepository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class PlayerService {

 private final PlayerRepository repo;


    public PlayerService(PlayerRepository repo) {
        this.repo = repo;
    }

    public Boolean AddNewPlayer(Player player)
    {

        player.setExperiencePoints(0);

        player.setRank(Rank.BRONZE);
        player.setLevel(1);

        try
        {
            repo.save(player);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }


    }


    public Player GetPlayerById(Long playerId)
    {
        try {
            Optional<Player> existingPlayer = repo.findById(playerId); // userId is of type Long
            return existingPlayer.orElse(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

//    public Player ValidatePlayer(String email,String password)
//    {
//        try {
//           ArrayList<Player> players = GetAllPlayer();
//           for(Player player: players)
//           {
//               if(email.equals(player.getEmail()) && player.getPassword().equals(password))
//               {
//
//                   return player;
//
//               }
//           }
//           return null;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }


    public ArrayList<Player> GetAllPlayer()
    {
        try {
            return (ArrayList<Player>) repo.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteById(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }





    public Player UpdatePlayer(Long playerId, UpdatePlayerRequest request)
     {

         try{
             Optional<Player> existingPlayer = repo.findById(playerId); // userId is of type Long
            if(existingPlayer.isPresent())
            {
                Player player = existingPlayer.get();

                if (request.getName() != null) player.setName(request.getName());
                if (request.getLevel() != null) player.setLevel(request.getLevel());
                if (request.getXp() != null) player.setExperiencePoints(request.getXp());
                if (request.getRank() != null) player.setRank(request.getRank());

                return repo.save(player);
            }
            else
            {
                return null;
            }

         }
         catch (Exception e)
         {
             e.printStackTrace();
             return null;
         }

     }



}