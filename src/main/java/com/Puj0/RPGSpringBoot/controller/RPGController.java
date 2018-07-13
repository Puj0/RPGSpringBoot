package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RPGController {

    @Autowired
    @Qualifier(value = "gameService")
    private IGameService gameService;

    @RequestMapping(value = "/acters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Acter> getActers(){
        return this.gameService.getAll();
    }

    @RequestMapping(value = "/games/start",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String startGame(){
        return gameService.startGame();
    }

//    @RequestMapping(value = "/add/acter/{name},{health}",
//    method = RequestMethod.POST,
//    consumes = "application/json")
//    public ResponseEntity<>


}
