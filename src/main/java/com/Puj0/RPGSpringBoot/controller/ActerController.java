package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.service.IActerService;
import com.Puj0.RPGSpringBoot.view.ActerParameters;
import com.Puj0.RPGSpringBoot.view.ActerView;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Acter> getActers() {
        return acterService.getAllActers();
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Acter> addActers(@RequestBody String range) {
        log.info("Range = {}", range);
        return acterService.createActers(Integer.parseInt(range));
    }

    @PostMapping(value = "/addActer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActerView> addActer(@Valid @RequestBody ActerParameters acterParameters) {
        return acterService.createActer(acterParameters);
    }
}
