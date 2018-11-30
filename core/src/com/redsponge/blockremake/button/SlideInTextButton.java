package com.redsponge.blockremake.button;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SlideInTextButton extends TextButton {

    public SlideInTextButton(String text, Skin skin, int screenWidth, int y, float slideInDuration, float width) {
        super(text, skin);
        setWidth(width);
        this.setPosition(-getWidth(), y);
        this.addAction(Actions.moveTo(screenWidth / 2f-getWidth()/2, y, slideInDuration, Interpolation.swingOut));
    }
}
