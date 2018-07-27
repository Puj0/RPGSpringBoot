package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.Game;
import com.Puj0.RPGSpringBoot.domain.GameActer;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.command.CommandDispatcher;
import com.Puj0.RPGSpringBoot.repository.ActerRepository;
import com.Puj0.RPGSpringBoot.repository.GameActerRepository;
import com.Puj0.RPGSpringBoot.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GameService implements IGameService {

    private ActerRepository acterRepository;

    private GameRepository gameRepository;

    private GameActerRepository gameActerRepository;

    public GameService(ActerRepository acterRepository, GameRepository gameRepository, GameActerRepository gameActerRepository) {
        this.acterRepository = acterRepository;
        this.gameRepository = gameRepository;
        this.gameActerRepository = gameActerRepository;
    }

    @Override
    public String getGameResult(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            return game.get().getResult();
        }
        return "Game with ID: " + id + " doesn't exist.";
    }

    @Override
    public String startGame(int rounds, List<Long> idList) {

        List<Acter> acters = acterRepository.findAllById(idList);

        if (acters.isEmpty()){
            acters = acterRepository.findAll();
        }

        Game game = new Game.GameBuilder(rounds)
                .addActers(acters)
                .addCommandDispatcher(new CommandDispatcher())
                .build();
        game.runGame();
        gameRepository.save(game);

        Arrays.stream(game.getActers().getArray())
                .forEach(acterWithInitiative -> {
                    GameActer ga = new GameActer();
                    ga.setActer(acterWithInitiative.getActer());
                    ga.setGame(game);
                    ga.setHealthPoints(acterWithInitiative.getActer().getHealthPoints());
                    gameActerRepository.save(ga);
                });
        return game.getResult();

    }

}
