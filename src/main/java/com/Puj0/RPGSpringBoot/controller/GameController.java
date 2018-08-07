package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.domain.game.Game;
import com.Puj0.RPGSpringBoot.filter.GameSpecification;
import com.Puj0.RPGSpringBoot.filter.GameSpecificationBuilder;
import com.Puj0.RPGSpringBoot.view.GameRequest;
import com.Puj0.RPGSpringBoot.view.GameView;
import com.Puj0.RPGSpringBoot.service.IGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public ResponseEntity<GameView> getGame(@PathVariable("id") String id) throws Exception {
        log.info("ID = {}", id);
        return new ResponseEntity<>(gameService.getGame(Long.parseLong(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity startGame(@Valid @RequestBody GameRequest gameRequest){
        gameService.startGame(gameRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<GameView>> getFilteredGames(@RequestParam(value = "filter") String filter){
        GameSpecificationBuilder builder = new GameSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(filter + ",");
        while(matcher.find()){
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Game> specification = builder.build();
        return new ResponseEntity<>(gameService.findAll(specification), HttpStatus.OK);
    }
}