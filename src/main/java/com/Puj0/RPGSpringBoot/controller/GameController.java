package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.service.IActerService;
import com.Puj0.RPGSpringBoot.service.IGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private IGameService gameService;

    @Autowired
    private IActerService acterService;

    @GetMapping(value = "/start/{rounds}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String startGame(@PathVariable int rounds) {
        logger.info("Rounds = {}", rounds);
        return gameService.startGame(rounds);
    }

    @GetMapping(value = "/result/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGameResult(@PathVariable("id") String id) {
        logger.info("ID = {}", id);
        return gameService.getGameResult(Long.parseLong(id));
    }

}
