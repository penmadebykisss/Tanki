package com.gdx.tanks.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerTank extends Tank {
    public PlayerTank(int x, int y) {
        super(x, y, "player-tank.png");
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        this.oldX = bounds.getX();
        this.oldY = bounds.getY();

        float moveX = 0;
        float moveY = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) moveX -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveX += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) moveY += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) moveY -= 1;


        if (moveX != 0 || moveY != 0) {
            float length = (float) Math.sqrt(moveX * moveX + moveY * moveY);
            moveX /= length;
            moveY /= length;


            bounds.x += moveX * speed * deltaTime;
            bounds.y += moveY * speed * deltaTime;

            this.directionX = moveX;
            this.directionY = moveY;

            rotation = (float) Math.toDegrees(Math.atan2(moveY, moveX)) - 90;
        }
    }

}
