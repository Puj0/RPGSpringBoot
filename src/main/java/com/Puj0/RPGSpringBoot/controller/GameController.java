package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.domain.GameSearchRequest;
import com.Puj0.RPGSpringBoot.view.GameRequest;
import com.Puj0.RPGSpringBoot.view.GameView;
import com.Puj0.RPGSpringBoot.service.IGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<GameView> getGame(@PathVariable("id") String id) {
        log.info("ID = {}", id);
        return new ResponseEntity<>(gameService.getGame(Long.parseLong(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity startGame(@Valid @RequestBody GameRequest gameRequest){
        gameService.startGame(gameRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GameView>> getGames(GameSearchRequest request){
        return new ResponseEntity<>(gameService.getGames(request), HttpStatus.OK);
    }
}