package com.Puj0.RPGSpringBoot.service;

import com.Puj0.RPGSpringBoot.domain.game.Game;
import com.Puj0.RPGSpringBoot.domain.game.GameActer;
import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import com.Puj0.RPGSpringBoot.domain.command.CommandDispatcher;
import com.Puj0.RPGSpringBoot.exception.ResourceNotFoundException;
import com.Puj0.RPGSpringBoot.view.GameRequest;
import com.Puj0.RPGSpringBoot.view.GameView;
import com.Puj0.RPGSpringBoot.mapper.IGameMapper;
import com.Puj0.RPGSpringBoot.repository.IActerRepository;
import com.Puj0.RPGSpringBoot.repository.IGameActerRepository;
import com.Puj0.RPGSpringBoot.repository.IGameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GameService implements IGameService {

    private final IActerRepository acterRepository;

    private final IGameRepository gameRepository;

    private final IGameActerRepository gameActerRepository;

    private final IGameMapper gameMapper;

    public GameService(IActerRepository acterRepository, IGameRepository gameRepository, IGameActerRepository gameActerRepository, IGameMapper gameMapper) {
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

        return gameMapper.map(game.get());
    }

    @Override
    public void startGame(GameRequest gameRequest) {
        List<Acter> acters;
        List<Long> ids = gameRequest.getIDs();

        if(ids == null){
            acters = acterRepository.findAll();
        } else {
            acters = acterRepository.findAllById(ids);
        }

        log.info("Rounds: {}", gameRequest.getRounds());
        Game game = new Game.GameBuilder(gameRequest.getRounds())
                .addActers(acters)
                .addCommandDispatcher(new CommandDispatcher())
                .build();
        game = game.runGame();
        gameRepository.save(game);

        Game finalGame = game;
        Arrays.stream(game.getActers().getArray())
                .forEach(acterWithInitiative -> {
                    GameActer gameActer = new GameActer();
                    gameActer.setActer(acterWithInitiative.getActer());
                    gameActer.setGame(finalGame);
                    gameActer.setHealthPoints(acterWithInitiative.getActer().getHealthPoints());
                    gameActerRepository.save(gameActer);
                });
    }

    @Override
    public List<GameView> findByTotalRounds(int totalRounds) {
        if (gameRepository.findByTotalRounds(totalRounds).isEmpty()){
            return new ArrayList<>();
        } else {
            return gameRepository.findByTotalRounds(totalRounds).stream()
                    .map(gameMapper::map)
                    .collect(Collectors.toList());
        }
    }
}
