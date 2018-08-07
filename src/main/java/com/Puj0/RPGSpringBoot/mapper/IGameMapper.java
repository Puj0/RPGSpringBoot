package com.Puj0.RPGSpringBoot.mapper;

import com.Puj0.RPGSpringBoot.domain.game.Game;
import com.Puj0.RPGSpringBoot.view.GameView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IGameMapper {

    GameView map(Game game);

    Game map(GameView gameView);
}
