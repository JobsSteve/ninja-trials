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


package com.madgear.ninjatrials.managers;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.debug.Debug;

import com.madgear.ninjatrials.NinjaTrials;


public class ResourceManager {

    private static final TextureOptions mTransparentTextureOption = TextureOptions.BILINEAR;

    // ResourceManager Singleton instance
    private static ResourceManager INSTANCE;

    /* The variables listed should be kept public, allowing us easy access
       to them when creating new Sprites, Text objects and to play sound files */
    public NinjaTrials activity;
    public Engine engine;
    public Context context;
    public float cameraWidth;
    public float cameraHeight;
    public TextureManager textureManager;

    // MAIN MENU:
    public static ITextureRegion mainTitleTR;
    public static ITextureRegion mainTitlePattern1TR;

    // MAIN OPTIONS MENU:
    public static ITextureRegion mainOptionsPatternTR;
    public static ITextureRegion mainOptionsSoundBarsActiveTR;
    public static ITextureRegion mainOptionsSoundBarsInactiveTR;

    // CONTROLLER OPTIONS MENU:
    public static ITextureRegion controllerOptionsPatternTR;
    public static ITextureRegion controllerOuyaTR;
    public static ITextureRegion controllerMarksTR;

    // HUD:
    public static ITextureRegion hudPowerBarCursorTR;
    public static ITextureRegion hudCursorTR;
    public static ITextureRegion hudPowerBarPushTR;

    public static ITextureRegion hudAngleBarCursorTR;

    public static ITextureRegion runLineBar;
    public static ITextureRegion runMarkP1;
    public static ITextureRegion runMarkP2;
    public static ITiledTextureRegion cutHead;
    public static ITiledTextureRegion jumpHead;
    public static ITiledTextureRegion runHead;
    public static ITiledTextureRegion shurikenHead;


    // JUMP TRIAL:
    public static ITextureRegion jumpBg1Bamboo;
    public static ITextureRegion jumpBg1StoneStatues;
    public static ITextureRegion jumpBg2BambooForest1;
    public static ITextureRegion jumpBg3BambooForest2;
    public static ITextureRegion jumpBg4Mount;
    public static ITextureRegion jumpBg5Pagoda;
    public static ITextureRegion jumpBg6Clouds;
    public static ITextureRegion jumpBg7Lake;
    public static ITextureRegion jumpBg8MountFuji;
    public static ITextureRegion jumpBg9Sky;
    public static ITiledTextureRegion jumpChRyoko;
    public static ITiledTextureRegion jumpChSho;
    public static ITiledTextureRegion jumpEffectPreparation;
    public static ITiledTextureRegion jumpEffectWallKick;

    // CUT TRIAL:
    public static ITiledTextureRegion cutShoTR;
    public static ITextureRegion cutTreeTopTR;
    public static ITextureRegion cutTreeBottomTR;
    public static ITextureRegion cutCandleTopTR;
    public static ITextureRegion cutCandleBottomTR;
    public static ITextureRegion cutCandleLightTR;
    public static ITextureRegion cutEyesTR;
    public static ITextureRegion cutBackgroundTR;
    public static ITextureRegion cutSweatDropTR;
    public static ITiledTextureRegion cutCharSparkleTR;
    public static ITextureRegion cutSwordSparkle1TR;
    public static ITiledTextureRegion cutSwordSparkle2TR;
    public static ITextureRegion cutHudBarTR;
    public static ITextureRegion cutHudCursorTR;


    // CUT SCENE SOUNDS:
    public static Music cutMusic;
    public static Sound cutEyesZoom;
    public static Sound cutKatana1;
    public static Sound cutKatana2;
    public static Sound cutKatana3;
    public static Sound cutKatanaWhoosh;
    public static Sound cutThud;


	// RUN SCENE
    public static ITiledTextureRegion runSho;
    public static ITiledTextureRegion runRyoko;
    public static ITextureRegion runBgFloor;
    public static ITextureRegion runBgTreesFront;
    public static ITextureRegion runBgTreesBack;
    public static ITextureRegion runDushStart;
    public static ITextureRegion runDushContinue;

    // SHURIKEN SCENE
    public static ITextureRegion shurikenBackground;
    public static ITiledTextureRegion shurikenRyokoHands;
    public static ITiledTextureRegion shurikenRyokoLose;
    public static ITiledTextureRegion shurikenRyokoWin;
    public static ITiledTextureRegion shurikenShoHands;
    public static ITiledTextureRegion shurikenShoLose;
    public static ITiledTextureRegion shurikenShoWin;
    public static ITiledTextureRegion shurikenShuriken;
    public static ITiledTextureRegion shurikenStrawman1;
    public static ITiledTextureRegion shurikenStrawman2;
    public static ITextureRegion shurikenStrawman3;
    public static ITextureRegion shurikenTempShuriken;
    public static ITextureRegion shurikenTempStrawman;

    // HOW TO PLAY
    public static ITextureRegion howToPlayArrow;
    public static ITextureRegion howToPlayButton;
    public static ITextureRegion howToPlayDigitalPad;

    // CHARACTER PROFILE
    public static ITextureRegion characterProfileBackground1;
    public static ITextureRegion characterProfileBackground2;
    public static ITextureRegion characterProfileRyoko;
    public static ITextureRegion characterProfileSho;

    // MENU ACHIEVEMENTS
    public static ITextureRegion menuAchievementsContainerDescription;
    public static ITextureRegion menuAchievementsContainerIcons;
    public static ITextureRegion menuAchievementsIconsBig;
    public static ITextureRegion menuAchievementsIconsSmall;
    public static ITextureRegion menuAchievementsIngameContainer;
    public static ITextureRegion menuAchievementsSuccessStamp;

    // MENU MAP
    public static ITiledTextureRegion menuMapBackgroundMarks;
    public static ITextureRegion menuMapBackground;
    public static ITiledTextureRegion menuMapChRyoko;
    public static ITiledTextureRegion menuMapChSho;
    public static ITiledTextureRegion menuMapDrawings;
    public static ITiledTextureRegion menuMapScroll;

    // MENU PAUSE
    public static ITextureRegion menuPauseBambooFrame;

    // MENU SELECTED
    public static ITiledTextureRegion menuSelectChRyoko;
    public static ITiledTextureRegion menuSelectChSho;
    public static ITextureRegion menuSelectClouds;
    public static ITextureRegion menuSelectDifficulty;
    public static ITextureRegion menuSelectMoon;
    public static ITextureRegion menuSelectRoof;
    public static ITextureRegion menuSelectSky;

    // RESULTS SCENE LOSE
    public static ITextureRegion loseCharRyokoTR;
    public static ITextureRegion loseCharShoTR;
    public static ITextureRegion loseBgTR;

    // RESULTS SCENE LOSE SOUNDS
    public static Music loseMusic;
    public static Sound loseYouLose;


    // RESULTS SCENE WIN
    public static ITextureRegion winBg;
    public static ITextureRegion winScroll;
    public static ITiledTextureRegion winDrawings;
    public static ITextureRegion winCharSho;
    public static ITextureRegion winCharRyoko;
    public static ITiledTextureRegion winStampRanking;
    public static final int WIN_STAMP_INDEX_THUG = 0;
    public static final int WIN_STAMP_INDEX_NINJA = 1;
    public static final int WIN_STAMP_INDEX_NINJA_MASTER = 2;
    public static final int WIN_STAMP_INDEX_GRAND_MASTER = 3;

    // RESULTS SCENE WIN SOUNDS
    public static Music winMusic;
    public static Sound winPointsSum;
    public static Sound winYouWin;
    public static Sound winPointsTotal;

    // INTRO1
    public static ITextureRegion intro1Gradient;
    public static ITextureRegion intro1Logo;
    public static ITextureRegion intro1Ryoko;
    public static ITextureRegion intro1Shapes;
    public static ITextureRegion intro1Sho;
    public static ITextureRegion intro1TrialCut;
    public static ITextureRegion intro1TrialJump;
    public static ITextureRegion intro1TrialRun;
    public static ITextureRegion intro1TrialThrow;

    // INTRO2
    public static ITextureRegion intro2CommonBg;
    public static ITiledTextureRegion intro2CommonMaster;
    public static ITextureRegion intro2CommonMasterTextBalloon;
    public static ITiledTextureRegion intro2CommonRyoko;
    public static ITextureRegion intro2CommonRyokoTextBalloon;
    public static ITiledTextureRegion intro2CommonSho;
    public static ITextureRegion intro2CommonShoTextBalloon;
    public static ITextureRegion intro2RyokoBalloonText;
    public static ITextureRegion intro2RyokoBg;
    public static ITextureRegion intro2Ryoko;
    public static ITextureRegion intro2ShoBalloonText;
    public static ITextureRegion intro2ShoBg;
    public static ITextureRegion intro2Sho;


    // GAME OVER SOUNDS
    public static Music gameOverMusic;
    public static Sound gameOver;

    // ENDING
    public static ITextureRegion endingCreditsBackground;
    public static ITextureRegion endingCreditsCategories;
    public static ITextureRegion endingCreditsLogoAndengine;
    public static ITextureRegion endingCreditsLogoEstudioevergreen;
    public static ITextureRegion endingRyokoEasyBg;
    public static ITextureRegion endingRyokoEasy;
    public static ITextureRegion endingShoEasyBg;
    public static ITextureRegion endingShoEasy;

    // FONTS:
    public Font fontSmall;        // pequeño
    public Font fontMedium;        // mediano
    public Font fontBig;        // grande
    public Font fontXBig;        // Extra grande


    //public BuildableBitmapTextureAtlas mBitmapTextureAtlas;
    public ITiledTextureRegion mTiledTextureRegion;
    //public ITextureRegion mSpriteTextureRegion;

    public Music music;
    public Sound mSound;

    public float cameraScaleFactorX = 1;
    public float cameraScaleFactorY = 1;

    // MUSICS
    public static Music credits;
    public static Music ending;
    public static Music intro1;
    public static Music intro2;
    public static Music map;
    public static Music records;
    public static Music trialJump;
    public static Music trialRun;
    public static Music trialShurikens;

    // SOUNDS
    public static Sound effectEyeGleam;
    public static Sound effectMasterHit;
    public static Sound effectSweatDrop;
    public static Sound judge1;
    public static Sound judge2;
    public static Sound judge3;
    public static Sound judge4;
    public static Sound judge5;
    public static Sound judge6;
    public static Sound judge7;
    public static Sound judge8;
    public static Sound judge9;
    public static Sound judgeExcellent;
    public static Sound judgeGood;
    public static Sound judgeGo;
    public static Sound judgeGreat;
    public static Sound judgeReady;
    public static Sound menuAchievement;
    public static Sound menuActivate;
    public static Sound menuBack;
    public static Sound menuFocus;
    public static Sound menuIntro1;
    public static Sound menuLogoMadgear;
    public static Sound menuRank;
    public static Sound ryokoCutCut;
    public static Sound ryokoCutLose;
    public static Sound ryokoCutWin;
    public static Sound ryokoJumpCharge;
    public static Sound ryokoJumpFall;
    public static Sound ryokoJumpHop;
    public static Sound ryokoJumpLose;
    public static Sound ryokoJumpWin;
    public static Sound ryokoMenuContinue;
    public static Sound ryokoMenuGameOver;
    public static Sound ryokoRunCharge;
    public static Sound ryokoRunLose;
    public static Sound ryokoRunStart;
    public static Sound ryokoRunWin;
    public static Sound ryokoShurikenLose;
    public static Sound ryokoShurikenThrow;
    public static Sound ryokoShurikenWin;
    public static Sound shoCutCut;
    public static Sound shoCutLose;
    public static Sound shoCutWin;
    public static Sound shoJumpCharge;
    public static Sound shoJumpFall;
    public static Sound shoJumpHop;
    public static Sound shoJumpLose;
    public static Sound shoJumpWin;
    public static Sound shoMenuContinue;
    public static Sound shoMenuGameOver;
    public static Sound shoRunCharge;
    public static Sound shoRunLose;
    public static Sound shoRunStart;
    public static Sound shoRunWin;
    public static Sound shoShurikenLose;
    public static Sound shoShurikenThrow;
    public static Sound shoShurikenWin;
    public static Sound trialCutCandleBlowOut;
    public static Sound trialCutCandleShowingCut;
    public static Sound trialCutCandleThud;
    public static Sound trialCutCandleWobble;
    public static Sound trialCutCandleWoobleThud;
    public static Sound trialCutEyesZoomV2;
    public static Sound trialCutKatanaWhoosh3;
    public static Sound trialJumpFall;
    public static Sound trialJumpReach;
    public static Sound trialJumpSlip;
    public static Sound trialJumpTap1;
    public static Sound trialJumpTap2;
    public static Sound trialJumpThud;
    public static Sound trialJumpWhoosh1;
    public static Sound trialJumpWhoosh2;
    public static Sound trialJumpWhoosh3;
    public static Sound trialJumpWobble;
    public static Sound trialRunTap1;
    public static Sound trialRunTap2;
    public static Sound trialRunTap3;
    public static Sound trialRunWind1Start;
    public static Sound trialRunWind2Running;
    public static Sound trialRunWind3End;
    public static Sound trialShurikenStrawmanAscend;
    public static Sound trialShurikenStrawmanDescend;
    public static Sound trialShurikenStrawmanDestroyed;
    public static Sound trialShurikenStrawmanHit;
    public static Sound trialShurikenStrawmanMove;
    public static Sound trialShurikenThrowing;

    // Inicializa el manejador:
    public static void setup(NinjaTrials pActivity, Engine pEngine, Context pContext,
            float pCameraWidth, float pCameraHeight){
        getInstance().activity = pActivity;
        getInstance().engine = pEngine;
        getInstance().context = pContext;
        getInstance().cameraWidth = pCameraWidth;
        getInstance().cameraHeight = pCameraHeight;
        getInstance().textureManager = pActivity.getTextureManager();
    }

    // Constructor:
    ResourceManager(){
        // The constructor is of no use to us
    }

