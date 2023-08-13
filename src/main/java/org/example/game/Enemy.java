package org.example.game;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Enemy {
    public int id;
    public float speed; // m / sec
    public int dmg;
    public int atkSpeed=1;
    public int hp = 20;

    public Enemy(float speed, int dmg, int hp) {
        this.speed = speed;
        this.dmg = dmg;
        this.hp = hp;
    }

//    public EnemyAction enemyAction;
}
