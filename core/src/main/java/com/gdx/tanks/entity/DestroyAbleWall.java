package com.gdx.tanks.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class DestroyAbleWall extends Wall {
    private int healthPoints;
    private boolean isActive;

    public DestroyAbleWall(float x, float y) {
        super(x, y);
        this.healthPoints = 150;
        this.isActive = true;
        this.texture = new Texture("wall-full-hp.png");
        this.bounds = new Rectangle(x, y, 40, 30);
    }


    public void takeDamage(int damage) {
        if (!isActive) return;

        healthPoints -= damage;

        if (healthPoints <= 0) {
            isActive = false;
            texture.dispose();
            texture = null;
        } else if (healthPoints <= 50) {
            texture.dispose();
            texture = new Texture("wall-half-hp.png");
        }
    }


    public void render(SpriteBatch batch) {
        if (isActive && texture != null) {
            batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
        }
    }


    public boolean isDestroyed() {
        return !isActive;
    }


    public int getHealthPoints() {
        return healthPoints;
    }

    public boolean isActive() {
        return isActive;
    }


    public Rectangle getBounds() {
        if (!isActive) {
            return new Rectangle(0, 0, 0, 0);
        }
        return bounds;
    }
}
