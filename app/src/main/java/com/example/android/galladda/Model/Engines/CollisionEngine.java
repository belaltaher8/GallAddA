package com.example.android.galladda.Model.Engines;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.android.galladda.EntityComponent.Components.ComponentType;
import com.example.android.galladda.EntityComponent.Components.DeathComponent;
import com.example.android.galladda.EntityComponent.Components.LivesComponent;
import com.example.android.galladda.EntityComponent.Components.PositionComponent;
import com.example.android.galladda.EntityComponent.Entities.Bullets.BadBulletEntity;
import com.example.android.galladda.EntityComponent.Entities.General.AbstractEntity;
import com.example.android.galladda.EntityComponent.Entities.Bullets.GoodBulletEntity;
import com.example.android.galladda.EntityComponent.Entities.Enemies.MathEnemyEntity;
import com.example.android.galladda.EntityComponent.Entities.General.EntityManager;
import com.example.android.galladda.EntityComponent.Entities.Enum.EntityType;
import com.example.android.galladda.EntityComponent.Entities.Player.PlayerEntity;

import java.util.ArrayList;

/**
 * @author Belal Taher
 * Created on 8/17/2017.
 * The CollisionEngine class uses the current position of entities and the dimensions of entities to draw invisible rectangles
 * that serve as collision detectors. If two of these invisible rectangles intersect, a collision is occuring.
 */

public class CollisionEngine extends AbstractEngine {
    
    public CollisionEngine(EntityManager aEM){
        super(aEM);
    }

    /**
     * This method checks if any of the player's bullets are intersecting with enemies and checks if
     * any of the enemies' bullets are intersecting with the player
     */
    @Override
    public void update() {
        updateGoodBullets();
        updateBadBullets();
    }

    /**
     * This method uses the bitmap of the enemies and player's bullets to draw collision detectors and see if are intersecting
     */
    private void updateGoodBullets(){

        //TODO: comment this algorithm
        boolean killed = false;
        ArrayList<AbstractEntity> myBullets =  myEM.getEntitiesOfType(EntityType.GoodBullet);
        ArrayList<AbstractEntity> myMathEnemies = myEM.getEntitiesOfType(EntityType.MathEnemy);
        int currentBulletIndex = 0;

        while(currentBulletIndex < myBullets.size()){
            GoodBulletEntity currentBullet = (GoodBulletEntity) myBullets.get(currentBulletIndex);
            PositionComponent bulletPos = (PositionComponent) currentBullet.getComponent(ComponentType.Position);
            Bitmap bulletBitmap = myEM.getBitmap(EntityType.GoodBullet);
            Rect myBulletCollisionSensor = new Rect((int) bulletPos.getX(), (int) bulletPos.getY(), (int) bulletPos.getX()+bulletBitmap.getWidth(), (int) bulletPos.getY() + bulletBitmap.getHeight());
            int currentEnemyIndex = 0;

            while(currentEnemyIndex < myMathEnemies.size()){

                MathEnemyEntity currentEnemy = (MathEnemyEntity) myMathEnemies.get(currentEnemyIndex);
                PositionComponent enemyPos = (PositionComponent) currentEnemy.getComponent(ComponentType.Position);
                Bitmap enemyBitmap = myEM.getBitmap(EntityType.MathEnemy);
                Rect myEnemyCollisionSensor = new Rect((int) enemyPos.getX(), (int) enemyPos.getY(), (int) enemyPos.getX() + enemyBitmap.getWidth(), (int) enemyPos.getY() + enemyBitmap.getHeight());

                if(myEnemyCollisionSensor.intersect(myBulletCollisionSensor)){
                    myBullets.remove(currentBullet);
                    currentEnemy.explode();
                    ((DeathComponent) currentEnemy.getComponent(ComponentType.Death)).die();
                    addEnemyDeath(EntityType.MathEnemy);
                    killed = true;
                    break;
                }
                else{
                    currentEnemyIndex++;
                }
            }

            if(killed == false){
                currentBulletIndex++;
            }
            else{
                killed = false;
            }

        }
    }

    private void updateBadBullets(){

        ArrayList<AbstractEntity> badBullets = myEM.getEntitiesOfType(EntityType.BadBullet);
        PlayerEntity myPlayer = myEM.getPlayerOne();
        int currentBulletIndex = 0;

        while(currentBulletIndex < badBullets.size()){

            BadBulletEntity currentBullet = (BadBulletEntity) badBullets.get(currentBulletIndex);
            PositionComponent bulletPos = (PositionComponent) currentBullet.getComponent(ComponentType.Position);
            Bitmap bulletBitmap = myEM.getBitmap(EntityType.BadBullet);
            Rect myBulletCollisionSensor = new Rect((int) bulletPos.getX(), (int) bulletPos.getY(), (int) bulletPos.getX()+bulletBitmap.getWidth(), (int) bulletPos.getY() + bulletBitmap.getHeight());

            PositionComponent myPlayerPos = (PositionComponent) myPlayer.getComponent(ComponentType.Position);
            Bitmap playerBitmap = myEM.getBitmap(EntityType.Player);
            int top = (int) myPlayerPos.getY() + 40;
            Rect myPlayerCollisionSensor = new Rect((int) myPlayerPos.getX(), top, (int) myPlayerPos.getX() + playerBitmap.getWidth(), top + (playerBitmap.getHeight() - 50));

            if(myPlayerCollisionSensor.intersect(myBulletCollisionSensor)){
                myPlayer.explode();
                badBullets.remove(currentBullet);
                LivesComponent myLives = (LivesComponent) myPlayer.getComponent(ComponentType.Lives);
                myLives.loseALife();
                break;
            }
            else{
                currentBulletIndex++;
            }
        }
    }

    /**
     * If an collision occurs between a player bullet and an enemy, this method updates the entity manager because
     * the challenge engine needs to know how many deaths of each enemy type have occurred
     *
     * @param ET the entity type of the enemy that just died
     */
    private void addEnemyDeath(EntityType ET){
        myEM.addEnemyDeath(ET);
    }
}
