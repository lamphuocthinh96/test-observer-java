package org.example.game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class EnemyAction implements Runnable {
    private Enemy enemy;

    private float pos = 0;

    private PropertyChangeSupport support;

    public EnemyAction() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setEnemy(Enemy value) {
        this.enemy = value;

    }

    @Override
    public void run() {
        int randDelay = new Random().nextInt(5);
        while (this.enemy.hp > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            randDelay -= 1;
            if (randDelay <= 0) {
                pos += enemy.speed;
                support.firePropertyChange("move", this.enemy, pos);
                if (pos >= 10) {
                    System.out.println("Attacking....");
                    support.firePropertyChange("attack", this.enemy, pos);
                }
            }
        }
    }
}