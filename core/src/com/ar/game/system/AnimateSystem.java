package com.ar.game.system;

import com.ar.game.component.*;
import com.ar.game.entity.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
            case HIT:
                region = (TextureRegion) animation.animation.get(HIT).getKeyFrame(state.stateTimer);
                break;
            case NORMAL:
            case FALLING:
            default:
                region = (TextureRegion) animation.animation.get(NORMAL).getKeyFrame(state.stateTimer);
        }

        if (entity instanceof Player || entity instanceof IceBall) {
            if ((physics.body.getLinearVelocity().x < 0 || !state.isRunningRight) && !region.isFlipX()) {
                region.flip(true, false);
                state.isRunningRight = false;
            } else if ((physics.body.getLinearVelocity().x > 0 || state.isRunningRight) && region.isFlipX()) {
                region.flip(true, false);
                state.isRunningRight = true;
            }
        }

        float width = region.getRegionWidth();
        float height = region.getRegionHeight();
        if (entity instanceof IceBall) {
            SkillComponent skill = Mapper.skill.get(entity);
            if (skill.isRight) {
                batch.draw(
                        region,
                        transform.position.x - 0.8f,
                        transform.position.y - 0.27f,
                        width / PPM / 5,
                        height / PPM / 5
                );
            } else {
                batch.draw(
                        region,
                        transform.position.x,
                        transform.position.y - 0.2f,
                        width / PPM / 5,
                        height / PPM / 5
                );
            }
        } else if (entity instanceof Bomb) {
                batch.draw(
                        region,
                        transform.position.x - 0.25f,
                        transform.position.y - 0.25f,
                        width / PPM / 1.25f,
                        height / PPM / 1.25f
                );
        } else if (entity instanceof Wall) {
            batch.draw(
                    region,
                    transform.position.x - 0.4f,
                    transform.position.y - 0.5f,
                    width / PPM / 1.15f,
                    height / PPM / 1.15f
            );
        } else { // Player
            PlayerComponent player = Mapper.player.get(entity);
            if (player.debufTime > 0) batch.setColor(0, 0, 0.9f, 1);
            batch.draw(
                    region,
                    transform.position.x - (data.width / 2) - 0.2f,
                    transform.position.y - (data.height / 2) - 0.2f,
                    width / PPM / 9,
                    height / PPM / 9
            );
        }
        batch.setColor(Color.WHITE);
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
