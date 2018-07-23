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

    private final static Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private IGameService gameService;

    @Autowired
    private IActerService acterService;

    @RequestMapping(value = "/start/{rounds}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String startGame(@PathVariable int rounds) {
        logger.debug("Rounds = {}", rounds);
        return gameService.startGame(rounds);
    }

    @RequestMapping(value = "/result/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGameResult(@PathVariable("id") String id) {
        logger.debug("ID = {}", id);
        return gameService.getGameResult(Long.parseLong(id));
    }

}
