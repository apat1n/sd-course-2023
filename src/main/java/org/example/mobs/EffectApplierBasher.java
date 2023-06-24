package org.example.mobs;

import org.example.Pair;

public class EffectApplierBasher extends AbstractEffectApplier{

    Strategy oldStrategy;

    public EffectApplierBasher(Mob mob) {
        super(mob);
        oldStrategy = mob.strategy;
    }

    @Override
    public void setPosition(Pair<Integer, Integer> position) {

    }

    @Override
    public void move() {
        if (stunDuration != 3){
            affectedMob.strategy = new BashedStrategy();
            affectedMob.move();
            stunDuration++;
        } else {
            affectedMob.strategy = oldStrategy;
            affectedMob.move();
        }
    }

}
