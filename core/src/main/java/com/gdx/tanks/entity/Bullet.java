package com.gdx.tanks.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private Rectangle bounds;
    private float directionX;
    private float directionY;
    private float speed = 500;
    private float damage;
    private boolean active = true;
    private Texture texture;
    private int shooterId;

    public Bullet(float x, float y, float directionX, float directionY, int shooterId) {
        this.directionX = directionX;
        this.directionY = directionY;
        this.bounds = new Rectangle(x, y, 20, 10);
        this.texture = new Texture("bullet.png");
        this.shooterId = shooterId;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void update(float delta) {
        if (!active) return;

        bounds.x += directionX * speed * delta;
        bounds.y += directionY * speed * delta;

        if (bounds.x < -100 || bounds.x > 900 ||
            bounds.y < -100 || bounds.y > 700) {
            active = false;
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getShooterId() {
        return shooterId;
    }

    public float getDamage() {
        return damage;
    }

    ;
}
