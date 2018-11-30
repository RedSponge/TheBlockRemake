package com.redsponge.blockremake.skins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.redsponge.blockremake.assets.Fonts;

public class Skins {

    public static Skins INSTANCE = new Skins();

    public final Skin UI;
    public Skins() {



        UI = new Skin(Gdx.files.internal("skins/ui.json"));
        UI.get(TextButton.TextButtonStyle.class).font = Fonts.INSTANCE.DOTTY;
    }

    public void dispose() {
        UI.dispose();
    }

}
