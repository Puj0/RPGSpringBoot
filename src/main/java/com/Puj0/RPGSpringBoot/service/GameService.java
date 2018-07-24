package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.Game;
import com.Puj0.RPGSpringBoot.domain.command.CommandDispatcher;
import com.Puj0.RPGSpringBoot.repository.ActerRepository;
import com.Puj0.RPGSpringBoot.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@Scope("prototype")
public class GameService implements IGameService {

    private ActerRepository acterRepository;

    private GameRepository gameRepository;

    public GameService(ActerRepository acterRepository, GameRepository gameRepository) {
        Logger logger = LoggerFactory.getLogger(GameService.class);
        logger.info("Napravio sam gameService");
        this.acterRepository = acterRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createGame(int rounds) {
        Game game = new Game.GameBuilder(rounds)
                .addActers(acterRepository.findAll())
                .addCommandDispatcher(new CommandDispatcher())
                .build();
        gameRepository.save(game);
        return game;
    }

    @Override
    public String startGame(int rounds) {
        Game game = createGame(rounds).runGame();
        gameRepository.save(game);
        return game.getResult();
    }

    @Override
    public String getGameResult(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            return game.get().getResult();
        }
        return "Game with ID: " + id + " doesn't exist.";
    }

}
