package com.gdx.tanks.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tank {
    protected Rectangle bounds;
    protected Texture texture;
    protected int speed;
    protected final int width = 30;
    protected final int height = 20;
    protected float rotation = 0;
    protected float oldX = 0;
    protected float oldY = 0;
    protected float directionX = 0;
    protected float directionY = 1;
    protected int healthPoints = 100;
    protected int id;
    protected static int nextId = 0;
    protected boolean isActive = true;

    public Tank(int x, int y, int speed,  String texturePath) {
        this.bounds = new Rectangle(x, y, width, height);
        this.speed = speed;
        this.texture = new Texture(texturePath);
        this.id = nextId++;
    }

    public Bullet shoot(){

        float startX, startY;


        float angleRad = (float)Math.toRadians(rotation + 90);


        float offsetX = (float)Math.cos(angleRad) * (height / 2 + 10);
        float offsetY = (float)Math.sin(angleRad) * (height / 2 + 10);

        startX = bounds.x + width / 2 + offsetX - 10;
        startY = bounds.y + height / 2 + offsetY - 5;


        float dirX = (float)Math.cos(angleRad);
        float dirY = (float)Math.sin(angleRad);

        return new Bullet(startX, startY, dirX, dirY, this.id);

    }

    public void takeDamage(float damage){
        if(!isActive) return;

        healthPoints -= damage;
        if(healthPoints <= 0){
            isActive = false;
            texture.dispose();
            texture = null;
        }
    }

    public void update(float dt){

    }

    public void render(SpriteBatch batch){
        batch.draw(texture, bounds.x, bounds.y, bounds.width / 2, bounds.height / 2, bounds.width, bounds.height, 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    public void dispose(){
        texture.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public float getOldX() {
        return oldX;
    }
    public float getOldY() {
        return oldY;
    }

    public float getDirectionX() {
        return directionX;
    }
    public float getDirectionY() {
        return directionY;
    }

    public int getId() {
        return id;
    }
}
