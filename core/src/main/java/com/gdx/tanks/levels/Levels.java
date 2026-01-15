package com.gdx.tanks.levels;

import com.badlogic.gdx.utils.Null;
import com.gdx.tanks.entity.Bullet;
import com.gdx.tanks.entity.DestroyAbleWall;
import com.gdx.tanks.entity.Wall;
import com.gdx.tanks.entity.Water;

import java.util.ArrayList;

public class Levels {
    public ArrayList<Wall> createDefaultWalls(int WND_WIDTH, int WND_HEIGHT) {
        ArrayList<Wall> defWalls = new ArrayList<>();

        for (int x = 0; x < WND_WIDTH; x += 40) {
            defWalls.add(new Wall(x, 0));
        }

        for (int x = 0; x < WND_WIDTH; x += 40) {
            defWalls.add(new Wall(x, WND_HEIGHT - 30));
        }

        for (int y = 0; y < WND_HEIGHT; y += 30) {
            defWalls.add(new Wall(0, y));
        }

        for (int y = 0; y < WND_HEIGHT; y += 30) {
            defWalls.add(new Wall(WND_WIDTH - 40, y));
        }
        return defWalls;
    }

    public ArrayList<Wall> createLevelWalls(int levelNumber) {
        ArrayList<Wall> levelWalls = new ArrayList<>();
        switch (levelNumber) {
            case 1:
                levelWalls = createFirstLevel();
                break;
            case 2:
                levelWalls = createSecondLevel();
                break;
            case 3:
                levelWalls = createThirdLevel();
        }
        return levelWalls;
    }

    private ArrayList<Wall> createFirstLevel() {
        ArrayList<Wall> levelWalls = new ArrayList<>();
        levelWalls.add(new DestroyAbleWall(120, 30));
        levelWalls.add(new DestroyAbleWall(120, 60));
        levelWalls.add(new DestroyAbleWall(80, 60));
        levelWalls.add(new DestroyAbleWall(400, 540));

        for (int y = 30; y < 150; y += 30) {
            levelWalls.add(new DestroyAbleWall(200, y));
        }

        for (int y = 30; y < 210; y += 30) {
            levelWalls.add(new DestroyAbleWall(280, y));
        }

        for (int y = 30; y < 90; y += 30) {
            levelWalls.add(new DestroyAbleWall(680, y));
        }

        for (int y = 210; y < 330; y += 30) {
            levelWalls.add(new DestroyAbleWall(80, y));
        }

        for (int y = 90; y < 150; y += 30) {
            levelWalls.add(new DestroyAbleWall(560, y));
        }

        for (int y = 420; y < 570; y += 30) {
            levelWalls.add(new DestroyAbleWall(560, y));
        }

        for (int y = 270; y < 390; y += 30) {
            levelWalls.add(new DestroyAbleWall(440, y));
        }

        for (int x = 40; x < 200; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 210));
        }

        for (int x = 760; x > 520; x -= 40) {
            levelWalls.add(new DestroyAbleWall(x, 150));
        }

        for (int x = 760; x > 480; x -= 40) {
            levelWalls.add(new DestroyAbleWall(x, 210));
        }

        for (int x = 760; x > 600; x -= 40) {
            levelWalls.add(new DestroyAbleWall(x, 450));
        }

        for (int x = 40; x < 320; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 480));
        }

        for (int x = 200; x < 360; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 300));
        }
        for (int x = 280; x < 480; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 390));
        }
        for (int x = 760; x > 640; x -= 40) {
            for (int y = 300; y < 390; y += 30) {
                levelWalls.add(new DestroyAbleWall(x, y));
            }
        }
        return levelWalls;
    }

    private ArrayList<Wall> createSecondLevel() {
        ArrayList<Wall> levelWalls = new ArrayList<>();

        for (int x = 40; x < 320; x += 40) {
            for (int y = 270; y < 330; y += 30) {
                levelWalls.add(new Wall(x, y));
            }
        }
        for (int x = 480; x < 760; x += 40) {
            for (int y = 270; y < 330; y += 30) {
                levelWalls.add(new Wall(x, y));
            }
        }

        return levelWalls;
    }

    private ArrayList<Wall> createThirdLevel() {
        ArrayList<Wall> levelWalls = new ArrayList<>();

        for (int y = 180; y < 300; y += 30) {
            levelWalls.add(new DestroyAbleWall(360, y));
        }
        for (int x = 280; x < 440; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 240));
        }

        for (int x = 40; x < 160; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 60));
            levelWalls.add(new DestroyAbleWall(x, 90));
        }
        for (int y = 60; y < 150; y += 30) {
            levelWalls.add(new DestroyAbleWall(40, y));
            levelWalls.add(new DestroyAbleWall(120, y));
        }

        for (int x = 640; x < 720; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 510));
            levelWalls.add(new DestroyAbleWall(x, 540));
        }
        for (int y = 510; y < 570; y += 30) {
            levelWalls.add(new DestroyAbleWall(640, y));
        }

        for (int i = 0; i < 5; i++) {
            levelWalls.add(new DestroyAbleWall(160 + i * 40, 180 + i * 30));
            levelWalls.add(new DestroyAbleWall(560 - i * 40, 360 + i * 30));
        }

        for (int x = 200; x < 320; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 300));
            levelWalls.add(new DestroyAbleWall(x, 360));
        }
        for (int y = 300; y < 390; y += 30) {
            levelWalls.add(new DestroyAbleWall(200, y));
            levelWalls.add(new DestroyAbleWall(280, y));
        }

        for (int x = 480; x < 600; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 180));
            levelWalls.add(new DestroyAbleWall(x, 240));
        }
        for (int y = 180; y < 270; y += 30) {
            levelWalls.add(new DestroyAbleWall(480, y));
            levelWalls.add(new DestroyAbleWall(560, y));
        }

        for (int y = 120; y < 240; y += 30) {
            levelWalls.add(new DestroyAbleWall(400, y));
        }
        for (int y = 360; y < 480; y += 30) {
            levelWalls.add(new DestroyAbleWall(320, y));
        }

        for (int x = 240; x < 360; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 120));
        }
        for (int x = 440; x < 560; x += 40) {
            levelWalls.add(new DestroyAbleWall(x, 480));
        }

        levelWalls.add(new DestroyAbleWall(280, 180));
        levelWalls.add(new DestroyAbleWall(440, 360));
        levelWalls.add(new DestroyAbleWall(360, 420));
        levelWalls.add(new DestroyAbleWall(480, 300));

        return levelWalls;
    }

    public ArrayList<Water> createLevelWater(int levelNumber) {
        ArrayList<Water> levelWaters = new ArrayList<>();
        switch (levelNumber) {
            case 1:
                return new ArrayList<>();
            case 2:
                levelWaters = createSecondLevelWater();
                break;
        }
        return levelWaters;
    }

    private ArrayList<Water> createSecondLevelWater() {
        ArrayList<Water> levelWaters = new ArrayList<>();
        for (int x = 320; x < 480; x += 40) {
            for (int y = 270; y < 330; y += 30) {
                levelWaters.add(new Water(x, y));
            }
        }
        return levelWaters;
    }
}
