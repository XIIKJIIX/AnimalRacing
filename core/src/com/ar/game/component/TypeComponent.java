package com.ar.game.component;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {
    // Categories groupIndex bits
    public static final short PLAYER = 2;
    public static final short SCENERY = 4;
    // Never collide in same group if Negative
    public static final short SKILL = -2;
    public static final short ITEM = -4;

    private short group;

    public TypeComponent(short group) {
        this.group = group;
    }

    public short getGroup() {
        return group;
    }

    public void setGroup(short group) {
        this.group = group;
    }
}
