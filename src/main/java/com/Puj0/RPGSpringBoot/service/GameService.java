package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.IRandom;
import com.Puj0.RPGSpringBoot.domain.Printer;
import com.Puj0.RPGSpringBoot.domain.Game;
import com.Puj0.RPGSpringBoot.domain.ThreadRandom;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.command.CommandDispatcher;
import com.Puj0.RPGSpringBoot.repository.ActerRepository;
import com.Puj0.RPGSpringBoot.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service("gameService")
public class GameService implements IGameService {

    @Autowired
    private ActerRepository acterRepository;

    @Autowired
    private GameRepository gameRepository;

    private int rounds;
    private Game game;
    private IRandom random = new ThreadRandom();

    @Override
    public Iterable<Acter> getAll() {
        return acterRepository.findAll();
    }

    @Override
    public void createGame(int rounds) {
        game = new Game.GameBuilder(rounds)
                .addPrinter(Printer.getInstance())
                .addActers(acterRepository.findAll())
                .addCommandDispatcher(new CommandDispatcher())
                .build();
    }

    @Override
    public String startGame(int rounds) {
        createGame(rounds);
        return game.runGame();
    }

    @Override
    public Optional<Game> getGame(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public void saveGame() {
        Assert.notNull(game, "game not initialized");
        gameRepository.save(game);
    }
}
