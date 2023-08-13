package org.example.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero {

    int id;
    int dmg;
    int atkSpeed;
    int range;
    int hp;
    boolean busy = false;
}
