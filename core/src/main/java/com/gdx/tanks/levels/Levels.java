package com.gdx.tanks.levels;

import com.gdx.tanks.entity.Bullet;
import com.gdx.tanks.entity.DestroyAbleWall;
import com.gdx.tanks.entity.Wall;

import java.util.ArrayList;

public class Levels {
    public ArrayList<Wall> createDefaultWalls(int WND_WIDTH, int WND_HEIGHT){
        ArrayList<Wall> defWalls = new ArrayList<>();

        for(int x = 0; x < WND_WIDTH; x+=40){
            defWalls.add(new Wall(x, 0));
        }

        for(int x = 0; x < WND_WIDTH; x+=40){
            defWalls.add(new Wall(x, WND_HEIGHT - 30));
        }

        for(int y = 0; y < WND_HEIGHT; y+=30){
            defWalls.add(new Wall(0, y));
        }

        for(int y = 0; y < WND_HEIGHT; y+=30){
            defWalls.add(new Wall(WND_WIDTH - 40, y));
        }
        return defWalls;
    }

    public ArrayList<Wall> createLevelWalls(int levelNumber){
        ArrayList<Wall> levelWalls = new ArrayList<>();
        switch (levelNumber)
            {
            case 1:
                levelWalls = createFirstLevel();
                break;
//            case 2:
//                levelWalls = createSecondLevel();
//                break;
//            case 3:
//                levelWalls = createThirdLevel();
//                break;
            }
        return levelWalls;
    }

    private ArrayList<Wall> createFirstLevel(){
        ArrayList<Wall> levelWalls = new ArrayList<>();
        levelWalls.add(new DestroyAbleWall(120, 30));
        levelWalls.add(new DestroyAbleWall(120, 60));
        levelWalls.add(new DestroyAbleWall(80, 60));
        levelWalls.add(new DestroyAbleWall(400, 540));

        for(int y = 30; y<150; y+=30){
            levelWalls.add(new DestroyAbleWall(200, y));
        }

        for(int y = 30; y<210; y+=30){
            levelWalls.add(new DestroyAbleWall(280, y));
        }

        for(int y = 30; y<90; y+=30){
            levelWalls.add(new DestroyAbleWall(680, y));
        }

        for(int y = 210; y<330; y+=30){
            levelWalls.add(new DestroyAbleWall(80, y));
        }

        for(int y = 90; y<150; y+=30 ){
            levelWalls.add(new DestroyAbleWall(560, y));
        }

        for(int y = 420; y<570; y+=30){
            levelWalls.add(new DestroyAbleWall(560, y));
        }

        for(int y = 270; y < 390; y+=30){
            levelWalls.add(new DestroyAbleWall(440, y));
        }

        for(int x = 40; x<200; x+=40){
            levelWalls.add(new DestroyAbleWall(x, 210));
        }

        for (int x = 760; x>520; x -=40){
            levelWalls.add(new DestroyAbleWall(x, 150));
        }

        for(int x = 760; x>480; x -=40){
            levelWalls.add(new DestroyAbleWall(x, 210));
        }

        for(int x = 760; x>600; x -=40){
            levelWalls.add(new DestroyAbleWall(x, 450));
        }

        for(int x = 40; x< 320; x+=40){
            levelWalls.add(new DestroyAbleWall(x, 480));
        }

        for(int x = 200; x <360; x+=40){
            levelWalls.add(new DestroyAbleWall(x, 300));
        }
        for(int x = 280; x <480; x+=40){
            levelWalls.add(new DestroyAbleWall(x, 390));
        }
        for(int x = 760; x>640; x -=40){
            for(int y = 300; y<390;  y+=30){
                levelWalls.add(new DestroyAbleWall(x, y));
            }
        }
        return levelWalls;
    }
}
