package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.Printer;
import com.Puj0.RPGSpringBoot.domain.Game;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.command.CommandDispatcher;
import com.Puj0.RPGSpringBoot.repository.ActerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gameService")
public class GameService implements IGameService {

    @Autowired
    private ActerRepository acterRepository;
    private int rounds;
    private Game game;

    @Override
    public Iterable<Acter> getAll() {
        return acterRepository.findAll();
    }

    @Override
    public void createGame() {
        game = new Game.GameBuilder(rounds)
                .addPrinter(Printer.getInstance())
//                .addActers(acterRepository)
                .addCommandDispatcher(new CommandDispatcher())
                .build();
    }

    @Override
    public String startGame() {
        return String.valueOf(game.runGame());
    }
}
