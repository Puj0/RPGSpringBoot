package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.service.IActerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/acter")
class ActerController {

    private final IActerService acterService;

    ActerController(IActerService acterService){
        this.acterService = acterService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Acter> getActers() {
        return acterService.allActers();
    }

    @PostMapping(value = "/{range}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Acter> addActers(@PathVariable("range") String range) {
        log.info("Range = {}", range);
        return acterService.createActers(Integer.parseInt(range));
    }
}
