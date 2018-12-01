package com.redsponge.blockremake.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.Disposable;

/**
 * File created by Eran G aka RedSponge
 * Project: TheBlockRemake
 */

public class ParticlePools implements Disposable {

    public static final ParticlePools INSTANCE = new ParticlePools();
    public final ParticleEffectPool playerFollow;

    public ParticlePools() {
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("particles/test.p"), Gdx.files.internal(""));
        this.playerFollow = new ParticleEffectPool(particleEffect, 1, 1);
    }

    @Override
    public void dispose() {
        playerFollow.getFree();
    }
}
