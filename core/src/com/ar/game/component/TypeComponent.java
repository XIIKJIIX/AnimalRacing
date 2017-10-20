package com.ar.game.component;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {
    // Categories bits
    public static final short PLAYER_1 = 2;
    public static final short PLAYER_2 = 4;
    public static final short SCENERY = 8;

    private short type;

    public TypeComponent(short type) {
        this.type = type;
    }

    public void set(short type) {
        this.type = type;
    }

    public short get() { return this.type; }
}
