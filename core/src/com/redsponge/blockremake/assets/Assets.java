package com.redsponge.blockremake.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class Assets implements Disposable {

    public static final Assets INSTANCE = new Assets();

    public Assets() {
        AssetManager assetManager = new AssetManager();
        assetManager.load("atlases/platforms.atlas", TextureAtlas.class);

        assetManager.finishLoading();

        platforms = new Platforms(assetManager.get("atlases/platforms.atlas"));
    }

    public Platforms platforms;

    public static class Platforms implements Disposable {

        private Texture texture;
        public final NinePatch green;

        public Platforms(TextureAtlas atlas) {
            texture = atlas.getTextures().first();
            green = new NinePatch(atlas.findRegion("green"), 4, 4, 4, 4);
        }

        @Override
        public void dispose() {
            texture.dispose();
        }
    }

    @Override
    public void dispose() {
        platforms.dispose();
    }
}
