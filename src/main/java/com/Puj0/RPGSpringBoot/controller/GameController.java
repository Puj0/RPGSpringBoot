package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.service.IGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/games")
class GameController {

    private IGameService gameService;

    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/result/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGameResult(@PathVariable("id") String id) {
        log.info("ID = {}", id);
        return gameService.getGameResult(Long.parseLong(id));
    }

    @GetMapping(value = {"/start/{rounds}",
            "/start/{rounds}/{list_of_acter_ids}"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String startGameWithActers(@PathVariable("rounds") int rounds,
                                      @Nullable @PathVariable("list_of_acter_ids") String acterIDs){
        List<Long> idList = new ArrayList<>();
        if (acterIDs != null) {
            try {
                String[] ids = acterIDs.split(",");
                for (String id : ids) {
                    idList.add(Long.parseLong(id.trim()));
                }
            } catch (Exception e) {
                log.error("Unacceptable IDs error", e);
            }
        }
        return gameService.startGame(rounds, idList);
    }

}
