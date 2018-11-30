package com.redsponge.blockremake.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Utils {

    public static void renderCentered(BitmapFont font, SpriteBatch batch, float y, CharSequence str, Viewport viewport) {
        GlyphLayout layout = new GlyphLayout(font, str);
        font.draw(batch, str, viewport.getWorldWidth() / 2 - layout.width / 2, y);
    }

    public static float secondsSince(long jumpStart) {
        return (TimeUtils.nanoTime() - jumpStart) / 1000000000f;
    }
}