    public synchronized static ResourceManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ResourceManager();
        }
        return INSTANCE;
    }

    // Cada escena debe tener sus métodos para cargar y descargar recursos (metodo load y unload).
    // tanto en gráficos como música y sonido.
    // Deben ser "synchronized".

    /**
     * Loads the main menu resources.
     */
    public synchronized void loadMainMenuResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menus/");

        // Main Menu Ninja Trials Logo:
        if(mainTitleTR==null) {
            BitmapTextureAtlas mainTitleT = new BitmapTextureAtlas(
                    textureManager, 756, 495, mTransparentTextureOption);
            mainTitleTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    mainTitleT, activity, "menu_main_title.png", 0, 0);
            mainTitleT.load();
        }

        // Main Menu Pattern:
        if (mainTitlePattern1TR == null) {
            BuildableBitmapTextureAtlas mainTitlePattern1T = new BuildableBitmapTextureAtlas(
                    textureManager, 400, 300, TextureOptions.REPEATING_BILINEAR);
            mainTitlePattern1TR = BitmapTextureAtlasTextureRegionFactory
                    .createFromAsset(mainTitlePattern1T, activity, "menu_main_pattern_1.png");
            try {
                mainTitlePattern1T.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
                mainTitlePattern1T.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }
    }

    /**
     * Unloads the main menu resources.
     */
    public synchronized void unloadMainMenuResources() {
        if(mainTitleTR!=null) {
            if(mainTitleTR.getTexture().isLoadedToHardware()) {
                mainTitleTR.getTexture().unload();
                mainTitleTR = null;
            }
        }
        if(mainTitlePattern1TR!=null) {
            if(mainTitlePattern1TR.getTexture().isLoadedToHardware()) {
                mainTitlePattern1TR.getTexture().unload();
                mainTitlePattern1TR = null;
            }
        }
    }

    /**
     * Loads the main option menu resources.
     */
    public synchronized void loadOptionResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menus/");

        // Sound bars:
        BitmapTextureAtlas mainOptionsSoundBarsT = new BitmapTextureAtlas(textureManager, 575, 220,
                mTransparentTextureOption);
        ITextureRegion mainOptionsSoundBarsTR = BitmapTextureAtlasTextureRegionFactory.
                createFromAsset(mainOptionsSoundBarsT, activity, "menu_options_volume.png", 0, 0);
        mainOptionsSoundBarsT.load();
        mainOptionsSoundBarsActiveTR = TextureRegionFactory.
                extractFromTexture(mainOptionsSoundBarsT, 0, 0, 575, 110, false);
        mainOptionsSoundBarsInactiveTR = TextureRegionFactory.
                extractFromTexture(mainOptionsSoundBarsT, 0, 111, 575, 109, false);

        // Option Menu Pattern:
        if (mainOptionsPatternTR == null) {
            BuildableBitmapTextureAtlas mainOptionsPatternT = new BuildableBitmapTextureAtlas(
                    textureManager, 390, 361, TextureOptions.REPEATING_BILINEAR);
            mainOptionsPatternTR = BitmapTextureAtlasTextureRegionFactory
                    .createFromAsset(mainOptionsPatternT, activity, "menu_main_pattern_2.png");
            try {
                mainOptionsPatternT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
                mainOptionsPatternT.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }
    }

    /**
     * Unloads the option menu resources.
     */
    public synchronized void unloadOptionResources() {
        if(mainOptionsSoundBarsActiveTR!=null) {
            if(mainOptionsSoundBarsActiveTR.getTexture().isLoadedToHardware()) {
                mainOptionsSoundBarsActiveTR.getTexture().unload();
                mainOptionsSoundBarsActiveTR = null;
            }
        }
        if(mainOptionsSoundBarsInactiveTR!=null) {
            if(mainOptionsSoundBarsInactiveTR.getTexture().isLoadedToHardware()) {
                mainOptionsSoundBarsInactiveTR.getTexture().unload();
                mainOptionsSoundBarsInactiveTR = null;
            }
        }
        if(mainOptionsPatternTR!=null) {
            if(mainOptionsPatternTR.getTexture().isLoadedToHardware()) {
                mainOptionsPatternTR.getTexture().unload();
                mainOptionsPatternTR = null;
            }
        }
    }

    /**
     * Loads the main option menu resources.
     *     public static ITextureRegion controllerOuyaTR;
    public static ITextureRegion controllerMarksTR;
     */
    public synchronized void loadControllerOptionResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menus/");

        // Controller ouya:
        if(controllerOuyaTR==null) {
            BitmapTextureAtlas controllerOuyaT = new BitmapTextureAtlas(textureManager, 1164, 791,
                    mTransparentTextureOption);
            controllerOuyaTR = BitmapTextureAtlasTextureRegionFactory.
                    createFromAsset(
                            controllerOuyaT, activity, "menu_options_controller_ouya.png", 0, 0);
            controllerOuyaT.load();
        }

        // Controller marks:
        if(controllerMarksTR==null) {
            BitmapTextureAtlas controllerMarksT = new BitmapTextureAtlas(textureManager, 1195, 717,
                    mTransparentTextureOption);
            controllerMarksTR = BitmapTextureAtlasTextureRegionFactory.
                    createFromAsset(
                            controllerMarksT, activity, "menu_options_controller_marks.png", 0, 0);
            controllerMarksT.load();
        }

        // Controller Option Pattern:
        if (controllerOptionsPatternTR == null) {
            BuildableBitmapTextureAtlas controllerOptionsPatternT = new BuildableBitmapTextureAtlas(
                    textureManager, 319, 319, TextureOptions.REPEATING_BILINEAR);
            controllerOptionsPatternTR = BitmapTextureAtlasTextureRegionFactory
                    .createFromAsset(controllerOptionsPatternT, activity,
                            "menu_main_pattern_3.png");
            try {
                controllerOptionsPatternT.build(
                        new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                            BitmapTextureAtlas>(0, 0, 0));
                controllerOptionsPatternT.load();
            } catch (TextureAtlasBuilderException e) {
                Debug.e(e);
            }
        }
    }

    /**
     * Unloads the option menu resources.
     */
    public synchronized void unloadControllerOptionResources() {
        if(controllerOuyaTR!=null) {
            if(controllerOuyaTR.getTexture().isLoadedToHardware()) {
                controllerOuyaTR.getTexture().unload();
                controllerOuyaTR = null;
            }
        }
        if(controllerMarksTR!=null) {
            if(controllerMarksTR.getTexture().isLoadedToHardware()) {
                controllerMarksTR.getTexture().unload();
                controllerMarksTR = null;
            }
        }
        if(controllerOptionsPatternTR!=null) {
            if(controllerOptionsPatternTR.getTexture().isLoadedToHardware()) {
                controllerOptionsPatternTR.getTexture().unload();
                controllerOptionsPatternTR = null;
            }
        }
    }

    public synchronized void loadHUDResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/hud/");

        // Barra power cursor:
        if(hudPowerBarCursorTR==null) {
            BitmapTextureAtlas hudPowerBarCursorT = new BitmapTextureAtlas(
                    textureManager, 240, 120, mTransparentTextureOption);
            hudPowerBarCursorTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    hudPowerBarCursorT, activity, "hud_precision_indicator.png", 0, 0);
            hudPowerBarCursorT.load();
        }
        
        // Angle Bar:
        if (hudAngleBarCursorTR == null) {
        	BitmapTextureAtlas hudAngleBarCursorT = new BitmapTextureAtlas(
                    textureManager, 353, 257, mTransparentTextureOption);
            hudAngleBarCursorTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
            		hudAngleBarCursorT, activity, "hud_angle_indicator.png", 0, 0);
            hudAngleBarCursorT.load();
        }

        if(hudCursorTR==null) {
            BitmapTextureAtlas hudCursorT = new BitmapTextureAtlas(textureManager, 59, 52,
                    mTransparentTextureOption);
            hudCursorTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(hudCursorT,
                    activity, "hud_angle_cursor.png", 0, 0);
            hudCursorT.load();
        }

        // Cursor:
        if(hudCursorTR==null) {
            BitmapTextureAtlas hudCursorT = new BitmapTextureAtlas(textureManager, 59, 52,
                    mTransparentTextureOption);
            hudCursorTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(hudCursorT,
                    activity, "hud_precision_cursor.png", 0, 0);
            hudCursorT.load();
        }

        // Barra power push:
        if(hudPowerBarPushTR==null) {
            BitmapTextureAtlas hudPowerBarPushT = new BitmapTextureAtlas(textureManager, 120, 240,
                    mTransparentTextureOption);
            hudPowerBarPushTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    hudPowerBarPushT, activity, "hud_power_indicator.png", 0, 0);
            hudPowerBarPushT.load();
        }
        // LineBar
        if (runLineBar == null) {
            BitmapTextureAtlas runLineBarBit = new BitmapTextureAtlas(textureManager, 1012, 80,
                    mTransparentTextureOption);
            runLineBar = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    runLineBarBit, activity, "run_line_bar.png", 0, 0);
            runLineBarBit.load();
        }
        // LineMark
        BitmapTextureAtlas runMarkBit = new BitmapTextureAtlas(textureManager, 140, 116,
                mTransparentTextureOption);
        ITextureRegion runMark = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                runMarkBit, activity, "run_line_mark.png", 0, 0);
        runMarkBit.load();
        if (runMarkP1 == null)
            runMarkP1 = TextureRegionFactory.extractFromTexture(runMarkBit, 0, 0, 70, 116, false);
        if (runMarkP2 == null)
            runMarkP2 = TextureRegionFactory.extractFromTexture(runMarkBit, 70, 0, 140, 116, false);
        // RunHead
        if (runHead == null) {
            BuildableBitmapTextureAtlas runHeadBit = new BuildableBitmapTextureAtlas(
                    textureManager, 660, 440, mTransparentTextureOption);
            runHead = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    runHeadBit, context, "hud_head_run.png", 3, 2);
            try {
                runHeadBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            runHeadBit.load();
        }
        // CutHead
        if (cutHead == null) {
            BuildableBitmapTextureAtlas cutHeadBit = new BuildableBitmapTextureAtlas(
                    textureManager, 660, 440, mTransparentTextureOption);
            cutHead = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    cutHeadBit, context, "hud_head_cut.png", 3, 2);
            try {
                cutHeadBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            cutHeadBit.load();
        }
        // JumpHead
        if (jumpHead == null) {
            BuildableBitmapTextureAtlas jumpHeadBit = new BuildableBitmapTextureAtlas(
                    textureManager, 660, 440, mTransparentTextureOption);
            jumpHead = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    jumpHeadBit, context, "hud_head_jump.png", 3, 2);
            try {
                jumpHeadBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            jumpHeadBit.load();
        }
        // ShurikenHead
        if (shurikenHead == null) {
            BuildableBitmapTextureAtlas shurikenHeadBit = new BuildableBitmapTextureAtlas(
                    textureManager, 660, 440, mTransparentTextureOption);
            shurikenHead = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    shurikenHeadBit, context, "hud_head_shuriken.png", 3, 2);
            try {
                shurikenHeadBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            shurikenHeadBit.load();
        }
    }

    public synchronized void unloadHUDResources() {
        if(hudPowerBarCursorTR!=null) {
            if(hudPowerBarCursorTR.getTexture().isLoadedToHardware()) {
                hudPowerBarCursorTR.getTexture().unload();
                hudPowerBarCursorTR = null;
            }
        }
        if(hudAngleBarCursorTR!=null) {
            if(hudAngleBarCursorTR.getTexture().isLoadedToHardware()) {
            	hudAngleBarCursorTR.getTexture().unload();
            	hudAngleBarCursorTR = null;
            }
        }
        if(hudCursorTR!=null) {
            if(hudCursorTR.getTexture().isLoadedToHardware()) {
                hudCursorTR.getTexture().unload();
                hudCursorTR = null;
            }
        }
        if(hudPowerBarPushTR!=null) {
            if(hudPowerBarPushTR.getTexture().isLoadedToHardware()) {
                hudPowerBarPushTR.getTexture().unload();
                hudPowerBarPushTR = null;
            }
        }
        if (runLineBar != null && runLineBar.getTexture().isLoadedToHardware()) {
                runLineBar.getTexture().unload();
                runLineBar = null;
        }
        if (runMarkP1 != null && runMarkP1.getTexture().isLoadedToHardware()) {
                runMarkP1.getTexture().unload();
                runMarkP1 = null;
        }
        if (runMarkP2 != null && runMarkP2.getTexture().isLoadedToHardware()) {
                runMarkP2.getTexture().unload();
                runMarkP2 = null;
        }
        if (runHead != null && runHead.getTexture().isLoadedToHardware()) {
                runHead.getTexture().unload();
                runHead = null;
        }
        if (cutHead != null && cutHead.getTexture().isLoadedToHardware()) {
            cutHead.getTexture().unload();
            cutHead = null;
        }
        if (jumpHead != null && jumpHead.getTexture().isLoadedToHardware()) {
            jumpHead.getTexture().unload();
            jumpHead = null;
        }
        if (shurikenHead != null && shurikenHead.getTexture().isLoadedToHardware()) {
            shurikenHead.getTexture().unload();
            shurikenHead = null;
        }
    }

    public synchronized void loadJumpSceneResources() {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trial_jump/");

        if (jumpBg1Bamboo == null) {
            BitmapTextureAtlas jumpBg1BambooT = new BitmapTextureAtlas(textureManager, 89, 1080,
                    mTransparentTextureOption);
            jumpBg1Bamboo = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    jumpBg1BambooT, activity, "jump_bg_1_bamboo.png", 0, 0);
            jumpBg1BambooT.load();
        }
        if (jumpBg1StoneStatues == null) {
            BitmapTextureAtlas jumpBg1StoneStatuesT = new BitmapTextureAtlas(textureManager, 442, 310,
                    mTransparentTextureOption);
            jumpBg1StoneStatues = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    jumpBg1StoneStatuesT, activity, "jump_bg_1_stone_statues.png", 0, 0);
            jumpBg1StoneStatuesT.load();
        }
        if (jumpBg2BambooForest1 == null) {
            BitmapTextureAtlas jumpBg2BambooForest1T = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            jumpBg2BambooForest1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    jumpBg2BambooForest1T, activity, "jump_bg_2_bamboo_forest_1.png", 0, 0);
            jumpBg2BambooForest1T.load();
        }
        if (jumpBg3BambooForest2 == null) {
            BitmapTextureAtlas jumpBg3BambooForest2T = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            jumpBg3BambooForest2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    jumpBg3BambooForest2T, activity, "jump_bg_3_bamboo_forest_2.png", 0, 0);
            jumpBg3BambooForest2T.load();
        }
        if (jumpBg4Mount == null) {
            BitmapTextureAtlas jumpBg4MountT = new BitmapTextureAtlas(textureManager, 1920, 794,
                    mTransparentTextureOption);
            jumpBg4Mount = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    jumpBg4MountT, activity, "jump_bg_4_mount.png", 0, 0);
            jumpBg4MountT.load();
        }
        if (jumpBg5Pagoda == null) {
            BitmapTextureAtlas jumpBg5PagodaT = new BitmapTextureAtlas(textureManager, 650, 952,
                    mTransparentTextureOption);
            jumpBg5Pagoda = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    jumpBg5PagodaT, activity, "jump_bg_5_pagoda.png", 0, 0);
            jumpBg5PagodaT.load();
        }
        if (jumpBg6Clouds == null) {
            BitmapTextureAtlas jumpBg6CloudsT = new BitmapTextureAtlas(textureManager, 1920, 503,
                    mTransparentTextureOption);
            jumpBg6Clouds = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    jumpBg6CloudsT, activity, "jump_bg_6_clouds.png", 0, 0);
            jumpBg6CloudsT.load();
        }
        if (jumpBg7Lake == null) {
            BitmapTextureAtlas jumpBg7LakeT = new BitmapTextureAtlas(textureManager, 1920, 550,
                    mTransparentTextureOption);
            jumpBg7Lake = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    jumpBg7LakeT, activity, "jump_bg_7_lake.png", 0, 0);
            jumpBg7LakeT.load();
        }
        if (jumpBg8MountFuji == null) {
            BitmapTextureAtlas jumpBg8MountFujiT = new BitmapTextureAtlas(textureManager, 1920, 806,
                    mTransparentTextureOption);
            jumpBg8MountFuji = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    jumpBg8MountFujiT, activity, "jump_bg_8_mount_fuji.png", 0, 0);
            jumpBg8MountFujiT.load();
        }
        if (jumpBg9Sky == null) {
            BitmapTextureAtlas jumpBg9SkyT = new BitmapTextureAtlas(textureManager, 1920, 1471,
                    mTransparentTextureOption);
            jumpBg9Sky = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    jumpBg9SkyT, activity, "jump_bg_9_sky.png", 0, 0);
            jumpBg9SkyT.load();
        }

        //if (jumpChRyoko == null) {
        //    BuildableBitmapTextureAtlas jumpChRyokoBit = new BuildableBitmapTextureAtlas(
        //            textureManager, 2000, 2982, mTransparentTextureOption);
        //    jumpChRyoko = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
        //            jumpChRyokoBit, context, "jump_ch_ryoko.png", 4, 6);
        //    try {
        //        jumpChRyokoBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
        //                BitmapTextureAtlas>(0, 0, 0));
        //    }
        //    catch (TextureAtlasBuilderException e) {
        //        e.printStackTrace();
        //    }
        //    jumpChRyokoBit.load();
        //}

        //if (jumpChSho == null) {
        //    BuildableBitmapTextureAtlas jumpChShoBit = new BuildableBitmapTextureAtlas(
        //            textureManager, 2000, 2982, mTransparentTextureOption);
        //    jumpChSho = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
        //            jumpChShoBit, context, "jump_ch_sho.png", 4, 6);
        //    try {
        //        jumpChShoBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
        //                BitmapTextureAtlas>(0, 0, 0));
        //    }
        //    catch (TextureAtlasBuilderException e) {
        //        e.printStackTrace();
        //    }
        //    jumpChShoBit.load();
        //}

        //if (jumpEffectPreparation == null) {
        //    BuildableBitmapTextureAtlas jumpEffectPreparationBit = new BuildableBitmapTextureAtlas(
        //            textureManager, 590, 406, mTransparentTextureOption);
        //    jumpEffectPreparation = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
        //            jumpEffectPreparationBit, context, "jump_effect_preparation.png", 2, 2);
        //    try {
        //        jumpEffectPreparationBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
        //                BitmapTextureAtlas>(0, 0, 0));
        //    }
        //    catch (TextureAtlasBuilderException e) {
        //        e.printStackTrace();
        //    }
        //    jumpEffectPreparationBit.load();
        //}

        //if (jumpEffectWallKick == null) {
        //    BuildableBitmapTextureAtlas jumpEffectWallKickBit = new BuildableBitmapTextureAtlas(
        //            textureManager, 406, 590, mTransparentTextureOption);
        //    jumpEffectWallKick = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
        //            jumpEffectWallKickBit, context, "jump_effect_wall_kick.png", 2, 2);
        //    try {
        //        jumpEffectWallKickBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
        //                BitmapTextureAtlas>(0, 0, 0));
        //    }
        //    catch (TextureAtlasBuilderException e) {
        //        e.printStackTrace();
        //    }
        //    jumpEffectWallKickBit.load();
        //}
    }


    public synchronized void unloadJumpSceneResources() {
        if (jumpBg1Bamboo != null && jumpBg1Bamboo.getTexture().isLoadedToHardware()) {
                jumpBg1Bamboo.getTexture().unload();
                jumpBg1Bamboo = null;
        }
        if (jumpBg1StoneStatues != null && jumpBg1StoneStatues.getTexture().isLoadedToHardware()) {
                jumpBg1StoneStatues.getTexture().unload();
                jumpBg1StoneStatues = null;
        }
        if (jumpBg2BambooForest1 != null && jumpBg2BambooForest1.getTexture().isLoadedToHardware()) {
                jumpBg2BambooForest1.getTexture().unload();
                jumpBg2BambooForest1 = null;
        }
        if (jumpBg3BambooForest2 != null && jumpBg3BambooForest2.getTexture().isLoadedToHardware()) {
                jumpBg3BambooForest2.getTexture().unload();
                jumpBg3BambooForest2 = null;
        }
        if (jumpBg4Mount != null && jumpBg4Mount.getTexture().isLoadedToHardware()) {
                jumpBg4Mount.getTexture().unload();
                jumpBg4Mount = null;
        }
        if (jumpBg5Pagoda != null && jumpBg5Pagoda.getTexture().isLoadedToHardware()) {
                jumpBg5Pagoda.getTexture().unload();
                jumpBg5Pagoda = null;
        }
        if (jumpBg6Clouds != null && jumpBg6Clouds.getTexture().isLoadedToHardware()) {
                jumpBg6Clouds.getTexture().unload();
                jumpBg6Clouds = null;
        }
        if (jumpBg7Lake != null && jumpBg7Lake.getTexture().isLoadedToHardware()) {
                jumpBg7Lake.getTexture().unload();
                jumpBg7Lake = null;
        }
        if (jumpBg8MountFuji != null && jumpBg8MountFuji.getTexture().isLoadedToHardware()) {
                jumpBg8MountFuji.getTexture().unload();
                jumpBg8MountFuji = null;
        }
        if (jumpBg9Sky != null && jumpBg9Sky.getTexture().isLoadedToHardware()) {
                jumpBg9Sky.getTexture().unload();
                jumpBg9Sky = null;
        }
        if (jumpChRyoko != null && jumpChRyoko.getTexture().isLoadedToHardware()) {
                jumpChRyoko.getTexture().unload();
                jumpChRyoko = null;
        }
        if (jumpChSho != null && jumpChSho.getTexture().isLoadedToHardware()) {
                jumpChSho.getTexture().unload();
                jumpChSho = null;
        }
        if (jumpEffectPreparation != null && jumpEffectPreparation.getTexture().isLoadedToHardware()) {
                jumpEffectPreparation.getTexture().unload();
                jumpEffectPreparation = null;
        }
        if (jumpEffectWallKick != null && jumpEffectWallKick.getTexture().isLoadedToHardware()) {
                jumpEffectWallKick.getTexture().unload();
                jumpEffectWallKick = null;
        }
    }

    // Recursos para la escena de corte:
    public synchronized void loadCutSceneResources() {
        // Texturas:
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trial_cut/");

        // Sho:
        if(cutShoTR==null) {
            BuildableBitmapTextureAtlas cutShoT = new BuildableBitmapTextureAtlas(
                    textureManager, 1742, 1720, mTransparentTextureOption);
            cutShoTR = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    cutShoT, context, "cut_ch_sho_cut_anim.png", 2, 2);
            try {
                cutShoT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            } catch (TextureAtlasBuilderException e) { e.printStackTrace(); }
            cutShoT.load();
        }

        // Arbol:
        BitmapTextureAtlas cutTreeT = new BitmapTextureAtlas(textureManager, 640, 950,
                mTransparentTextureOption);
        ITextureRegion cutTreeTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                cutTreeT, activity, "cut_breakable_tree.png", 0, 0);
        cutTreeT.load();
        cutTreeTopTR = TextureRegionFactory.extractFromTexture(cutTreeT, 0, 0, 640, 403, false);
        cutTreeBottomTR = TextureRegionFactory.extractFromTexture(cutTreeT, 0, 404, 640, 546,
                false);

        // Farol:
        BitmapTextureAtlas cutCandleT = new BitmapTextureAtlas(textureManager, 310, 860,
                mTransparentTextureOption);
        ITextureRegion cutCandleTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                cutCandleT, activity, "cut_breakable_candle_base.png", 0, 0);
        cutCandleT.load();
        cutCandleTopTR = TextureRegionFactory.extractFromTexture(cutCandleT, 0, 0, 310, 515, false);
        cutCandleBottomTR = TextureRegionFactory.extractFromTexture(cutCandleT, 0, 516, 310, 344,
                false);

        // Luz del farol:
        BitmapTextureAtlas cutCandleLightT = new BitmapTextureAtlas(textureManager, 760, 380,
                mTransparentTextureOption);
        ITextureRegion cutCandleLightAllTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                cutCandleLightT, activity, "cut_breakable_candle_light.png", 0, 0);
        cutCandleLightT.load();
        cutCandleLightTR = TextureRegionFactory.extractFromTexture(cutCandleLightT, 0, 0, 388, 380,
                false);

        // Espada 2:
        if(cutSwordSparkle2TR==null) {
            BuildableBitmapTextureAtlas cutSword2T = new BuildableBitmapTextureAtlas(
                    textureManager, 1358, 1034, mTransparentTextureOption);
            cutSwordSparkle2TR = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    cutSword2T, context, "cut_sword_sparkle2.png", 2, 2);
            try {
                cutSword2T.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            } catch (TextureAtlasBuilderException e) { e.printStackTrace(); }
            cutSword2T.load();
        }

        // Ojos:
        if(cutEyesTR==null) {
            BitmapTextureAtlas cutEyesT =  new BitmapTextureAtlas(textureManager, 1416, 611,
                    mTransparentTextureOption);
            cutEyesTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    cutEyesT, activity, "cut_ch_sho_eyes.png", 0, 0);
            cutEyesT.load();
        }

        // Fondo:
        if(cutBackgroundTR==null) {
            BitmapTextureAtlas cutBackgroundT = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            cutBackgroundTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    cutBackgroundT, activity, "cut_background.png", 0, 0);
            cutBackgroundT.load();
        }

        // Gota:
        if(cutSweatDropTR==null) {
            BitmapTextureAtlas cutSweatDropT = new BitmapTextureAtlas(textureManager, 46, 107,
                    mTransparentTextureOption);
            cutSweatDropTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    cutSweatDropT, activity, "cut_ch_sweatdrop.png", 0, 0);
            cutSweatDropT.load();
        }

        // Character eye sparkle:
        if(cutCharSparkleTR==null) {
            BuildableBitmapTextureAtlas cutCharSparkleT = new BuildableBitmapTextureAtlas(
                    textureManager, 300, 100, mTransparentTextureOption);
            cutCharSparkleTR = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    cutCharSparkleT, context, "cut_ch_sparkle.png", 3, 1);
            try {
                cutCharSparkleT.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            } catch (TextureAtlasBuilderException e) { e.printStackTrace(); }
            cutCharSparkleT.load();
        }

        // Espada 1:
        if(cutSwordSparkle1TR==null) {
            BitmapTextureAtlas cutSword1T = new BitmapTextureAtlas(textureManager, 503, 345,
                    mTransparentTextureOption);
            cutSwordSparkle1TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    cutSword1T, activity, "cut_sword_sparkle1.png", 0, 0);
            cutSword1T.load();
        }

        // Music & Sounds:
        SoundFactory.setAssetBasePath("sounds/");
        MusicFactory.setAssetBasePath("music/");
        try {
            cutEyesZoom = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_eyes_zoom.ogg");
            cutKatana1 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_katana_cut1.ogg");
            cutKatana2 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_katana_cut2.ogg");
            cutKatana3 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_katana_cut3.ogg");
            cutKatanaWhoosh = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_katana_whoosh1.ogg");
            cutThud = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_katana_whoosh2.ogg");
            cutMusic = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "trial_cut_music.ogg");
        } catch (final IOException e) {
            Log.v("Sounds Load","Exception:" + e.getMessage());
        }
    }


    // Liberamos los recursos de la escena de corte:
    public synchronized void unloadCutSceneResources() {
        if(cutShoTR != null) {
            if(cutShoTR.getTexture().isLoadedToHardware()) {
                cutShoTR.getTexture().unload();
                cutShoTR = null;
            }
        }
        if(cutTreeTopTR!=null) {
            if(cutTreeTopTR.getTexture().isLoadedToHardware()) {
                cutTreeTopTR.getTexture().unload();
                cutTreeTopTR = null;
            }
        }
        if(cutTreeBottomTR!=null) {
            if(cutTreeBottomTR.getTexture().isLoadedToHardware()) {
                cutTreeBottomTR.getTexture().unload();
                cutTreeBottomTR = null;
            }
        }
        if(cutCandleTopTR!=null) {
            if(cutCandleTopTR.getTexture().isLoadedToHardware()) {
                cutCandleTopTR.getTexture().unload();
                cutCandleTopTR = null;
            }
        }
        if(cutCandleBottomTR!=null) {
            if(cutCandleBottomTR.getTexture().isLoadedToHardware()) {
                cutCandleBottomTR.getTexture().unload();
                cutCandleBottomTR = null;
            }
        }
        if(cutCandleLightTR!=null) {
            if(cutCandleLightTR.getTexture().isLoadedToHardware()) {
                cutCandleLightTR.getTexture().unload();
                cutCandleLightTR = null;
            }
        }
        if(cutEyesTR!=null) {
            if(cutEyesTR.getTexture().isLoadedToHardware()) {
                cutEyesTR.getTexture().unload();
                cutEyesTR = null;
            }
        }
        if(cutBackgroundTR!=null) {
            if(cutBackgroundTR.getTexture().isLoadedToHardware()) {
                cutBackgroundTR.getTexture().unload();
                cutBackgroundTR = null;
            }
        }
        if(cutSweatDropTR!=null) {
            if(cutSweatDropTR.getTexture().isLoadedToHardware()) {
                cutSweatDropTR.getTexture().unload();
                cutSweatDropTR = null;
            }
        }
        if(cutCharSparkleTR!=null) {
            if(cutCharSparkleTR.getTexture().isLoadedToHardware()) {
                cutCharSparkleTR.getTexture().unload();
                cutCharSparkleTR = null;
            }
        }
        if(cutSwordSparkle1TR!=null) {
            if(cutSwordSparkle1TR.getTexture().isLoadedToHardware()) {
                cutSwordSparkle1TR.getTexture().unload();
                cutSwordSparkle1TR = null;
            }
        }
        if(cutSwordSparkle2TR!=null) {
            if(cutSwordSparkle2TR.getTexture().isLoadedToHardware()) {
                cutSwordSparkle2TR.getTexture().unload();
                cutSwordSparkle2TR = null;
            }
        }

        // Music & Sounds:
        if(!cutEyesZoom.isReleased())
            cutEyesZoom.release();
        if(!cutKatana1.isReleased())
            cutKatana1.release();
        if(!cutKatana2.isReleased())
            cutKatana2.release();
        if(!cutKatana3.isReleased())
            cutKatana3.release();
        if(!cutKatanaWhoosh.isReleased())
            cutKatanaWhoosh.release();
        if(!cutEyesZoom.isReleased())
            cutThud.release();
        if(!cutMusic.isReleased())
            cutMusic.release();

        // Garbage Collector:
        System.gc();
    }

    public synchronized void loadRunSceneResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trial_run/");
        // Background
        if (runBgFloor == null) {
            BitmapTextureAtlas RunBg1 = new BitmapTextureAtlas(textureManager, 1024, 326,
                    mTransparentTextureOption);
            runBgFloor = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    RunBg1, activity, "run_background_floor.png", 0, 0);
            RunBg1.load();
        }
        if (runBgTreesBack == null) {
            BitmapTextureAtlas RunBg2 = new BitmapTextureAtlas(textureManager, 1021, 510,
                    mTransparentTextureOption);
            runBgTreesBack = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    RunBg2, activity, "run_background_trees_back.png", 0, 0);
            RunBg2.load();
        }
        if (runBgTreesFront == null) {
            BitmapTextureAtlas RunBg3 = new BitmapTextureAtlas(textureManager, 1024, 754,
                    mTransparentTextureOption);
            runBgTreesFront = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    RunBg3, activity, "run_background_trees_front.png", 0, 0);
            RunBg3.load();
        }

        // Dush
        if (runDushStart == null) {
            BitmapTextureAtlas runDush = new BitmapTextureAtlas(textureManager, 1296, 1080,
                    mTransparentTextureOption);
            runDushStart = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    runDush, activity, "run_dust_start.png", 0, 0);
            runDush.load();
        }
        if (runDushContinue == null) {
            BitmapTextureAtlas runDush = new BitmapTextureAtlas(textureManager, 600, 600,
                    mTransparentTextureOption);
            runDushContinue = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    runDush, activity, "run_dust_continuous.png", 0, 0);
            runDush.load();
        }

        // Sho
        if (runSho == null) {
            BuildableBitmapTextureAtlas runShoBit = new BuildableBitmapTextureAtlas(
                    textureManager, 2115, 2028, mTransparentTextureOption);
            runSho = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    runShoBit, context, "run_ch_sho.png", 5, 4);
            try {
                runShoBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            runShoBit.load();
        }

        // Ryoko
        if (runRyoko == null) {
            BuildableBitmapTextureAtlas runRyokoBit = new BuildableBitmapTextureAtlas(
                    textureManager, 2115, 2028, mTransparentTextureOption);
            runRyoko = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    runRyokoBit, context, "run_ch_ryoko.png", 5, 4);
            try {
                runRyokoBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            runRyokoBit.load();
        }
    }


    public synchronized void unloadRunSceneResources() {
        if (runSho != null && runSho.getTexture().isLoadedToHardware()) {
                runSho.getTexture().unload();
                runSho = null;
        }
        if (runRyoko != null && runRyoko.getTexture().isLoadedToHardware()) {
                runRyoko.getTexture().unload();
                runRyoko = null;
        }
        if (runBgFloor != null && runBgFloor.getTexture().isLoadedToHardware()) {
                runBgFloor.getTexture().unload();
                runBgFloor = null;
        }
        if (runBgTreesFront != null && runBgTreesFront.getTexture().isLoadedToHardware()) {
                runBgTreesFront.getTexture().unload();
                runBgTreesFront = null;
        }
        if (runBgTreesBack != null && runBgTreesBack.getTexture().isLoadedToHardware()) {
                runBgTreesBack.getTexture().unload();
                runBgTreesBack = null;
        }
        if (runDushStart != null && runDushStart.getTexture().isLoadedToHardware()) {
                runDushStart.getTexture().unload();
                runDushStart = null;
        }
        if (runDushContinue != null && runDushContinue.getTexture().isLoadedToHardware()) {
                runDushContinue.getTexture().unload();
                runDushContinue = null;
        }
        // Garbage Collector:
        System.gc();
    }

    public synchronized void loadShurikenSceneResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/trial_shuriken/");
        if (shurikenBackground == null) {
            BitmapTextureAtlas shurikenBackgroundT = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            shurikenBackground = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    shurikenBackgroundT, activity, "shuriken_background.png", 0, 0);
            shurikenBackgroundT.load();
        }

        if (shurikenRyokoHands == null) {
            BuildableBitmapTextureAtlas shurikenRyokoHandsBit = new BuildableBitmapTextureAtlas(
                    textureManager, 740, 960, mTransparentTextureOption);
            shurikenRyokoHands = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    shurikenRyokoHandsBit, context, "shuriken_ryoko_hands.png", 1, 3);
            try {
                shurikenRyokoHandsBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            shurikenRyokoHandsBit.load();
        }

        if (shurikenRyokoLose == null) {
            BuildableBitmapTextureAtlas shurikenRyokoLoseBit = new BuildableBitmapTextureAtlas(
                    textureManager, 1500, 978, mTransparentTextureOption);
            shurikenRyokoLose = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    shurikenRyokoLoseBit, context, "shuriken_ryoko_lose.png", 2, 1);
            try {
                shurikenRyokoLoseBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            shurikenRyokoLoseBit.load();
        }

        if (shurikenRyokoWin == null) {
            BuildableBitmapTextureAtlas shurikenRyokoWinBit = new BuildableBitmapTextureAtlas(
                    textureManager, 1400, 1036, mTransparentTextureOption);
            shurikenRyokoWin = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    shurikenRyokoWinBit, context, "shuriken_ryoko_win.png", 2, 1);
            try {
                shurikenRyokoWinBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            shurikenRyokoWinBit.load();
        }

        if (shurikenShoHands == null) {
            BuildableBitmapTextureAtlas shurikenShoHandsBit = new BuildableBitmapTextureAtlas(
                    textureManager, 740, 960, mTransparentTextureOption);
            shurikenShoHands = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    shurikenShoHandsBit, context, "shuriken_sho_hands.png", 1, 3);
            try {
                shurikenShoHandsBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            shurikenShoHandsBit.load();
        }

        if (shurikenShoLose == null) {
            BuildableBitmapTextureAtlas shurikenShoLoseBit = new BuildableBitmapTextureAtlas(
                    textureManager, 1500, 978, mTransparentTextureOption);
            shurikenShoLose = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    shurikenShoLoseBit, context, "shuriken_sho_lose.png", 2, 1);
            try {
                shurikenShoLoseBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            shurikenShoLoseBit.load();
        }

        if (shurikenShoWin == null) {
            BuildableBitmapTextureAtlas shurikenShoWinBit = new BuildableBitmapTextureAtlas(
                    textureManager, 1400, 1036, mTransparentTextureOption);
            shurikenShoWin = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    shurikenShoWinBit, context, "shuriken_sho_win.png", 2, 1);
            try {
                shurikenShoWinBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            shurikenShoWinBit.load();
        }

        // TODO
        //if (shurikenShuriken == null) {
        //    BuildableBitmapTextureAtlas shurikenShurikenBit = new BuildableBitmapTextureAtlas(
        //            textureManager, 196, 418, mTransparentTextureOption);
        //    shurikenShuriken = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
        //            shurikenShurikenBit, context, "shuriken_shuriken.png", ?,?);
        //    try {
        //        shurikenShurikenBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
        //                BitmapTextureAtlas>(0, 0, 0));
        //    }
        //    catch (TextureAtlasBuilderException e) {
        //        e.printStackTrace();
        //    }
        //    shurikenShurikenBit.load();
        //}

        if (shurikenStrawman1 == null) {
            BuildableBitmapTextureAtlas shurikenStrawman1Bit = new BuildableBitmapTextureAtlas(
                    textureManager, 1023, 640, mTransparentTextureOption);
            shurikenStrawman1 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    shurikenStrawman1Bit, context, "shuriken_strawman_1.png", 3, 1);
            try {
                shurikenStrawman1Bit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            shurikenStrawman1Bit.load();
        }

        if (shurikenStrawman2 == null) {
            BuildableBitmapTextureAtlas shurikenStrawman2Bit = new BuildableBitmapTextureAtlas(
                    textureManager, 1688, 1056, mTransparentTextureOption);
            shurikenStrawman2 = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    shurikenStrawman2Bit, context, "shuriken_strawman_2.png", 3, 1);
            try {
                shurikenStrawman2Bit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            shurikenStrawman2Bit.load();
        }
        if (shurikenStrawman3 == null) {
            BitmapTextureAtlas shurikenStrawman3T = new BitmapTextureAtlas(textureManager, 1068, 1635,
                    mTransparentTextureOption);
            shurikenStrawman3 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    shurikenStrawman3T, activity, "shuriken_strawman_3.png", 0, 0);
            shurikenStrawman3T.load();
        }
        if (shurikenTempShuriken == null) {
            BitmapTextureAtlas shurikenTempShurikenT = new BitmapTextureAtlas(textureManager, 128, 128,
                    mTransparentTextureOption);
            shurikenTempShuriken = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    shurikenTempShurikenT, activity, "shuriken_temp_shuriken.png", 0, 0);
            shurikenTempShurikenT.load();
        }
        if (shurikenTempStrawman == null) {
            BitmapTextureAtlas shurikenTempStrawmanT = new BitmapTextureAtlas(textureManager, 480, 820,
                    mTransparentTextureOption);
            shurikenTempStrawman = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    shurikenTempStrawmanT, activity, "shuriken_temp_strawman.png", 0, 0);
            shurikenTempStrawmanT.load();
        }
    }

    public synchronized void unloadShurikenSceneResources() {
        if (shurikenBackground != null && shurikenBackground.getTexture().isLoadedToHardware()) {
                shurikenBackground.getTexture().unload();
                shurikenBackground = null;
        }
        if (shurikenRyokoHands != null && shurikenRyokoHands.getTexture().isLoadedToHardware()) {
                shurikenRyokoHands.getTexture().unload();
                shurikenRyokoHands = null;
        }
        if (shurikenRyokoLose != null && shurikenRyokoLose.getTexture().isLoadedToHardware()) {
                shurikenRyokoLose.getTexture().unload();
                shurikenRyokoLose = null;
        }
        if (shurikenRyokoWin != null && shurikenRyokoWin.getTexture().isLoadedToHardware()) {
                shurikenRyokoWin.getTexture().unload();
                shurikenRyokoWin = null;
        }
        if (shurikenShoHands != null && shurikenShoHands.getTexture().isLoadedToHardware()) {
                shurikenShoHands.getTexture().unload();
                shurikenShoHands = null;
        }
        if (shurikenShoLose != null && shurikenShoLose.getTexture().isLoadedToHardware()) {
                shurikenShoLose.getTexture().unload();
                shurikenShoLose = null;
        }
        if (shurikenShoWin != null && shurikenShoWin.getTexture().isLoadedToHardware()) {
                shurikenShoWin.getTexture().unload();
                shurikenShoWin = null;
        }
        if (shurikenShuriken != null && shurikenShuriken.getTexture().isLoadedToHardware()) {
                shurikenShuriken.getTexture().unload();
                shurikenShuriken = null;
        }
        if (shurikenStrawman1 != null && shurikenStrawman1.getTexture().isLoadedToHardware()) {
                shurikenStrawman1.getTexture().unload();
                shurikenStrawman1 = null;
        }
        if (shurikenStrawman2 != null && shurikenStrawman2.getTexture().isLoadedToHardware()) {
                shurikenStrawman2.getTexture().unload();
                shurikenStrawman2 = null;
        }
        if (shurikenStrawman3 != null && shurikenStrawman3.getTexture().isLoadedToHardware()) {
                shurikenStrawman3.getTexture().unload();
                shurikenStrawman3 = null;
        }
        if (shurikenTempShuriken != null && shurikenTempShuriken.getTexture().isLoadedToHardware()) {
                shurikenTempShuriken.getTexture().unload();
                shurikenTempShuriken = null;
        }
        if (shurikenTempStrawman != null && shurikenTempStrawman.getTexture().isLoadedToHardware()) {
                shurikenTempStrawman.getTexture().unload();
                shurikenTempStrawman = null;
        }
    }

    public synchronized void loadIntro1Resources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/intro_1/");

        if (intro1Gradient == null) {
            BitmapTextureAtlas intro1GradientT = new BitmapTextureAtlas(textureManager, 1900, 1651,
                    mTransparentTextureOption);
            intro1Gradient = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro1GradientT, activity, "intro1_gradient.jpg", 0, 0);
            intro1GradientT.load();
        }
        if (intro1Logo == null) {
            BitmapTextureAtlas intro1LogoT = new BitmapTextureAtlas(textureManager, 756, 495,
                    mTransparentTextureOption);
            intro1Logo = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro1LogoT, activity, "intro1_logo.png", 0, 0);
            intro1LogoT.load();
        }
        if (intro1Ryoko == null) {
            BitmapTextureAtlas intro1RyokoT = new BitmapTextureAtlas(textureManager, 706, 1563,
                    mTransparentTextureOption);
            intro1Ryoko = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro1RyokoT, activity, "intro1_ryoko.png", 0, 0);
            intro1RyokoT.load();
        }
        if (intro1Shapes == null) {
            BitmapTextureAtlas intro1ShapesT = new BitmapTextureAtlas(textureManager, 1900, 1651,
                    mTransparentTextureOption);
            intro1Shapes = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro1ShapesT, activity, "intro1_shapes.png", 0, 0);
            intro1ShapesT.load();
        }
        if (intro1Sho == null) {
            BitmapTextureAtlas intro1ShoT = new BitmapTextureAtlas(textureManager, 981, 1734,
                    mTransparentTextureOption);
            intro1Sho = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro1ShoT, activity, "intro1_sho.png", 0, 0);
            intro1ShoT.load();
        }
        if (intro1TrialCut == null) {
            BitmapTextureAtlas intro1TrialCutT = new BitmapTextureAtlas(textureManager, 1260, 641,
                    mTransparentTextureOption);
            intro1TrialCut = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro1TrialCutT, activity, "intro1_trial_cut.jpg", 0, 0);
            intro1TrialCutT.load();
        }
        if (intro1TrialJump == null) {
            BitmapTextureAtlas intro1TrialJumpT = new BitmapTextureAtlas(textureManager, 1240, 637,
                    mTransparentTextureOption);
            intro1TrialJump = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro1TrialJumpT, activity, "intro1_trial_jump.jpg", 0, 0);
            intro1TrialJumpT.load();
        }
        if (intro1TrialRun == null) {
            BitmapTextureAtlas intro1TrialRunT = new BitmapTextureAtlas(textureManager, 1258, 643,
                    mTransparentTextureOption);
            intro1TrialRun = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro1TrialRunT, activity, "intro1_trial_run.jpg", 0, 0);
            intro1TrialRunT.load();
        }
        if (intro1TrialThrow == null) {
            BitmapTextureAtlas intro1TrialThrowT = new BitmapTextureAtlas(textureManager, 1242, 643,
                    mTransparentTextureOption);
            intro1TrialThrow = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro1TrialThrowT, activity, "intro1_trial_throw.jpg", 0, 0);
            intro1TrialThrowT.load();
        }
    }


    public synchronized void unloadIntro1Resources() {
        if (intro1Gradient != null && intro1Gradient.getTexture().isLoadedToHardware()) {
                intro1Gradient.getTexture().unload();
                intro1Gradient = null;
        }
        if (intro1Logo != null && intro1Logo.getTexture().isLoadedToHardware()) {
                intro1Logo.getTexture().unload();
                intro1Logo = null;
        }
        if (intro1Ryoko != null && intro1Ryoko.getTexture().isLoadedToHardware()) {
                intro1Ryoko.getTexture().unload();
                intro1Ryoko = null;
        }
        if (intro1Shapes != null && intro1Shapes.getTexture().isLoadedToHardware()) {
                intro1Shapes.getTexture().unload();
                intro1Shapes = null;
        }
        if (intro1Sho != null && intro1Sho.getTexture().isLoadedToHardware()) {
                intro1Sho.getTexture().unload();
                intro1Sho = null;
        }
        if (intro1TrialCut != null && intro1TrialCut.getTexture().isLoadedToHardware()) {
                intro1TrialCut.getTexture().unload();
                intro1TrialCut = null;
        }
        if (intro1TrialJump != null && intro1TrialJump.getTexture().isLoadedToHardware()) {
                intro1TrialJump.getTexture().unload();
                intro1TrialJump = null;
        }
        if (intro1TrialRun != null && intro1TrialRun.getTexture().isLoadedToHardware()) {
                intro1TrialRun.getTexture().unload();
                intro1TrialRun = null;
        }
        if (intro1TrialThrow != null && intro1TrialThrow.getTexture().isLoadedToHardware()) {
                intro1TrialThrow.getTexture().unload();
                intro1TrialThrow = null;
        }
        // Garbage Collector:
        System.gc();
    }

    public synchronized void loadIntro2Resources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/intro_2/");

        if (intro2CommonBg == null) {
            BitmapTextureAtlas intro2CommonBgT = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            intro2CommonBg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro2CommonBgT, activity, "intro2_common_bg.png", 0, 0);
            intro2CommonBgT.load();
        }

        if (intro2CommonMaster == null) {
            BuildableBitmapTextureAtlas intro2CommonMasterBit = new BuildableBitmapTextureAtlas(
                    textureManager, 698, 374, mTransparentTextureOption);
            intro2CommonMaster = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    intro2CommonMasterBit, context, "intro2_common_master.png", 2, 1);
            try {
                intro2CommonMasterBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            intro2CommonMasterBit.load();
        }
        if (intro2CommonMasterTextBalloon == null) {
            BitmapTextureAtlas intro2CommonMasterTextBalloonT = new BitmapTextureAtlas(textureManager, 502, 236,
                    mTransparentTextureOption);
            intro2CommonMasterTextBalloon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro2CommonMasterTextBalloonT, activity, "intro2_common_master_text_balloon.png", 0, 0);
            intro2CommonMasterTextBalloonT.load();
        }

        if (intro2CommonRyoko == null) {
            BuildableBitmapTextureAtlas intro2CommonRyokoBit = new BuildableBitmapTextureAtlas(
                    textureManager, 586, 764, mTransparentTextureOption);
            intro2CommonRyoko = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    intro2CommonRyokoBit, context, "intro2_common_ryoko.png", 1, 2);
            try {
                intro2CommonRyokoBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            intro2CommonRyokoBit.load();
        }
        if (intro2CommonRyokoTextBalloon == null) {
            BitmapTextureAtlas intro2CommonRyokoTextBalloonT = new BitmapTextureAtlas(textureManager, 598, 436,
                    mTransparentTextureOption);
            intro2CommonRyokoTextBalloon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro2CommonRyokoTextBalloonT, activity, "intro2_common_ryoko_text_balloon.png", 0, 0);
            intro2CommonRyokoTextBalloonT.load();
        }

        if (intro2CommonSho == null) {
            BuildableBitmapTextureAtlas intro2CommonShoBit = new BuildableBitmapTextureAtlas(
                    textureManager, 586, 764, mTransparentTextureOption);
            intro2CommonSho = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    intro2CommonShoBit, context, "intro2_common_sho.png", 1, 2);
            try {
                intro2CommonShoBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            intro2CommonShoBit.load();
        }
        if (intro2CommonShoTextBalloon == null) {
            BitmapTextureAtlas intro2CommonShoTextBalloonT = new BitmapTextureAtlas(textureManager, 598, 436,
                    mTransparentTextureOption);
            intro2CommonShoTextBalloon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro2CommonShoTextBalloonT, activity, "intro2_common_sho_text_balloon.png", 0, 0);
            intro2CommonShoTextBalloonT.load();
        }
        if (intro2RyokoBalloonText == null) {
            BitmapTextureAtlas intro2RyokoBalloonTextT = new BitmapTextureAtlas(textureManager, 987, 505,
                    mTransparentTextureOption);
            intro2RyokoBalloonText = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro2RyokoBalloonTextT, activity, "intro2_ryoko_balloon_text.png", 0, 0);
            intro2RyokoBalloonTextT.load();
        }
        if (intro2RyokoBg == null) {
            BitmapTextureAtlas intro2RyokoBgT = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            intro2RyokoBg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro2RyokoBgT, activity, "intro2_ryoko_bg.png", 0, 0);
            intro2RyokoBgT.load();
        }
        if (intro2Ryoko == null) {
            BitmapTextureAtlas intro2RyokoT = new BitmapTextureAtlas(textureManager, 633, 989,
                    mTransparentTextureOption);
            intro2Ryoko = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro2RyokoT, activity, "intro2_ryoko.png", 0, 0);
            intro2RyokoT.load();
        }
        if (intro2ShoBalloonText == null) {
            BitmapTextureAtlas intro2ShoBalloonTextT = new BitmapTextureAtlas(textureManager, 987, 505,
                    mTransparentTextureOption);
            intro2ShoBalloonText = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro2ShoBalloonTextT, activity, "intro2_sho_balloon_text.png", 0, 0);
            intro2ShoBalloonTextT.load();
        }
        if (intro2ShoBg == null) {
            BitmapTextureAtlas intro2ShoBgT = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            intro2ShoBg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro2ShoBgT, activity, "intro2_sho_bg.png", 0, 0);
            intro2ShoBgT.load();
        }
        if (intro2Sho == null) {
            BitmapTextureAtlas intro2ShoT = new BitmapTextureAtlas(textureManager, 813, 1049,
                    mTransparentTextureOption);
            intro2Sho = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    intro2ShoT, activity, "intro2_sho.png", 0, 0);
            intro2ShoT.load();
        }
    }


    public synchronized void unloadIntro2Resources() {
        if (intro2CommonBg != null && intro2CommonBg.getTexture().isLoadedToHardware()) {
                intro2CommonBg.getTexture().unload();
                intro2CommonBg = null;
        }
        if (intro2CommonMaster != null && intro2CommonMaster.getTexture().isLoadedToHardware()) {
                intro2CommonMaster.getTexture().unload();
                intro2CommonMaster = null;
        }
        if (intro2CommonMasterTextBalloon != null && intro2CommonMasterTextBalloon.getTexture().isLoadedToHardware()) {
                intro2CommonMasterTextBalloon.getTexture().unload();
                intro2CommonMasterTextBalloon = null;
        }
        if (intro2CommonRyoko != null && intro2CommonRyoko.getTexture().isLoadedToHardware()) {
                intro2CommonRyoko.getTexture().unload();
                intro2CommonRyoko = null;
        }
        if (intro2CommonRyokoTextBalloon != null && intro2CommonRyokoTextBalloon.getTexture().isLoadedToHardware()) {
                intro2CommonRyokoTextBalloon.getTexture().unload();
                intro2CommonRyokoTextBalloon = null;
        }
        if (intro2CommonSho != null && intro2CommonSho.getTexture().isLoadedToHardware()) {
                intro2CommonSho.getTexture().unload();
                intro2CommonSho = null;
        }
        if (intro2CommonShoTextBalloon != null && intro2CommonShoTextBalloon.getTexture().isLoadedToHardware()) {
                intro2CommonShoTextBalloon.getTexture().unload();
                intro2CommonShoTextBalloon = null;
        }
        if (intro2RyokoBalloonText != null && intro2RyokoBalloonText.getTexture().isLoadedToHardware()) {
                intro2RyokoBalloonText.getTexture().unload();
                intro2RyokoBalloonText = null;
        }
        if (intro2RyokoBg != null && intro2RyokoBg.getTexture().isLoadedToHardware()) {
                intro2RyokoBg.getTexture().unload();
                intro2RyokoBg = null;
        }
        if (intro2Ryoko != null && intro2Ryoko.getTexture().isLoadedToHardware()) {
                intro2Ryoko.getTexture().unload();
                intro2Ryoko = null;
        }
        if (intro2ShoBalloonText != null && intro2ShoBalloonText.getTexture().isLoadedToHardware()) {
                intro2ShoBalloonText.getTexture().unload();
                intro2ShoBalloonText = null;
        }
        if (intro2ShoBg != null && intro2ShoBg.getTexture().isLoadedToHardware()) {
                intro2ShoBg.getTexture().unload();
                intro2ShoBg = null;
        }
        if (intro2Sho != null && intro2Sho.getTexture().isLoadedToHardware()) {
                intro2Sho.getTexture().unload();
                intro2Sho = null;
        }
        // Garbage Collector:
        System.gc();
    }

    public synchronized void loadEndingResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/endings/");
        if (endingCreditsBackground == null) {
            BitmapTextureAtlas endingCreditsBackgroundT = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            endingCreditsBackground = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    endingCreditsBackgroundT, activity, "ending_credits_background.png", 0, 0);
            endingCreditsBackgroundT.load();
        }
        if (endingCreditsCategories == null) {
            BitmapTextureAtlas endingCreditsCategoriesT = new BitmapTextureAtlas(textureManager, 1200, 1020,
                    mTransparentTextureOption);
            endingCreditsCategories = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    endingCreditsCategoriesT, activity, "ending_credits_categories.png", 0, 0);
            endingCreditsCategoriesT.load();
        }
        if (endingCreditsLogoAndengine == null) {
            BitmapTextureAtlas endingCreditsLogoAndengineT = new BitmapTextureAtlas(textureManager, 389, 389,
                    mTransparentTextureOption);
            endingCreditsLogoAndengine = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    endingCreditsLogoAndengineT, activity, "ending_credits_logo_andengine.png", 0, 0);
            endingCreditsLogoAndengineT.load();
        }
        if (endingCreditsLogoEstudioevergreen == null) {
            BitmapTextureAtlas endingCreditsLogoEstudioevergreenT = new BitmapTextureAtlas(textureManager, 389, 389,
                    mTransparentTextureOption);
            endingCreditsLogoEstudioevergreen = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    endingCreditsLogoEstudioevergreenT, activity, "ending_credits_logo_estudioevergreen.png", 0, 0);
            endingCreditsLogoEstudioevergreenT.load();
        }
        if (endingRyokoEasyBg == null) {
            BitmapTextureAtlas endingRyokoEasyBgT = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            endingRyokoEasyBg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    endingRyokoEasyBgT, activity, "ending_ryoko_easy_bg.png", 0, 0);
            endingRyokoEasyBgT.load();
        }
        if (endingRyokoEasy == null) {
            BitmapTextureAtlas endingRyokoEasyT = new BitmapTextureAtlas(textureManager, 633, 989,
                    mTransparentTextureOption);
            endingRyokoEasy = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    endingRyokoEasyT, activity, "ending_ryoko_easy.png", 0, 0);
            endingRyokoEasyT.load();
        }
        if (endingShoEasyBg == null) {
            BitmapTextureAtlas endingShoEasyBgT = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            endingShoEasyBg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    endingShoEasyBgT, activity, "ending_sho_easy_bg.png", 0, 0);
            endingShoEasyBgT.load();
        }
        if (endingShoEasy == null) {
            BitmapTextureAtlas endingShoEasyT = new BitmapTextureAtlas(textureManager, 813, 1049,
                    mTransparentTextureOption);
            endingShoEasy = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    endingShoEasyT, activity, "ending_sho_easy.png", 0, 0);
            endingShoEasyT.load();
        }
    }

    public synchronized void unloadEndingResources() {
        if (endingCreditsBackground != null && endingCreditsBackground.getTexture().isLoadedToHardware()) {
                endingCreditsBackground.getTexture().unload();
                endingCreditsBackground = null;
        }
        if (endingCreditsCategories != null && endingCreditsCategories.getTexture().isLoadedToHardware()) {
                endingCreditsCategories.getTexture().unload();
                endingCreditsCategories = null;
        }
        if (endingCreditsLogoAndengine != null && endingCreditsLogoAndengine.getTexture().isLoadedToHardware()) {
                endingCreditsLogoAndengine.getTexture().unload();
                endingCreditsLogoAndengine = null;
        }
        if (endingCreditsLogoEstudioevergreen != null && endingCreditsLogoEstudioevergreen.getTexture().isLoadedToHardware()) {
                endingCreditsLogoEstudioevergreen.getTexture().unload();
                endingCreditsLogoEstudioevergreen = null;
        }
        if (endingRyokoEasyBg != null && endingRyokoEasyBg.getTexture().isLoadedToHardware()) {
                endingRyokoEasyBg.getTexture().unload();
                endingRyokoEasyBg = null;
        }
        if (endingRyokoEasy != null && endingRyokoEasy.getTexture().isLoadedToHardware()) {
                endingRyokoEasy.getTexture().unload();
                endingRyokoEasy = null;
        }
        if (endingShoEasyBg != null && endingShoEasyBg.getTexture().isLoadedToHardware()) {
                endingShoEasyBg.getTexture().unload();
                endingShoEasyBg = null;
        }
        if (endingShoEasy != null && endingShoEasy.getTexture().isLoadedToHardware()) {
                endingShoEasy.getTexture().unload();
                endingShoEasy = null;
        }
        // Garbage Collector:
        System.gc();
    }

    public synchronized void loadHowToPlayResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        if (howToPlayArrow == null) {
            BitmapTextureAtlas howToPlayArrowT = new BitmapTextureAtlas(textureManager, 149, 203,
                    mTransparentTextureOption);
            howToPlayArrow = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    howToPlayArrowT, activity, "how_to_play_arrow.png", 0, 0);
            howToPlayArrowT.load();
        }

        if (howToPlayButton == null) {
            BitmapTextureAtlas howToPlayButtonT = new BitmapTextureAtlas(textureManager, 182, 254,
                    mTransparentTextureOption);
            howToPlayButton = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    howToPlayButtonT, activity, "how_to_play_button.png", 0, 0);
            howToPlayButtonT.load();
        }

        if (howToPlayDigitalPad == null) {
            BitmapTextureAtlas howToPlayDigitalPadT = new BitmapTextureAtlas(textureManager, 471, 334,
                    mTransparentTextureOption);
            howToPlayDigitalPad = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    howToPlayDigitalPadT, activity, "how_to_play_digital_pad.png", 0, 0);
            howToPlayDigitalPadT.load();
        }
    }

    public synchronized void unloadHowToPlayResources() {
        if (howToPlayArrow != null && howToPlayArrow.getTexture().isLoadedToHardware()) {
                howToPlayArrow.getTexture().unload();
                howToPlayArrow = null;
        }

        if (howToPlayButton != null && howToPlayButton.getTexture().isLoadedToHardware()) {
                howToPlayButton.getTexture().unload();
                howToPlayButton = null;
        }

        if (howToPlayDigitalPad != null && howToPlayDigitalPad.getTexture().isLoadedToHardware()) {
                howToPlayDigitalPad.getTexture().unload();
                howToPlayDigitalPad = null;
        }
    }

    public synchronized void loadCharacterProfileResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        if (characterProfileBackground1 == null) {
            BitmapTextureAtlas characterProfileBackground1T = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            characterProfileBackground1 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    characterProfileBackground1T, activity, "character_profile_background_1.png", 0, 0);
            characterProfileBackground1T.load();
        }

        if (characterProfileBackground2 == null) {
            BitmapTextureAtlas characterProfileBackground2T = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            characterProfileBackground2 = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    characterProfileBackground2T, activity, "character_profile_background_2.png", 0, 0);
            characterProfileBackground2T.load();
        }

        if (characterProfileRyoko == null) {
            BitmapTextureAtlas characterProfileRyokoT = new BitmapTextureAtlas(textureManager, 706, 1563,
                    mTransparentTextureOption);
            characterProfileRyoko = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    characterProfileRyokoT, activity, "character_profile_ryoko.png", 0, 0);
            characterProfileRyokoT.load();
        }

        if (characterProfileSho == null) {
            BitmapTextureAtlas characterProfileShoT = new BitmapTextureAtlas(textureManager, 981, 1734,
                    mTransparentTextureOption);
            characterProfileSho = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    characterProfileShoT, activity, "character_profile_sho.png", 0, 0);
            characterProfileShoT.load();
        }
    }

    public synchronized void unloadCharacterProfileResources() {
        if (characterProfileBackground1 != null && characterProfileBackground1.getTexture().isLoadedToHardware()) {
                characterProfileBackground1.getTexture().unload();
                characterProfileBackground1 = null;
        }

        if (characterProfileBackground2 != null && characterProfileBackground2.getTexture().isLoadedToHardware()) {
                characterProfileBackground2.getTexture().unload();
                characterProfileBackground2 = null;
        }

        if (characterProfileRyoko != null && characterProfileRyoko.getTexture().isLoadedToHardware()) {
                characterProfileRyoko.getTexture().unload();
                characterProfileRyoko = null;
        }

        if (characterProfileSho != null && characterProfileSho.getTexture().isLoadedToHardware()) {
                characterProfileSho.getTexture().unload();
                characterProfileSho = null;
        }
    }

    public synchronized void loadMenuAchievementsResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menus/");
        if (menuAchievementsContainerDescription == null) {
            BitmapTextureAtlas menuAchievementsContainerDescriptionT = new BitmapTextureAtlas(textureManager, 438, 285,
                    mTransparentTextureOption);
            menuAchievementsContainerDescription = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuAchievementsContainerDescriptionT, activity, "menu_achievements_container_description.png", 0, 0);
            menuAchievementsContainerDescriptionT.load();
        }

        if (menuAchievementsContainerIcons == null) {
            BitmapTextureAtlas menuAchievementsContainerIconsT = new BitmapTextureAtlas(textureManager, 1063, 820,
                    mTransparentTextureOption);
            menuAchievementsContainerIcons = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuAchievementsContainerIconsT, activity, "menu_achievements_container_icons.png", 0, 0);
            menuAchievementsContainerIconsT.load();
        }

        if (menuAchievementsIconsBig == null) {
            BitmapTextureAtlas menuAchievementsIconsBigT = new BitmapTextureAtlas(textureManager, 1140, 1080,
                    mTransparentTextureOption);
            menuAchievementsIconsBig = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuAchievementsIconsBigT, activity, "menu_achievements_icons_big.png", 0, 0);
            menuAchievementsIconsBigT.load();
        }

        if (menuAchievementsIconsSmall == null) {
            BitmapTextureAtlas menuAchievementsIconsSmallT = new BitmapTextureAtlas(textureManager, 2040, 952,
                    mTransparentTextureOption);
            menuAchievementsIconsSmall = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuAchievementsIconsSmallT, activity, "menu_achievements_icons_small.png", 0, 0);
            menuAchievementsIconsSmallT.load();
        }

        if (menuAchievementsIngameContainer == null) {
            BitmapTextureAtlas menuAchievementsIngameContainerT = new BitmapTextureAtlas(textureManager, 806, 192,
                    mTransparentTextureOption);
            menuAchievementsIngameContainer = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuAchievementsIngameContainerT, activity, "menu_achievements_ingame_container.png", 0, 0);
            menuAchievementsIngameContainerT.load();
        }

        if (menuAchievementsSuccessStamp == null) {
            BitmapTextureAtlas menuAchievementsSuccessStampT = new BitmapTextureAtlas(textureManager, 260, 260,
                    mTransparentTextureOption);
            menuAchievementsSuccessStamp = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuAchievementsSuccessStampT, activity, "menu_achievements_success_stamp.png", 0, 0);
            menuAchievementsSuccessStampT.load();
        }
    }

    public synchronized void unloadMenuAchievementsResources() {
        if (menuAchievementsContainerDescription != null && menuAchievementsContainerDescription.getTexture().isLoadedToHardware()) {
                menuAchievementsContainerDescription.getTexture().unload();
                menuAchievementsContainerDescription = null;
        }

        if (menuAchievementsContainerIcons != null && menuAchievementsContainerIcons.getTexture().isLoadedToHardware()) {
                menuAchievementsContainerIcons.getTexture().unload();
                menuAchievementsContainerIcons = null;
        }

        if (menuAchievementsIconsBig != null && menuAchievementsIconsBig.getTexture().isLoadedToHardware()) {
                menuAchievementsIconsBig.getTexture().unload();
                menuAchievementsIconsBig = null;
        }

        if (menuAchievementsIconsSmall != null && menuAchievementsIconsSmall.getTexture().isLoadedToHardware()) {
                menuAchievementsIconsSmall.getTexture().unload();
                menuAchievementsIconsSmall = null;
        }

        if (menuAchievementsIngameContainer != null && menuAchievementsIngameContainer.getTexture().isLoadedToHardware()) {
                menuAchievementsIngameContainer.getTexture().unload();
                menuAchievementsIngameContainer = null;
        }

        if (menuAchievementsSuccessStamp != null && menuAchievementsSuccessStamp.getTexture().isLoadedToHardware()) {
                menuAchievementsSuccessStamp.getTexture().unload();
                menuAchievementsSuccessStamp = null;
        }
    }

    public synchronized void loadMenuMapResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menus/");

        if (menuMapBackgroundMarks == null) {
            BuildableBitmapTextureAtlas menuMapBackgroundMarksBit = new BuildableBitmapTextureAtlas(
                    textureManager, 94, 152, mTransparentTextureOption);
            menuMapBackgroundMarks = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    menuMapBackgroundMarksBit, context, "menu_map_background_marks.png", 1, 4);
            try {
                menuMapBackgroundMarksBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            menuMapBackgroundMarksBit.load();
        }

        if (menuMapBackground == null) {
            BitmapTextureAtlas menuMapBackgroundT = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            menuMapBackground = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuMapBackgroundT, activity, "menu_map_background.png", 0, 0);
            menuMapBackgroundT.load();
        }


        if (menuMapChRyoko == null) {
            BuildableBitmapTextureAtlas menuMapChRyokoBit = new BuildableBitmapTextureAtlas(
                    textureManager, 192, 330, mTransparentTextureOption);
            menuMapChRyoko = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    menuMapChRyokoBit, context, "menu_map_ch_ryoko.png", 2, 2);
            try {
                menuMapChRyokoBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            menuMapChRyokoBit.load();
        }


        if (menuMapChSho == null) {
            BuildableBitmapTextureAtlas menuMapChShoBit = new BuildableBitmapTextureAtlas(
                    textureManager, 192, 330, mTransparentTextureOption);
            menuMapChSho = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    menuMapChShoBit, context, "menu_map_ch_sho.png", 2, 2);
            try {
                menuMapChShoBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            menuMapChShoBit.load();
        }


        if (menuMapDrawings == null) {
            BuildableBitmapTextureAtlas menuMapDrawingsBit = new BuildableBitmapTextureAtlas(
                    textureManager, 1106, 962, mTransparentTextureOption);
            menuMapDrawings = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    menuMapDrawingsBit, context, "menu_map_drawings.png", 2, 2);
            try {
                menuMapDrawingsBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            menuMapDrawingsBit.load();
        }

        if (menuMapScroll == null) {
            BuildableBitmapTextureAtlas menuMapScrollBit = new BuildableBitmapTextureAtlas(
                    textureManager, 1568, 1632, mTransparentTextureOption);
            menuMapScroll = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    menuMapScrollBit, context, "menu_map_scroll.png", 2, 2);
            try {
                menuMapScrollBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            menuMapScrollBit.load();
        }
    }

    public synchronized void unloadMenuMapResources() {
        if (menuMapBackgroundMarks != null && menuMapBackgroundMarks.getTexture().isLoadedToHardware()) {
                menuMapBackgroundMarks.getTexture().unload();
                menuMapBackgroundMarks = null;
        }

        if (menuMapBackground != null && menuMapBackground.getTexture().isLoadedToHardware()) {
                menuMapBackground.getTexture().unload();
                menuMapBackground = null;
        }

        if (menuMapChRyoko != null && menuMapChRyoko.getTexture().isLoadedToHardware()) {
                menuMapChRyoko.getTexture().unload();
                menuMapChRyoko = null;
        }

        if (menuMapChSho != null && menuMapChSho.getTexture().isLoadedToHardware()) {
                menuMapChSho.getTexture().unload();
                menuMapChSho = null;
        }

        if (menuMapDrawings != null && menuMapDrawings.getTexture().isLoadedToHardware()) {
                menuMapDrawings.getTexture().unload();
                menuMapDrawings = null;
        }

        if (menuMapScroll != null && menuMapScroll.getTexture().isLoadedToHardware()) {
                menuMapScroll.getTexture().unload();
                menuMapScroll = null;
        }
    }

    public synchronized void loadMenuPauseResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menus/");
        if (menuPauseBambooFrame == null) {
            BitmapTextureAtlas menuPauseBambooFrameT = new BitmapTextureAtlas(textureManager, 1192, 717,
                    mTransparentTextureOption);
            menuPauseBambooFrame = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuPauseBambooFrameT, activity, "menu_pause_bamboo_frame.png", 0, 0);
            menuPauseBambooFrameT.load();
        }
    }

    public synchronized void unloadMenuPauseResources() {
        if (menuPauseBambooFrame != null && menuPauseBambooFrame.getTexture().isLoadedToHardware()) {
                menuPauseBambooFrame.getTexture().unload();
                menuPauseBambooFrame = null;
        }
    }

    public synchronized void loadMenuSelectedResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menus/");

        if (menuSelectChRyoko == null) {
            BuildableBitmapTextureAtlas menuSelectChRyokoBit = new BuildableBitmapTextureAtlas(
                    textureManager, 870, 1028, mTransparentTextureOption);
            menuSelectChRyoko = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    menuSelectChRyokoBit, context, "menu_select_ch_ryoko.png", 2, 1);
            try {
                menuSelectChRyokoBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            menuSelectChRyokoBit.load();
        }

        if (menuSelectChSho == null) {
            BuildableBitmapTextureAtlas menuSelectChShoBit = new BuildableBitmapTextureAtlas(
                    textureManager, 1310, 1120, mTransparentTextureOption);
            menuSelectChSho = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    menuSelectChShoBit, context, "menu_select_ch_sho.png", 2, 1);
            try {
                menuSelectChShoBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            menuSelectChShoBit.load();
        }
        if (menuSelectClouds == null) {
            BitmapTextureAtlas menuSelectCloudsT = new BitmapTextureAtlas(textureManager, 1422, 537,
                    mTransparentTextureOption);
            menuSelectClouds = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuSelectCloudsT, activity, "menu_select_clouds.png", 0, 0);
            menuSelectCloudsT.load();
        }
        if (menuSelectDifficulty == null) {
            BitmapTextureAtlas menuSelectDifficultyT = new BitmapTextureAtlas(textureManager, 1649, 633,
                    mTransparentTextureOption);
            menuSelectDifficulty = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuSelectDifficultyT, activity, "menu_select_difficulty.png", 0, 0);
            menuSelectDifficultyT.load();
        }
        if (menuSelectMoon == null) {
            BitmapTextureAtlas menuSelectMoonT = new BitmapTextureAtlas(textureManager, 940, 905,
                    mTransparentTextureOption);
            menuSelectMoon = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuSelectMoonT, activity, "menu_select_moon.png", 0, 0);
            menuSelectMoonT.load();
        }
        if (menuSelectRoof == null) {
            BitmapTextureAtlas menuSelectRoofT = new BitmapTextureAtlas(textureManager, 1585, 385,
                    mTransparentTextureOption);
            menuSelectRoof = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuSelectRoofT, activity, "menu_select_roof.png", 0, 0);
            menuSelectRoofT.load();
        }
        if (menuSelectSky == null) {
            BitmapTextureAtlas menuSelectSkyT = new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            menuSelectSky = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    menuSelectSkyT, activity, "menu_select_sky.png", 0, 0);
            menuSelectSkyT.load();
        }
    }

    public synchronized void unloadMenuSelectedResources() {
        if (menuSelectChRyoko != null && menuSelectChRyoko.getTexture().isLoadedToHardware()) {
                menuSelectChRyoko.getTexture().unload();
                menuSelectChRyoko = null;
        }

        if (menuSelectChSho != null && menuSelectChSho.getTexture().isLoadedToHardware()) {
                menuSelectChSho.getTexture().unload();
                menuSelectChSho = null;
        }

        if (menuSelectClouds != null && menuSelectClouds.getTexture().isLoadedToHardware()) {
                menuSelectClouds.getTexture().unload();
                menuSelectClouds = null;
        }

        if (menuSelectDifficulty != null && menuSelectDifficulty.getTexture().isLoadedToHardware()) {
                menuSelectDifficulty.getTexture().unload();
                menuSelectDifficulty = null;
        }

        if (menuSelectMoon != null && menuSelectMoon.getTexture().isLoadedToHardware()) {
                menuSelectMoon.getTexture().unload();
                menuSelectMoon = null;
        }

        if (menuSelectRoof != null && menuSelectRoof.getTexture().isLoadedToHardware()) {
                menuSelectRoof.getTexture().unload();
                menuSelectRoof = null;
        }

        if (menuSelectSky != null && menuSelectSky.getTexture().isLoadedToHardware()) {
                menuSelectSky.getTexture().unload();
                menuSelectSky = null;
        }
    }

    public synchronized void loadResultLoseSceneResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/results/");

        // Bg:
        if(loseBgTR==null) {
            BitmapTextureAtlas loseBgT =  new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            loseBgTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    loseBgT, activity, "results_lose_background.png", 0, 0);
            loseBgT.load();
        }

        // Sho:
        if(loseCharShoTR==null) {
            BitmapTextureAtlas loseCharShoT =  new BitmapTextureAtlas(textureManager, 797, 440,
                    mTransparentTextureOption);
            loseCharShoTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    loseCharShoT, activity, "results_lose_ch_sho.png", 0, 0);
            loseCharShoT.load();
        }

        // Ryoko:
        if(loseCharRyokoTR==null) {
            BitmapTextureAtlas loseCharRyokoT =  new BitmapTextureAtlas(textureManager, 797, 440,
                    mTransparentTextureOption);
            loseCharRyokoTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    loseCharRyokoT, activity, "results_lose_ch_ryoko.png", 0, 0);
            loseCharRyokoT.load();
        }

        // Music & Sounds:
        SoundFactory.setAssetBasePath("sounds/");
        MusicFactory.setAssetBasePath("music/");
        try {
            loseYouLose = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_you_lose.ogg");
            loseMusic = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "result_lose.ogg");
        } catch (final IOException e) {
            Log.v("Sounds Load","Exception:" + e.getMessage());
        }
    }

    public synchronized void unloadResultLoseSceneResources() {
        if(loseBgTR!=null) {
            if(loseBgTR.getTexture().isLoadedToHardware()) {
                loseBgTR.getTexture().unload();
                loseBgTR = null;
            }
        }
        if(loseCharShoTR!=null) {
            if(loseCharShoTR.getTexture().isLoadedToHardware()) {
                loseCharShoTR.getTexture().unload();
                loseCharShoTR = null;
            }
        }
        if(loseCharRyokoTR!=null) {
            if(loseCharRyokoTR.getTexture().isLoadedToHardware()) {
                loseCharRyokoTR.getTexture().unload();
                loseCharRyokoTR = null;
            }
        }

        // Music & Sounds:
        if(!loseYouLose.isReleased())
            loseYouLose.release();
        if(!loseMusic.isReleased())
            loseMusic.release();

        // Garbage Collector:
        System.gc();
    }

    
    public synchronized void loadResultWinResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/results/");

        // Bg:
        if(winBg==null) {
            BitmapTextureAtlas winBgT =  new BitmapTextureAtlas(textureManager, 1920, 1080,
                    mTransparentTextureOption);
            winBg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    winBgT, activity, "results_win_background.png", 0, 0);
            winBgT.load();
        }

        // Scroll:
        if(winScroll==null) {
            BitmapTextureAtlas winScrollT =  new BitmapTextureAtlas(textureManager, 1064, 1029,
                    mTransparentTextureOption);
            winScroll = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    winScrollT, activity, "results_win_scroll.png", 0, 0);
            winScrollT.load();
        }

        // Sho:
        if(winCharSho==null) {
            BitmapTextureAtlas winCharShoT =  new BitmapTextureAtlas(textureManager, 437, 799,
                    mTransparentTextureOption);
            winCharSho = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    winCharShoT, activity, "results_win_ch_sho.png", 0, 0);
            winCharShoT.load();
        }

        // Ryoko:
        if(winCharRyoko==null) {
            BitmapTextureAtlas winCharRyokoT =  new BitmapTextureAtlas(textureManager, 395, 767,
                    mTransparentTextureOption);
            winCharRyoko = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                    winCharRyokoT, activity, "results_win_ch_ryoko.png", 0, 0);
            winCharRyokoT.load();
        }

        // Drawings:
        if (winDrawings == null) {
            BuildableBitmapTextureAtlas winDrawingsBit = new BuildableBitmapTextureAtlas(
                    textureManager, 1106, 962, mTransparentTextureOption);
            winDrawings = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    winDrawingsBit, context, "results_win_drawings.png", 2, 2);
            try {
                winDrawingsBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            winDrawingsBit.load();
        }

        // Stamps:
        if (winStampRanking == null) {
            BuildableBitmapTextureAtlas winStampRankingBit = new BuildableBitmapTextureAtlas(
                    textureManager, 780, 400, mTransparentTextureOption);
            winStampRanking = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                    winStampRankingBit, context, "results_win_stamp_ranking.png", 2, 2);
            try {
                winStampRankingBit.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource,
                        BitmapTextureAtlas>(0, 0, 0));
            }
            catch (TextureAtlasBuilderException e) {
                e.printStackTrace();
            }
            winStampRankingBit.load();
        }

        // Music & Sounds:
        SoundFactory.setAssetBasePath("sounds/");
        MusicFactory.setAssetBasePath("music/");
        try {
            winYouWin = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_you_win.ogg");
            winPointsSum = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "menu_points_sum.ogg");
            winPointsTotal = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "menu_points_total.ogg");
            winMusic = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "result_win.ogg");
        } catch (final IOException e) {
            Log.v("Sounds Load","Exception:" + e.getMessage());
        }
    }


    public synchronized void unloadResultWinResources() {
        if(winBg!=null) {
            if(winBg.getTexture().isLoadedToHardware()) {
                winBg.getTexture().unload();
                winBg = null;
            }
        }
        if(winScroll!=null) {
            if(winScroll.getTexture().isLoadedToHardware()) {
                winScroll.getTexture().unload();
                winScroll = null;
            }
        }
        if(winCharSho!=null) {
            if(winCharSho.getTexture().isLoadedToHardware()) {
                winCharSho.getTexture().unload();
                winCharSho = null;
            }
        }
        if(winCharRyoko!=null) {
            if(winCharRyoko.getTexture().isLoadedToHardware()) {
                winCharRyoko.getTexture().unload();
                winCharRyoko = null;
            }
        }
        if(winDrawings!=null) {
            if(winDrawings.getTexture().isLoadedToHardware()) {
                winDrawings.getTexture().unload();
                winDrawings = null;
            }
        }
        if(winStampRanking!=null) {
            if(winStampRanking.getTexture().isLoadedToHardware()) {
                winStampRanking.getTexture().unload();
                winStampRanking = null;
            }
        }

        // Music & Sounds:
        if(winYouWin != null && !winYouWin.isReleased())
            winYouWin.release();
        if(winPointsSum != null && !winPointsSum.isReleased())
            winPointsSum.release();
        if(winPointsTotal != null && !winPointsTotal.isReleased())
            winPointsTotal.release();
        if(winMusic != null && !winMusic.isReleased())
            winMusic.release();

        // Garbage Collector:
        System.gc();
    }
    
    
    public synchronized void loadGameOverResources() {
        // Music & Sounds:
        SoundFactory.setAssetBasePath("sounds/");
        MusicFactory.setAssetBasePath("music/");
        try {
            gameOver = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_game_over.ogg");
            gameOverMusic = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "game_over.ogg");
        } catch (final IOException e) {
            Log.v("Sounds Load","Exception:" + e.getMessage());
        }
    }


    public synchronized void unloadGameOverResources() {
        // Music & Sounds:
        if(!gameOver.isReleased())
            gameOver.release();
        if(!gameOverMusic.isReleased())
            gameOverMusic.release();
    }

    public synchronized void loadMusicsResources() {
        MusicFactory.setAssetBasePath("music/");
        try {
            credits = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "credits.ogg");
            ending = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "ending.ogg");
            intro1 = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "intro1.ogg");
            intro2 = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "intro2.ogg");
            map = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "map.ogg");
            records = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "records.ogg");
            trialJump = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "trial_jump.ogg");
            trialRun = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "trial_run.ogg");
            trialShurikens = MusicFactory.createMusicFromAsset(
                    activity.getMusicManager(), context, "trial_shurikens.ogg");
        }
        catch (final IOException e) {
            Log.v("Sounds Load","Exception:" + e.getMessage());
        }
    }

    public synchronized void unloadMusicsResources() {
        if (!credits.isReleased())
            credits.release();
        if (!ending.isReleased())
            ending.release();
        if (!intro1.isReleased())
            intro1.release();
        if (!intro2.isReleased())
            intro2.release();
        if (!map.isReleased())
            map.release();
        if (!records.isReleased())
            records.release();
        if (!trialJump.isReleased())
            trialJump.release();
        if (!trialRun.isReleased())
            trialRun.release();
        if (!trialShurikens.isReleased())
            trialShurikens.release();
    }

    public synchronized void loadSoundsResources() {
        SoundFactory.setAssetBasePath("sounds/");
        try {
            effectEyeGleam = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "effect_eye_gleam.ogg");
            effectMasterHit = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "effect_master_hit.ogg");
            effectSweatDrop = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "effect_sweat_drop.ogg");
            judge1 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_1.ogg");
            judge2 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_2.ogg");
            judge3 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_3.ogg");
            judge4 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_4.ogg");
            judge5 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_5.ogg");
            judge6 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_6.ogg");
            judge7 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_7.ogg");
            judge8 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_8.ogg");
            judge9 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_9.ogg");
            judgeExcellent = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_excellent.ogg");
            judgeGood = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_good.ogg");
            judgeGo = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_go.ogg");
            judgeGreat = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_great.ogg");
            judgeReady = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "judge_ready.ogg");
            menuAchievement = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "menu_achievement.ogg");
            menuActivate = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "menu_activate.ogg");
            menuBack = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "menu_back.ogg");
            menuFocus = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "menu_focus.ogg");
            menuIntro1 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "menu_intro1.ogg");
            menuLogoMadgear = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "menu_logo_madgear.ogg");
            menuRank = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "menu_rank.ogg");
            ryokoCutCut = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_cut_cut.ogg");
            ryokoCutLose = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_cut_lose.ogg");
            ryokoCutWin = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_cut_win.ogg");
            ryokoJumpCharge = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_jump_charge.ogg");
            ryokoJumpFall = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_jump_fall.ogg");
            ryokoJumpHop = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_jump_hop.ogg");
            ryokoJumpLose = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_jump_lose.ogg");
            ryokoJumpWin = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_jump_win.ogg");
            ryokoMenuContinue = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_menu_continue.ogg");
            ryokoMenuGameOver = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_menu_game_over.ogg");
            ryokoRunCharge = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_run_charge.ogg");
            ryokoRunLose = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_run_lose.ogg");
            ryokoRunStart = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_run_start.ogg");
            ryokoRunWin = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_run_win.ogg");
            ryokoShurikenLose = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_shuriken_lose.ogg");
            ryokoShurikenThrow = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_shuriken_throw.ogg");
            ryokoShurikenWin = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "ryoko_shuriken_win.ogg");
            shoCutCut = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_cut_cut.ogg");
            shoCutLose = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_cut_lose.ogg");
            shoCutWin = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_cut_win.ogg");
            shoJumpCharge = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_jump_charge.ogg");
            shoJumpFall = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_jump_fall.ogg");
            shoJumpHop = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_jump_hop.ogg");
            shoJumpLose = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_jump_lose.ogg");
            shoJumpWin = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_jump_win.ogg");
            shoMenuContinue = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_menu_continue.ogg");
            shoMenuGameOver = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_menu_game_over.ogg");
            shoRunCharge = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_run_charge.ogg");
            shoRunLose = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_run_lose.ogg");
            shoRunStart = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_run_start.ogg");
            shoRunWin = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_run_win.ogg");
            shoShurikenLose = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_shuriken_lose.ogg");
            shoShurikenThrow = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_shuriken_throw.ogg");
            shoShurikenWin = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "sho_shuriken_win.ogg");
            trialCutCandleBlowOut = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_candle_blow_out.ogg");
            trialCutCandleShowingCut = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_candle_showing_cut.ogg");
            trialCutCandleThud = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_candle_thud.ogg");
            trialCutCandleWobble = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_candle_wobble.ogg");
            trialCutCandleWoobleThud = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_candle_wooble_thud.ogg");
            trialCutEyesZoomV2 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_eyes_zoom_v2.ogg");
            trialCutKatanaWhoosh3 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_cut_katana_whoosh3.ogg");
            trialJumpFall = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_jump_fall.ogg");
            trialJumpReach = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_jump_reach.ogg");
            trialJumpSlip = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_jump_slip.ogg");
            trialJumpTap1 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_jump_tap1.ogg");
            trialJumpTap2 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_jump_tap2.ogg");
            trialJumpThud = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_jump_thud.ogg");
            trialJumpWhoosh1 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_jump_whoosh1.ogg");
            trialJumpWhoosh2 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_jump_whoosh2.ogg");
            trialJumpWhoosh3 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_jump_whoosh3.ogg");
            trialJumpWobble = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_jump_wobble.ogg");
            trialRunTap1 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_run_tap1.ogg");
            trialRunTap2 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_run_tap2.ogg");
            trialRunTap3 = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_run_tap3.ogg");
            trialRunWind1Start = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_run_wind_1_start.ogg");
            trialRunWind2Running = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_run_wind_2_running.ogg");
            trialRunWind3End = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_run_wind_3_end.ogg");
            trialShurikenStrawmanAscend = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_shuriken_strawman_ascend.ogg");
            trialShurikenStrawmanDescend = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_shuriken_strawman_descend.ogg");
            trialShurikenStrawmanDestroyed = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_shuriken_strawman_destroyed.ogg");
            trialShurikenStrawmanHit = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_shuriken_strawman_hit.ogg");
            trialShurikenStrawmanMove = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_shuriken_strawman_move.ogg");
            trialShurikenThrowing = SoundFactory.createSoundFromAsset(
                    activity.getSoundManager(), context, "trial_shuriken_throwing.ogg");
        }
        catch (final IOException e) {
            Log.v("Sounds Load","Exception:" + e.getMessage());
        }
    }

    public synchronized void unloadSoundsResources() {
        if (!effectEyeGleam.isReleased())
            effectEyeGleam.release();
        if (!effectMasterHit.isReleased())
            effectMasterHit.release();
        if (!effectSweatDrop.isReleased())
            effectSweatDrop.release();
        if (!judge1.isReleased())
            judge1.release();
        if (!judge2.isReleased())
            judge2.release();
        if (!judge3.isReleased())
            judge3.release();
        if (!judge4.isReleased())
            judge4.release();
        if (!judge5.isReleased())
            judge5.release();
        if (!judge6.isReleased())
            judge6.release();
        if (!judge7.isReleased())
            judge7.release();
        if (!judge8.isReleased())
            judge8.release();
        if (!judge9.isReleased())
            judge9.release();
        if (!judgeExcellent.isReleased())
            judgeExcellent.release();
        if (!judgeGood.isReleased())
            judgeGood.release();
        if (!judgeGo.isReleased())
            judgeGo.release();
        if (!judgeGreat.isReleased())
            judgeGreat.release();
        if (!judgeReady.isReleased())
            judgeReady.release();
        if (!menuAchievement.isReleased())
            menuAchievement.release();
        if (!menuActivate.isReleased())
            menuActivate.release();
        if (!menuBack.isReleased())
            menuBack.release();
        if (!menuFocus.isReleased())
            menuFocus.release();
        if (!menuIntro1.isReleased())
            menuIntro1.release();
        if (!menuLogoMadgear.isReleased())
            menuLogoMadgear.release();
        if (!menuRank.isReleased())
            menuRank.release();
        if (!ryokoCutCut.isReleased())
            ryokoCutCut.release();
        if (!ryokoCutLose.isReleased())
            ryokoCutLose.release();
        if (!ryokoCutWin.isReleased())
            ryokoCutWin.release();
        if (!ryokoJumpCharge.isReleased())
            ryokoJumpCharge.release();
        if (!ryokoJumpFall.isReleased())
            ryokoJumpFall.release();
        if (!ryokoJumpHop.isReleased())
            ryokoJumpHop.release();
        if (!ryokoJumpLose.isReleased())
            ryokoJumpLose.release();
        if (!ryokoJumpWin.isReleased())
            ryokoJumpWin.release();
        if (!ryokoMenuContinue.isReleased())
            ryokoMenuContinue.release();
        if (!ryokoMenuGameOver.isReleased())
            ryokoMenuGameOver.release();
        if (!ryokoRunCharge.isReleased())
            ryokoRunCharge.release();
        if (!ryokoRunLose.isReleased())
            ryokoRunLose.release();
        if (!ryokoRunStart.isReleased())
            ryokoRunStart.release();
        if (!ryokoRunWin.isReleased())
            ryokoRunWin.release();
        if (!ryokoShurikenLose.isReleased())
            ryokoShurikenLose.release();
        if (!ryokoShurikenThrow.isReleased())
            ryokoShurikenThrow.release();
        if (!ryokoShurikenWin.isReleased())
            ryokoShurikenWin.release();
        if (!shoCutCut.isReleased())
            shoCutCut.release();
        if (!shoCutLose.isReleased())
            shoCutLose.release();
        if (!shoCutWin.isReleased())
            shoCutWin.release();
        if (!shoJumpCharge.isReleased())
            shoJumpCharge.release();
        if (!shoJumpFall.isReleased())
            shoJumpFall.release();
        if (!shoJumpHop.isReleased())
            shoJumpHop.release();
        if (!shoJumpLose.isReleased())
            shoJumpLose.release();
        if (!shoJumpWin.isReleased())
            shoJumpWin.release();
        if (!shoMenuContinue.isReleased())
            shoMenuContinue.release();
        if (!shoMenuGameOver.isReleased())
            shoMenuGameOver.release();
        if (!shoRunCharge.isReleased())
            shoRunCharge.release();
        if (!shoRunLose.isReleased())
            shoRunLose.release();
        if (!shoRunStart.isReleased())
            shoRunStart.release();
        if (!shoRunWin.isReleased())
            shoRunWin.release();
        if (!shoShurikenLose.isReleased())
            shoShurikenLose.release();
        if (!shoShurikenThrow.isReleased())
            shoShurikenThrow.release();
        if (!shoShurikenWin.isReleased())
            shoShurikenWin.release();
        if (!trialCutCandleBlowOut.isReleased())
            trialCutCandleBlowOut.release();
        if (!trialCutCandleShowingCut.isReleased())
            trialCutCandleShowingCut.release();
        if (!trialCutCandleThud.isReleased())
            trialCutCandleThud.release();
        if (!trialCutCandleWobble.isReleased())
            trialCutCandleWobble.release();
        if (!trialCutCandleWoobleThud.isReleased())
            trialCutCandleWoobleThud.release();
        if (!trialCutEyesZoomV2.isReleased())
            trialCutEyesZoomV2.release();
        if (!trialCutKatanaWhoosh3.isReleased())
            trialCutKatanaWhoosh3.release();
        if (!trialJumpFall.isReleased())
            trialJumpFall.release();
        if (!trialJumpReach.isReleased())
            trialJumpReach.release();
        if (!trialJumpSlip.isReleased())
            trialJumpSlip.release();
        if (!trialJumpTap1.isReleased())
            trialJumpTap1.release();
        if (!trialJumpTap2.isReleased())
            trialJumpTap2.release();
        if (!trialJumpThud.isReleased())
            trialJumpThud.release();
        if (!trialJumpWhoosh1.isReleased())
            trialJumpWhoosh1.release();
        if (!trialJumpWhoosh2.isReleased())
            trialJumpWhoosh2.release();
        if (!trialJumpWhoosh3.isReleased())
            trialJumpWhoosh3.release();
        if (!trialJumpWobble.isReleased())
            trialJumpWobble.release();
        if (!trialRunTap1.isReleased())
            trialRunTap1.release();
        if (!trialRunTap2.isReleased())
            trialRunTap2.release();
        if (!trialRunTap3.isReleased())
            trialRunTap3.release();
        if (!trialRunWind1Start.isReleased())
            trialRunWind1Start.release();
        if (!trialRunWind2Running.isReleased())
            trialRunWind2Running.release();
        if (!trialRunWind3End.isReleased())
            trialRunWind3End.release();
        if (!trialShurikenStrawmanAscend.isReleased())
            trialShurikenStrawmanAscend.release();
        if (!trialShurikenStrawmanDescend.isReleased())
            trialShurikenStrawmanDescend.release();
        if (!trialShurikenStrawmanDestroyed.isReleased())
            trialShurikenStrawmanDestroyed.release();
        if (!trialShurikenStrawmanHit.isReleased())
            trialShurikenStrawmanHit.release();
        if (!trialShurikenStrawmanMove.isReleased())
            trialShurikenStrawmanMove.release();
        if (!trialShurikenThrowing.isReleased())
            trialShurikenThrowing.release();
    }

    /* Loads fonts resources
     */
    public synchronized void loadFonts(Engine pEngine){
        FontFactory.setAssetBasePath("fonts/");

        // Small = 64
        fontSmall = FontFactory.createStrokeFromAsset(pEngine.getFontManager(),
                pEngine.getTextureManager(), 512, 512, activity.getAssets(), "go3v2.ttf",
                64f, true, android.graphics.Color.WHITE, 3, android.graphics.Color.RED);
        fontSmall.load();

        // Medium = 96
        fontMedium = FontFactory.createStrokeFromAsset(pEngine.getFontManager(),
                pEngine.getTextureManager(), 1024, 1024, activity.getAssets(), "go3v2.ttf",
                96f, true, android.graphics.Color.WHITE, 3, android.graphics.Color.RED);
        fontMedium.load();

        // Big = 128
        fontBig = FontFactory.createStrokeFromAsset(pEngine.getFontManager(),
                pEngine.getTextureManager(), 1024, 1024, activity.getAssets(), "go3v2.ttf",
                128f, true, android.graphics.Color.WHITE, 3, android.graphics.Color.RED);
        fontBig.load();

        // XBig = 192
        fontXBig = FontFactory.createStrokeFromAsset(pEngine.getFontManager(),
                pEngine.getTextureManager(), 1024, 1024, activity.getAssets(), "go3v2.ttf",
                192f, true, android.graphics.Color.WHITE, 3, android.graphics.Color.RED);
        fontXBig.load();
    }

    /* If an unloadFonts() method is necessary, we can provide one
     */
    public synchronized void unloadFonts(){
        fontSmall.unload();
        fontMedium.unload();
        fontBig.unload();
        fontXBig.unload();
    }
}
        //if (AAA == null) {
        //    BitmapTextureAtlas BBB = new BitmapTextureAtlas(textureManager, 1920, 1080,
        //            mTransparentTextureOption);
        //    AAA = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
        //            BBB, activity, "CCC", 0, 0);
        //    BBB.load();
        //}

        //if (AAA != null && AAA.getTexture().isLoadedToHardware()) {
        //        AAA.getTexture().unload();
        //        AAA = null;
        //}
