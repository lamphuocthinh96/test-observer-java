package org.example.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Battle {
    List<Enemy> enemies = new ArrayList<>();
    List<Hero> heroes = new ArrayList<>();

    public void start() {
        heroes.add(new Hero(1, 5, 1, 5, 100, false));
        enemies.add(new Enemy(1, 2, 10, 2,10));
        enemies.add(new Enemy(2, 1, 5, 5, 30));
        enemies.add(new Enemy(3, 0.3f, 20, 5, 30));
        List<EnemyAction> enemyActions = new ArrayList<>();
        BattleChannel battleChannel = new BattleChannel();
        battleChannel.setEnemies(enemies);
        enemies.forEach(enemy -> {
            EnemyAction enemyAction = new EnemyAction();
            enemyAction.setEnemy(enemy);
            enemyAction.addPropertyChangeListener(battleChannel);
            enemyActions.add(enemyAction);
        });
        List<HeroAction> heroActions = new ArrayList<>();
        battleChannel.setHeroes(heroes);
        heroes.forEach(hero -> {
            HeroAction heroAction = new HeroAction();
            heroAction.setHero(hero);
            heroAction.addPropertyChangeListener(battleChannel);
            heroActions.add(heroAction);
        });
        battleChannel.initMap();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        enemyActions.forEach(executorService::submit);
        heroActions.forEach(executorService::submit);
        executorService.shutdown();
    }
}
