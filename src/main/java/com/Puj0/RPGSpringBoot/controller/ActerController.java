package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.domain.ActerSearchRequest;
import com.Puj0.RPGSpringBoot.domain.game.MinimumHeroes;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.service.IActerService;
import com.Puj0.RPGSpringBoot.view.ActerRequest;
import com.Puj0.RPGSpringBoot.view.ActerView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/acters")
class ActerController {

    private final IActerService acterService;

    ActerController(IActerService acterService){
        this.acterService = acterService;
    }

    @PostMapping(value = "/addActers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Acter>> addActers(@Valid @RequestBody MinimumHeroes minNumOfHeroes) {
        log.info("Minimum number of Heroes = {}", minNumOfHeroes.getMinNumOfHeroes());
        return new ResponseEntity<>(acterService.createActers(minNumOfHeroes), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActerView> addActer(@Valid @RequestBody ActerRequest acterRequest) {
        ActerView acterView = acterService.createActer(acterRequest);
        if (acterView != null){
            return new ResponseEntity<>(acterView, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<ActerView>> getActers(ActerSearchRequest values){
        return new ResponseEntity<>(acterService.getActers(values), HttpStatus.OK);
    }
}
