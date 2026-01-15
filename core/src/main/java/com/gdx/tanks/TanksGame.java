package com.gdx.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    private ArrayList<Water> water;
    private int WND_WIDTH = 800;
    private int WND_HEIGHT = 600;
    private int firstPlayerWins = 0;
    private int secondPlayerWins = 0;
    private boolean matchOver = false;


    @Override
    public void create() {
        Levels levels = new Levels();
        this.batch = new SpriteBatch();

        spawnTanks();

        this.defWalls = levels.createDefaultWalls(WND_WIDTH, WND_HEIGHT);
        this.defWalls.addAll(levels.createLevelWalls(levelNumber));

        this.bullets = new ArrayList<>();

        this.water = levels.createLevelWater(levelNumber);

        System.out.println(Gdx.graphics.getHeight() + "x" + Gdx.graphics.getWidth());
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (matchOver) {
            gameOver();
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                restartMatch();
            }
            return;
        }

        if (checkGameOver()) {
            resetGame();
        }

        float delta = Gdx.graphics.getDeltaTime();
        updateGame(delta);
        renderGame();
    }

    private void updateGame(float delta) {
        playerTank.update(delta);
        secondPlayerTank.update(delta);
        for (Bullet bullet : bullets) {
            bullet.update(delta);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Bullet bullet = playerTank.shoot();
            if (bullet != null) {
                bullets.add(bullet);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Bullet bullet = secondPlayerTank.shoot();
            if (bullet != null) {
                bullets.add(bullet);
            }
        }


        if (checkCollisions(playerTank)) {
            playerTank.getBounds().x = playerTank.getOldX();
            playerTank.getBounds().y = playerTank.getOldY();
        }
        if (checkCollisions(secondPlayerTank)) {
            secondPlayerTank.getBounds().x = secondPlayerTank.getOldX();
            secondPlayerTank.getBounds().y = secondPlayerTank.getOldY();
        }
        if (checkWaterCollisions(playerTank)) {
            playerTank.getBounds().x = playerTank.getOldX();
            playerTank.getBounds().y = playerTank.getOldY();
        }
        if (checkWaterCollisions(secondPlayerTank)) {
            secondPlayerTank.getBounds().x = secondPlayerTank.getOldX();
            secondPlayerTank.getBounds().y = secondPlayerTank.getOldY();

        }
        if (checkTanksCollisions()) {
            playerTank.getBounds().x = playerTank.getOldX();
            playerTank.getBounds().y = playerTank.getOldY();
            secondPlayerTank.getBounds().x = secondPlayerTank.getOldX();
            secondPlayerTank.getBounds().y = secondPlayerTank.getOldY();
        }

        checkBulletWallCollisions();
        checkBulletTankCollisions();

    }

    private void renderGame() {
        batch.begin();
        playerTank.render(batch);
        secondPlayerTank.render(batch);

        for (Wall wall : defWalls) {
            wall.render(batch);
        }

        for (Water water : water) {
            water.render(batch);
        }

        for (Bullet bullet : bullets) {
            bullet.render(batch);

        }

        batch.end();
    }

    private boolean checkCollisions(Tank tank) {
        Rectangle tankBounds = tank.getBounds();

        for (Wall wall : defWalls) {
            if (tankBounds.overlaps(wall.getBounds())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkWaterCollisions(Tank tank) {
        Rectangle tankBounds = tank.getBounds();

        for (Water water : water) {
            if (tankBounds.overlaps(water.getBounds())) {
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

    private boolean checkTanksCollisions() {
        Rectangle firstTankBounds = playerTank.getBounds();
        Rectangle secondTankBounds = secondPlayerTank.getBounds();
        if (firstTankBounds.overlaps(secondTankBounds)) {
            return true;
        }
        return false;
    }

    private void resetGame() {

        if (firstPlayerWins >= 2 || secondPlayerWins >= 2) {
            matchOver = true;
            return;
        }


        if (levelNumber < 3) {
            levelNumber += 1;
        } else {
            levelNumber = 1;
        }

        bullets.clear();
        defWalls.clear();
        water.clear();

        Levels level = new Levels();
        defWalls = level.createDefaultWalls(WND_WIDTH, WND_HEIGHT);
        defWalls.addAll(level.createLevelWalls(levelNumber));
        water = level.createLevelWater(levelNumber);

        respawnTanks();
    }

    private void respawnTanks() {
        playerTank.dispose();
        secondPlayerTank.dispose();

        spawnTanks();
    }

    private void spawnTanks() {
        this.playerTank = new PlayerTank(80, 30);
        this.secondPlayerTank = new SecondPlayerTank(730, 540);
    }

    private boolean checkGameOver() {
        if (!playerTank.isActive()) {
            secondPlayerWins += 1;
            return true;
        } else if (!secondPlayerTank.isActive()) {
            firstPlayerWins += 1;
            return true;
        }
        return false;
    }

    private void gameOver() {
        String winner = firstPlayerWins > secondPlayerWins ? "First Player Wins" : "Second Player Wins";
        String restartText = "'R' to restart game";


        BitmapFont font = new BitmapFont();


        float centerX = 400;
        float centerY = 300;

        batch.begin();
        font.setColor(1, 1, 1, 1);
        font.getData().setScale(3);
        GlyphLayout winnerLayout = new GlyphLayout(font, winner);
        font.draw(batch, winner, centerX - winnerLayout.width / 2, centerY);

        font.getData().setScale(2);
        GlyphLayout restartLayout = new GlyphLayout(font, restartText);
        font.draw(batch, restartText, centerX - restartLayout.width / 2, centerY - 100);

        batch.end();
    }

    public void restartMatch() {
        matchOver = false;
        firstPlayerWins = 0;
        secondPlayerWins = 0;
        levelNumber = 0;
        resetGame();
    }


    @Override
    public void dispose() {
        batch.dispose();
        playerTank.dispose();
        secondPlayerTank.dispose();
    }
}
