package org.example.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BattleChannel implements PropertyChangeListener {

    private List<Enemy> enemies;
    private List<Hero> heroes;
    private float[][] map;

    public void initMap() {
        this.map = new float[enemies.size()][10];
    }

    public synchronized void propertyChange(PropertyChangeEvent evt) {
//        this.setEnemy((Enemy) evt.getNewValue());
//        System.out.println(evt);
        try {
            if (evt.getPropertyName().equals("move")) {
                updateBattle((Enemy) evt.getOldValue(), (int) (float) evt.getNewValue());
            }
            Enemy enemy = (Enemy) evt.getOldValue();
            float pos = (float) evt.getNewValue();
            for (Hero hero : heroes) {
                if (pos >= 5 && !hero.isBusy()) {
                    hero.setBusy(true);
                    enemy.setHp(enemy.getHp() - hero.dmg);
                    System.out.printf("E-%d be hit -%d to %d by hero %d\n",
                            enemy.getId(), hero.dmg, enemy.getHp(), hero.getId());
                    break;
                }
            }
            if (enemy.hp <= 0) {
                enemies.remove(enemy);
                System.out.println("E" + enemy.getId() + " is dead ");
            }
//            enemy.setHp(enemy.getHp() - );
            if (evt.getPropertyName().equals("attack")) {
//            Enemy enemy = (Enemy) evt.getOldValue();
                Iterator<Hero> iter = heroes.iterator();
                while(iter.hasNext()){
                    Hero hero = iter.next();
                    hero.setHp(hero.getHp() - enemy.getDmg());
                    System.out.printf("Hero %d HP = %d\n", hero.getId(), hero.getHp());
                    if (hero.getHp() <= 0) {
                        System.out.printf("Hero %d dead\n", hero.getId());
                        iter.remove();
                    }
                }
            }
            if (enemies.isEmpty()) {
                System.out.println("Congratulation! Mission passed");
                System.exit(0);
            }
            if (heroes.isEmpty()) {
                System.out.println("Mission failed!");
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void updateBattle(Enemy enemy, int pos) {
        System.out.print("E-" + enemy.getId());
        for (int i = 0; i < 10; i++) {
            if (i == (int) pos) System.out.print("*");
            else
                System.out.print("-");
        }
        System.out.println();
        map[enemy.id - 1][Math.min(pos, 9)] = 1;
    }
}