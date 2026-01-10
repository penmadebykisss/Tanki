package com.gdx.tanks.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Wall {
    protected Rectangle bounds;
    Texture texture;

    public Wall(float x, float y) {
        this.bounds = new Rectangle(x, y, 40,30);
        this.texture = new Texture("wall-full-hp.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }

}
