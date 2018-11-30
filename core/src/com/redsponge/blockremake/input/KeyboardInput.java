package com.redsponge.blockremake.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class KeyboardInput implements InputProcessor {

    InputHandle handle;

    public KeyboardInput(InputHandle handle) {
        this.handle = handle;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.RIGHT:
                handle.right = true;
                break;
            case Input.Keys.LEFT:
                handle.left = true;
                break;
            case Input.Keys.UP:
                handle.jump = true;
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.RIGHT:
                handle.right = false;
                break;
            case Input.Keys.LEFT:
                handle.left = false;
                break;
            case Input.Keys.UP:
                handle.jump = false;
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
