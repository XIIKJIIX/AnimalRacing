package com.ar.game.system;

import com.ar.game.component.*;
import com.ar.game.entity.IceBall;
import com.ar.game.entity.Skill;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

import static com.ar.game.component.StateComponent.State.*;
import static com.ar.game.constant.B2Dvars.PPM;

public class AnimateSystem extends IteratingSystem {

    private OrthographicCamera camera;
    private World world;
    private SpriteBatch batch;

    @Inject
    public AnimateSystem(OrthographicCamera camera, World world, SpriteBatch batch) {
        super(Family.all(AnimationComponent.class, StateComponent.class, TransformComponent.class).get());
        this.camera = camera;
        this.world = world;
        this.batch = batch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        StateComponent state = Mapper.state.get(entity);
        AnimationComponent animation = Mapper.animation.get(entity);
        TransformComponent transform = Mapper.transform.get(entity);
        PhysicsComponent physics = Mapper.physics.get(entity);
        DataComponent data = Mapper.data.get(entity);

        TextureRegion region;
        switch (state.state) {
            case JUMPING:
                region = (TextureRegion) animation.animation.get(JUMPING).getKeyFrame(state.stateTimer);
                break;
            case MOVING:
                region = (TextureRegion) animation.animation.get(MOVING).getKeyFrame(state.stateTimer, true);
                break;
            case NORMAL:
            case FALLING:
            default:
                region = (TextureRegion) animation.animation.get(NORMAL).getKeyFrame(state.stateTimer);
        }

        if ((physics.body.getLinearVelocity().x < 0 || !state.isRunningRight) && !region.isFlipX()) {
            region.flip(true, false);
            state.isRunningRight = false;
        } else if ((physics.body.getLinearVelocity().x > 0 || state.isRunningRight) && region.isFlipX()) {
            region.flip(true, false);
            state.isRunningRight = true;
        }
        float width = region.getRegionWidth();
        float height = region.getRegionHeight();
        if (entity instanceof IceBall) {
            batch.draw(
                    region,
                    transform.position.x - 0.8f,
                    transform.position.y - 0.27f,
                    width / PPM / 6,
                    height / PPM / 6
            );
        } else { // Player
            batch.draw(
                    region,
                    transform.position.x - (data.width / 2) - 0.15f,
                    transform.position.y - (data.height / 2) - 0.15f,
                    width / PPM / 4,
                    height / PPM / 4
            );
        }
    }

    @Override
    public void update(float deltaTime) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
        camera.update();
    }
}
