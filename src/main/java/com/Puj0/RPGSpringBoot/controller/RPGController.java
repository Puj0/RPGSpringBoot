package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.service.IActerService;
import com.Puj0.RPGSpringBoot.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RPGController {

    @Autowired
    private IGameService gameService;

    @Autowired
    private IActerService acterService;

    @RequestMapping({"index", "", "/"})
    String index(Model model) {
        model.addAttribute("acters", acterService.allActers());
        return "acters";
    }

    @RequestMapping(value = "/acters",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Acter> getActers() {
        return this.gameService.getAll();
    }

    @RequestMapping(value = "/games/start/{rounds}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String startGame(@PathVariable String rounds) {
        System.out.println("Rounds = " + rounds);
        String result = gameService.startGame(Integer.parseInt(rounds));
        gameService.saveGame();
        return result;
    }


    @RequestMapping(value = "/createActers/{range}")
    public String createActers(@PathVariable String range, Model model) {
        System.out.println("Range = " + range);
        acterService.createActers(Integer.parseInt(range));
        model.addAttribute("acters", acterService.allActers());
        return "redirect:/index";
    }

    @RequestMapping(value = "result/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getGameResult(@PathVariable String id) {
        System.out.println("ID = " + id);
        if (gameService.getGame(Long.parseLong(id)).isPresent()) {
            return gameService.getGame(Long.parseLong(id)).get().getResult();
        } else {
            return "Game with ID: " + id + " doesn't exist.";
        }
    }

}
