package com.Puj0.RPGSpringBoot.controller;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.service.IActerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acters")
class ActerController {

    private final static Logger logger = LoggerFactory.getLogger(ActerController.class);

    @Autowired
    private IActerService acterService;

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Acter> getActers() {
        return acterService.allActers();
    }

    @RequestMapping(value = "/createActers/{range}")
    public String createActers(@PathVariable("range") String range, Model model) {
        logger.info("Range = {}", range);
        acterService.createActers(Integer.parseInt(range));
        model.addAttribute("acters", acterService.allActers());
        return "redirect:/acters";
    }
}
