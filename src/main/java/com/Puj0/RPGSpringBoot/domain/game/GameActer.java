package com.Puj0.RPGSpringBoot.domain.game;

import com.Puj0.RPGSpringBoot.domain.acters.Acter;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "game_acter")
public class GameActer {

    @Id
    @Column(name = "game_acter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "acter_id")
    private Acter acter;

    @Column(name = "health_points")
    private int healthPoints;

}
