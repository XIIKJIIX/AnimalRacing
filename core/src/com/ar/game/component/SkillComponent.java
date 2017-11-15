package com.ar.game.component;

import com.badlogic.ashley.core.Component;

import java.util.HashMap;

public class SkillComponent implements Component {
    public static enum Type {HEAL, DAMAGE, SLOW, NONE};
    public static enum ShotType {PROJECTILE, BULLET, LASER, OBJECT};
    public static enum ON_HIT {BOMB, BLOCK, THROUGH, GONE};

    // Map with Type and amount in percent of it's value to heal or damage
    public HashMap<Type, Float> type;
    public ShotType shotType;
    public ON_HIT onHit;

    public SkillComponent(ShotType shotType, ON_HIT onHit) {
        this.shotType = shotType;
        this.onHit = onHit;
    }

    public SkillComponent addSkill(Type type, float amount) {
        this.type.put(type, amount);
        return this;
    }
}
