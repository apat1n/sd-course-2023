package org.example.mobs;

public abstract class AbstractEffectApplier extends Mob{

    protected Mob affectedMob;
    private int stunDuration = 0;
    AbstractEffectApplier(Strategy strategy) {
        super(strategy);
    }

    AbstractEffectApplier(Mob mob){
        super(mob.strategy);
        this.affectedMob = mob;
        this.stunDuration = 0;
    }

    @Override
    public void move() {
        if (stunDuration == 0){
            stunDuration++;
        } else {
            affectedMob.move();
        }
    }
}
