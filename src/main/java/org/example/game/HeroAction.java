package org.example.game;

import lombok.Data;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Random;

@Data
public class HeroAction implements Runnable {
    private Hero hero;

    private PropertyChangeSupport support;

    public HeroAction() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }


    @Override
    public void run() {
        int randHit = new Random().nextInt(12);
        while (hero.hp > 0) {
            try {
                Thread.sleep(1000);
                if(hero.busy) {
                    Thread.sleep(hero.atkSpeed * 1000L);
                    hero.busy = false;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            System.out.println("HP = " + this.hero.hp);
        }
    }
}