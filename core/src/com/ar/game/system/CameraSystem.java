package com.ar.game.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.google.inject.Inject;

public class CameraSystem extends EntitySystem {
    public static float acc = 1.001f;
    private OrthographicCamera camera;

    @Inject
    public CameraSystem(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        camera.position.x *= acc;
    }
}
