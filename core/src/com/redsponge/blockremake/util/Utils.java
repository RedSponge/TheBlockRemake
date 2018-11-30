package com.redsponge.blockremake.util;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.redsponge.blockremake.components.BodyComponent;
import com.redsponge.blockremake.components.ComponentMappers;

public class Utils {

    public static void renderCentered(BitmapFont font, SpriteBatch batch, float y, CharSequence str, Viewport viewport) {
        GlyphLayout layout = new GlyphLayout(font, str);
        font.draw(batch, str, viewport.getWorldWidth() / 2 - layout.width / 2, y);
    }

    public static float secondsSince(long jumpStart) {
        return (TimeUtils.nanoTime() - jumpStart) / 1000000000f;
    }

    public static Vector2 getWorldPosition(Entity entity) {
        BodyComponent body = ComponentMappers.body.get(entity);
        return body.body.getWorldCenter().scl(Constants.PPM);
    }
}
