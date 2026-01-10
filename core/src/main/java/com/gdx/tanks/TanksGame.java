package com.gdx.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.gdx.tanks.entity.*;
import com.gdx.tanks.levels.Levels;
import java.util.ArrayList;

public class TanksGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private PlayerTank playerTank;
    private SecondPlayerTank secondPlayerTank;
    private ArrayList<Wall> defWalls;
    private int levelNumber = 1;
    private ArrayList<Bullet> bullets;
    private int WND_WIDTH = 800;
    private int WND_HEIGHT = 600;



    @Override
    public void create() {
        Levels levels = new Levels();
        batch = new SpriteBatch();

        playerTank = new PlayerTank(80, 32, 60);
        secondPlayerTank = new SecondPlayerTank(730, 550, 60);

        defWalls = levels.createDefaultWalls(WND_WIDTH, WND_HEIGHT);
        defWalls.addAll(levels.createLevelWalls(levelNumber));

        bullets = new ArrayList<>();


        System.out.println(Gdx.graphics.getHeight() + "x" + Gdx.graphics.getWidth());
    }



    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();


        playerTank.update(delta);
        secondPlayerTank.update(delta);


        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            bullets.add(playerTank.shoot());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bullets.add(secondPlayerTank.shoot());
        }


        if(checkCollisions(playerTank)){
            playerTank.getBounds().x = playerTank.getOldX();
            playerTank.getBounds().y = playerTank.getOldY();
        }
        if(checkCollisions(secondPlayerTank)){
            secondPlayerTank.getBounds().x = secondPlayerTank.getOldX();
            secondPlayerTank.getBounds().y = secondPlayerTank.getOldY();
        }


        for (Bullet bullet : bullets) {
            bullet.update(delta);
        }


        checkBulletWallCollisions();
        checkBulletTankCollisions();

        batch.begin();
        playerTank.render(batch);
        secondPlayerTank.render(batch);

        for (Wall wall : defWalls) {
            wall.render(batch);
        }

        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }

        batch.end();
    }

    private boolean checkCollisions(Tank tank){
        Rectangle tankBounds = tank.getBounds();

        for (Wall wall : defWalls) {
            if (tankBounds.overlaps(wall.getBounds())) {
                return true;
            }
        }
        return false;
    }

    private void checkBulletWallCollisions() {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);

            for (int j = defWalls.size() - 1; j >= 0; j--) {
                Wall wall = defWalls.get(j);


                if (bullet.getBounds().overlaps(wall.getBounds())) {
                    if (wall instanceof DestroyAbleWall) {
                        ((DestroyAbleWall) wall).takeDamage(50);
                        if (((DestroyAbleWall) wall).isDestroyed()) {
                            defWalls.remove(j);
                        }
                    }

                    bullet.setActive(false);
                    bullets.remove(i);
                    break;
                }
            }
        }
    }

    private void checkBulletTankCollisions() {
        Rectangle firstTankBounds = playerTank.getBounds();
        Rectangle secondTankBounds = secondPlayerTank.getBounds();

        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            if (bullet.getBounds().overlaps(firstTankBounds) && playerTank.getId() != bullet.getShooterId()) {
                playerTank.takeDamage(50);
                bullet.setActive(false);
                bullets.remove(i);
            }
            if (bullet.getBounds().overlaps(secondTankBounds) && secondPlayerTank.getId() != bullet.getShooterId()) {
                secondPlayerTank.takeDamage(50);
                bullet.setActive(false);
                bullets.remove(i);
            }
        }



    }

    @Override
    public void dispose() {
        batch.dispose();
        playerTank.dispose();
        secondPlayerTank.dispose();
    }
}
