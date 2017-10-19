package com.ar.game.handler;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class KeyboardController implements InputProcessor {
//    public static final Map<String, Integer> ALL_KEYS = Keys
    public boolean left,right,up,down;
    public Map<Integer, Boolean> KEY_MAP =
            IntStream.range(0, 256)
                    .boxed()
                    .collect(Collectors.toMap(Function.identity(), i -> false));
    public boolean isMouse1Down, isMouse2Down,isMouse3Down;
    public boolean isDragged;
    public Vector2 mouseLocation = new Vector2(0,0);

    @Override
    public boolean keyDown(int keycode) {
        KEY_MAP.put(keycode, true);
        return true;
    }
    @Override
    public boolean keyUp(int keycode) {
        KEY_MAP.put(keycode, false);
        return true;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == 0){
            isMouse1Down = true;
        }else if(button == 1){
            isMouse2Down = true;
        }else if(button == 2){
            isMouse3Down = true;
        }
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isDragged = false;
        //System.out.println(button);
        if(button == 0){
            isMouse1Down = false;
        }else if(button == 1){
            isMouse2Down = false;
        }else if(button == 2){
            isMouse3Down = false;
        }
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        isDragged = true;
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}