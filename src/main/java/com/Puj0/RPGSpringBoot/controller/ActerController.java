package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.domain.game.MinimumHeroes;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.filter.ActerSpecificationBuilder;
import com.Puj0.RPGSpringBoot.service.IActerService;
import com.Puj0.RPGSpringBoot.view.ActerRequest;
import com.Puj0.RPGSpringBoot.view.ActerView;
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
@RequestMapping("/acters")
class ActerController {

    private final IActerService acterService;

    ActerController(IActerService acterService){
        this.acterService = acterService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Acter>> getActers() {
        return new ResponseEntity<>(acterService.getAllActers(), HttpStatus.OK);
    }

    @PostMapping(value = "/addActers/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Acter>> addActers(@Valid @RequestBody MinimumHeroes minNumOfHeroes) {
        log.info("Minimum number of Heroes = {}", minNumOfHeroes.getMinNumOfHeroes());
        return new ResponseEntity<>(acterService.createActers(minNumOfHeroes), HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActerView> addActer(@Valid @RequestBody ActerRequest acterRequest) {
        ActerView acterView = acterService.createActer(acterRequest);
        if (acterView != null){
            return new ResponseEntity<>(acterView, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<ActerView>> getFilteredActers(@RequestParam(value = "filter") String filter){
        ActerSpecificationBuilder builder = new ActerSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(filter + ",");
        while(matcher.find()){
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Acter> specification = builder.build();
        return new ResponseEntity<>(acterService.findAll(specification), HttpStatus.OK);
    }
}
