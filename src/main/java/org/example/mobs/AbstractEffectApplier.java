package org.example.mobs;

import org.example.Pair;

public abstract class AbstractEffectApplier extends Mob{

    public Mob affectedMob;
    protected int stunDuration = 0;
    AbstractEffectApplier(Strategy strategy) {
        super(strategy);
    }

    AbstractEffectApplier(Mob mob){
        super(mob.strategy);
        this.affectedMob = mob;
        this.stunDuration = 0;
    }

    @Override
    public boolean decresaseHealth(int attacked) {
        return affectedMob.decresaseHealth(attacked);
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return affectedMob.getPosition();
    }
}
