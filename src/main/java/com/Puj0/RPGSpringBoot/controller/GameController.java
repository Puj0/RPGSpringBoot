package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.view.GameParameters;
import com.Puj0.RPGSpringBoot.view.GameView;
import com.Puj0.RPGSpringBoot.service.IGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/games")
class GameController {

    private IGameService gameService;

    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GameView getGame(@PathVariable("id") String id) throws Exception {
        log.info("ID = {}", id);
        return gameService.getGame(Long.parseLong(id));
    }

    @PostMapping(value = "/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public void startGame(@Valid @RequestBody GameParameters gameParameters){
        gameService.startGame(gameParameters);
    }
}