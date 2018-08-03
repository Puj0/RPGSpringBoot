package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.Game;
import com.Puj0.RPGSpringBoot.domain.GameActer;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.command.CommandDispatcher;
import com.Puj0.RPGSpringBoot.exception.ResourceNotFoundException;
import com.Puj0.RPGSpringBoot.view.GameParameters;
import com.Puj0.RPGSpringBoot.view.GameView;
import com.Puj0.RPGSpringBoot.mapper.GameMapper;
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

    private final ActerRepository acterRepository;

    private final GameRepository gameRepository;

    private final GameActerRepository gameActerRepository;

    private final GameMapper gameMapper;

    public GameService(ActerRepository acterRepository, GameRepository gameRepository, GameActerRepository gameActerRepository, GameMapper gameMapper) {
        this.acterRepository = acterRepository;
        this.gameRepository = gameRepository;
        this.gameActerRepository = gameActerRepository;
        this.gameMapper = gameMapper;
    }

    @Override
    public GameView getGame(Long id) {
        Optional<Game> game = gameRepository.findById(id);

        if (!game.isPresent()) {
            throw new ResourceNotFoundException("Game with ID: " + id + " doesn't exist.");
        }
        return gameMapper.mapGameView(game.get());
    }

    @Override
    public void startGame(GameParameters gameParameters) {
        List<Acter> acters;
        List<Long> ids = gameParameters.getIDs();

        if(ids == null){
            acters = acterRepository.findAll();
        } else {
            acters = acterRepository.findAllById(ids);
        }

        log.info("Rounds: {}", gameParameters.getRounds());
        Game game = new Game.GameBuilder(gameParameters.getRounds())
                .addActers(acters)
                .addCommandDispatcher(new CommandDispatcher())
                .build();
        game.runGame();
        gameRepository.save(game);

        Arrays.stream(game.getActers().getArray())
                .forEach(acterWithInitiative -> {
                    GameActer gameActer = new GameActer();
                    gameActer.setActer(acterWithInitiative.getActer());
                    gameActer.setGame(game);
                    gameActer.setHealthPoints(acterWithInitiative.getActer().getHealthPoints());
                    gameActerRepository.save(gameActer);
                });
    }

}
