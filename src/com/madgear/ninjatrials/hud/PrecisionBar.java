/*
 * Ninja Trials is an old school style Android Game developed for OUYA & using
 * AndEngine. It features several minigames with simple gameplay.
 * Copyright 2013 Mad Gear Games <madgeargames@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.madgear.ninjatrials.hud;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.Sprite;

import com.madgear.ninjatrials.managers.ResourceManager;


/**
 * Energy bar with a cursor that moves from left to right, and in reverse direction when
 * the cursor reach the right margin.

 * Cursor moves from a minimum value to the maximum value (from 0 to 200), taking all the range
 * of values. The cursor makes a whole cycle in a time "timeRound".
 *
 * The cursor speed is calculated based on timeRound.
 *
 * @author Madgear Games
 */
@SuppressWarnings({ "static-access" })
public class PrecisionBar extends Entity {
    public static final float CURSOR_MIN_VALUE = 0f;
    public static final float CURSOR_MAX_VALUE = 200f;
    private float cursorValue = CURSOR_MIN_VALUE;
    private float speed;
    private int direction = 1;
    private float curXInit;
    private int semicycle = 0;
    private Sprite bar, cursor;

    /**
     * Contruct a PowerBarCursor object.
     *
     * @param posX Position axis X.
     * @param posY Position axis Y.
     * @param timeRound Time in seconds the cursor takes in complete a whole cycle. It's used to
     * calculate the cursor speed.
     */
    public PrecisionBar(float posX, float posY, float timeRound) {
        curXInit = posX - 100;
        semicycle = 0;
        bar = new Sprite(posX, posY,
                ResourceManager.getInstance().hudPowerBarCursor,
                ResourceManager.getInstance().engine.getVertexBufferObjectManager());
        cursor = new Sprite(curXInit, posY + 60,
                ResourceManager.getInstance().hudCursor,
                ResourceManager.getInstance().engine.getVertexBufferObjectManager());
        attachChild(bar);
        attachChild(cursor);
        speed = 2 * (CURSOR_MAX_VALUE - CURSOR_MIN_VALUE) / timeRound;
        setIgnoreUpdate(true);
    }

    /**
     * Sets the cursor value.
     * @param value The new cursor value.
     */
    public void setCursorValue(float value) {
        if (value >= CURSOR_MIN_VALUE && value <= CURSOR_MAX_VALUE)
            cursorValue = value;
    }

    /**
     * Continue moving the cursor.
     */
    public void start() {
        setIgnoreUpdate(false);
    }

    /**
     * Stops moving the cursor.
     */
    public void stop() {
        setIgnoreUpdate(true);
    }

    /**
     * Gets the power value.
     * @return An integer value from -100 (left) to 100 (right). 0 is the center value.
     */
    public int getPowerValue() {
        return Math.round(cursorValue) - 100;
    }
    
    /**
     * Gets the number of semi-cycles of the bar. A semi-cycle begins each time the cursor reach
     * the bar edge and changes his direction. The first semi-cycle is 0;
     * @return
     */
    public int getSemicycle() {
        return semicycle;
    }

    /**
     * Updates the value of the cursor position and controls when the cursor reach the left or
     * right margin, changing the cursor direction.
     * Cursor doesn't move if the time passed is higher than 0.2 s from the last update.
     */
    @Override
    protected void onManagedUpdate(final float pSecondsElapsed) {
        // controlamos que no se vaya el cursor por el retraso:
        if (pSecondsElapsed < 0.2)
            cursorValue += pSecondsElapsed * speed * direction;
        cursor.setX(curXInit + cursorValue);
        if (cursorValue >= CURSOR_MAX_VALUE) {
            direction = -1;
            semicycle++;
        }
        if (cursorValue <= CURSOR_MIN_VALUE) {
            direction = 1;
            semicycle++;
        }
        super.onManagedUpdate(pSecondsElapsed);
    }
}
