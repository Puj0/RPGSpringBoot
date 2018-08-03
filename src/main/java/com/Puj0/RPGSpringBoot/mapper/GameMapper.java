package com.Puj0.RPGSpringBoot.mapper;

import com.Puj0.RPGSpringBoot.domain.Game;
import com.Puj0.RPGSpringBoot.view.GameView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {

    GameView mapGameView(Game game);

    Game mapGame(GameView gameView);
}
