package com.game.controller;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Sweets
@RestController
@RequestMapping("/rest")
public class PlayerController {

    @Autowired
    PlayerDataService playerDataService;

    public PlayerController(PlayerDataService playerDataService) {
        this.playerDataService = playerDataService;
    }

    @GetMapping("/players")
    public List<Player> getList(@RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "title", required = false) String title,
                                @RequestParam(value = "race", required = false) Race race,
                                @RequestParam(value = "profession", required = false) Profession profession,
                                @RequestParam(value = "after", required = false) Long after,
                                @RequestParam(value = "before", required = false) Long before,
                                @RequestParam(value = "banned", required = false) Boolean banned,
                                @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                @RequestParam(value = "maxLevel", required = false) Integer maxLevel,
                                @RequestParam(value = "order", required = false) PlayerOrder order,
                                @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize){

        final List<Player> tmp = playerDataService.getListPlayers(name, title, race, profession,
                after, before, banned , minExperience, maxExperience, minLevel, maxLevel);
        final List<Player> sortedList = playerDataService.sortPlayers(tmp, order);
        return playerDataService.sortPage(sortedList, pageNumber, pageSize);
    }

    @GetMapping("/players/count")
    public Integer getCount(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "race", required = false) Race race,
                            @RequestParam(value = "profession", required = false) Profession profession,
                            @RequestParam(value = "after", required = false) Long after,
                            @RequestParam(value = "before", required = false) Long before,
                            @RequestParam(value = "banned", required = false) Boolean banned,
                            @RequestParam(value = "minExperience", required = false) Integer minExperience,
                            @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                            @RequestParam(value = "minLevel", required = false) Integer minLevel,
                            @RequestParam(value = "maxLevel", required = false) Integer maxLevel){
        return playerDataService.getListPlayers(name, title, race, profession, after, before,
                banned, minExperience, maxExperience, minLevel, maxLevel).size();
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable(value = "id") String id){
        return playerDataService.getPlayer(id);
    }

    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player) {
        return playerDataService.createNewPlayer(player);
    }

    @PostMapping("/players/{id}")
    public Player updatePlayer(@PathVariable (value = "id") String id,
                               @RequestBody Player player){
        return playerDataService.updatePlayer(playerDataService.getPlayer(id), player);
    }

    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable (value = "id") String id){
        playerDataService.deletePlayer(playerDataService.getPlayer(id));
    }
}
