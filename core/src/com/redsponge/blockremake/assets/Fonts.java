package com.redsponge.blockremake.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {

    public static Fonts INSTANCE = new Fonts();

    public final BitmapFont DOTTY;
    public final BitmapFont LCD;

    public Fonts() {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 48;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/dotty.ttf"));
        DOTTY = generator.generateFont(parameter);
        generator.dispose();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/LCD_Solid.ttf"));
        LCD = generator.generateFont(parameter);
        generator.dispose();
    }

}
