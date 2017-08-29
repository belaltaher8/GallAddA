package com.example.android.galladda.Model.Handlers;

import android.content.Context;

import com.example.android.galladda.EntityComponent.Components.ComponentType;
import com.example.android.galladda.EntityComponent.Components.PositionComponent;
import com.example.android.galladda.EntityComponent.Entities.General.AbstractEntity;
import com.example.android.galladda.EntityComponent.Entities.Enemies.MathEnemyEntity;
import com.example.android.galladda.EntityComponent.Entities.General.EntityManager;
import com.example.android.galladda.EntityComponent.Entities.Enum.EntityType;
import com.example.android.galladda.EntityComponent.Entities.Player.PlayerEntity;

import java.util.ArrayList;

/**
 * @author Belal Taher
 * Created on 8/15/2017.
 * The LevelHandler class constructs the entity manager. It also sets the starting position for all entities for any given level.
 *
 */

public class LevelHandler {

    //Level handler initializes the entity manager
    EntityManager currentLevelEM;

    //The player's starting position and enemies' starting positions are functions of the device's width and height
    private int screenWidth;
    private int screenHeight;

    public LevelHandler(Context myContext){
        currentLevelEM = new EntityManager(myContext);
        createPlayer();
    }

    /**
     * This method serves as communication between the front end and the level handler. The reason this is necessary is because
     * the level handler has to know the width and height of the screen to make sure the player and enemies are initialized in the same
     * relative location regardless of the device.
     *
     * @param screenDimensions an 2D array with 2 indices, screenDimensions[0] is the width and screenDimensions[1] is the height
     */
    public void takeInScreenDimensions(int[] screenDimensions){
        //extracts screen dimensions from array
        screenWidth = screenDimensions[0];
        screenHeight = screenDimensions[1];
    }

    /**
     * This method creates a new player entity and adds it to the entity manager
     */
    private void createPlayer(){
        //extracts Array List holding all players from entity manager
        ArrayList<AbstractEntity> myPlayers = currentLevelEM.getEntitiesOfType(EntityType.Player);

        //creates new player entity object and adds it to the array list
        myPlayers.add(new PlayerEntity());
    }

    /**
     * This method initializes enemies' positions and the type of enemies present given a certain level.
     *
     * @param currentLevel the positions and types of enemies is dependent on the current level
     */
    public void initializeLevel(int currentLevel){
        //Reset the player's position at the beginning of the level
        resetPlayerOne();

        //Checks the level
        if(currentLevel == 1){
            ArrayList<AbstractEntity> myEnemies = currentLevelEM.getEntitiesOfType(EntityType.MathEnemy);
            for(int i = 1 ; i < 4; i++){
                MathEnemyEntity enemyToAdd = new MathEnemyEntity();
                PositionComponent enemyPC = (PositionComponent) enemyToAdd.getComponent(ComponentType.Position);
                enemyPC.setY(100*i);
                enemyPC.setX(screenWidth/2);
                MathEnemyEntity enemyToAdd2 = new MathEnemyEntity();
                PositionComponent enemyPC2 = (PositionComponent) enemyToAdd2.getComponent(ComponentType.Position);
                enemyPC2.setY(100*i);
                enemyPC2.setX(screenWidth/2+200);
                MathEnemyEntity enemyToAdd3 = new MathEnemyEntity();
                PositionComponent enemyPC3 = (PositionComponent) enemyToAdd3.getComponent(ComponentType.Position);
                enemyPC3.setY(100*i);
                enemyPC3.setX(screenWidth/2-200);
                myEnemies.add(enemyToAdd);
                myEnemies.add(enemyToAdd2);
                myEnemies.add(enemyToAdd3);

            }
        }
    }

    /**
     * This method resets the position of player one as a function of the screen width and height
     */
    private void resetPlayerOne(){
        PositionComponent playerPC = (PositionComponent)currentLevelEM.getPlayerOne().getComponent(ComponentType.Position);
        playerPC.setX(screenWidth/2-75);
        playerPC.setY(screenHeight/1.5f);
    }

    /**
     * This method returns the current level entity manager
     *
     * @return the current level's entity manager
     */
    public EntityManager getCurrentLevelEM(){
        return currentLevelEM;
    }
}
