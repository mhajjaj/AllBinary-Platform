/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.game.init;

import javax.microedition.lcdui.CommandListener;

import org.allbinary.game.resource.ResourceLoadingLevelFactory;
import org.allbinary.image.GameFeatureImageCacheFactory;

import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import allbinary.game.configuration.GameConfigurationCentral;
import allbinary.game.configuration.event.ChangedGameFeatureListener;
import allbinary.game.configuration.feature.InputFeatureFactory;
import allbinary.game.configuration.feature.MainFeatureFactory;
import allbinary.game.input.PlatformInputMappingFactory;
import allbinary.game.input.event.GameKeyEventFactory;
import allbinary.game.resource.FeaturedResourceRelativeRelationshipFactory;
import allbinary.game.resource.ResourceInitialization;
import allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import allbinary.input.motion.CompleteMotionGestureInputToGameMotionGestureInput;
import allbinary.input.motion.button.BasicTouchInputFactory;

public class BaseGameInitialization implements GameInitializationInterface
{
    protected final ResourceInitialization[] resourceInitializationArray;

    private final int portion;

    private boolean initialized;
    
    protected final int EARLY_RESOURCES = 0;
    protected final int GAME_RESOURCES = 1;
    protected final int EARLY_CHANGABLE_RESOURCES = 2;
    protected final int GAME_CHANGABLE_RESOURCES = 3;
    
    public BaseGameInitialization(ResourceInitialization[] resourceInitializationArray, int portion)
    {
        this.resourceInitializationArray = resourceInitializationArray;
        this.portion = portion;
    }

    public void initKey(int portion) throws Exception
    {
    }

    protected void initKeyMapping(int portion) throws Exception
    {
        if (ChangedGameFeatureListener.getInstance().isChanged(
                InputFeatureFactory.getInstance().INPUT_MAPPING))
        {
            PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().init();
            ProgressCanvasFactory.getInstance().addPortion(50, "Game Keys");
            ChangedGameFeatureListener.getInstance().remove(
                    InputFeatureFactory.getInstance().INPUT_MAPPING);
        }
    }

    public void init(CommandListener commandListener, int level) 
        throws Exception
    {
        ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
            ResourceLoadingLevelFactory.getInstance();
        
        if (!this.isInitialized() && 
                (level == resourceLoadingLevelFactory.LOAD_ALL.getLevel() ||
                level == resourceLoadingLevelFactory.LOAD_EARLY.getLevel()))
        {
            //PreLogUtil.put("Early Game Init", this, CommonStrings.getInstance().INIT);
            
            int localPortion = 40;
            if(level == resourceLoadingLevelFactory.LOAD_EARLY.getLevel())
            {
                localPortion = 8;
            }
            
            this.setInitialized(true);

            this.initKey(getPortion());

            this.initKeyMapping(getPortion());

            GameKeyEventFactory.getInstance().init();

            ProgressCanvas progressCanvas = 
                ProgressCanvasFactory.getInstance();
            
            progressCanvas.addPortion(localPortion, "Game Key Events");

            // TWB - This is not a good initialization process but it must
            // happen
            // after canvas start
            BasicTouchInputFactory.getInstance().init(
                    PlatformInputMappingFactory.getInstance().getPersistentInputMappingInstance().getInputMapping());
            progressCanvas.addPortion(localPortion, "Touch Input");

            CompleteMotionGestureInputToGameMotionGestureInput.init();
            progressCanvas.addPortion(localPortion, "Motion Input");

            GameFeatureImageCacheFactory.init();
            progressCanvas.addPortion(localPortion, "Image Cache");

            //PreLogUtil.put("Early Resources", this, CommonStrings.getInstance().INIT);
            this.resourceInitializationArray[EARLY_RESOURCES].init();
        }
    }

//    
    private boolean gameInitialized;
    private boolean allLoaded;
    
    public void resourceInitialization(int level)
    throws Exception
    {
        ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
            ResourceLoadingLevelFactory.getInstance();
        
        if(this.resourceAnimationChange() && level == resourceLoadingLevelFactory.LOAD_EARLY.getLevel())
        {
            this.clearResources();
            
            this.resourceInitializationArray[this.EARLY_CHANGABLE_RESOURCES].init();
        }
        
        if(this.resourceAnimationChange() && level == resourceLoadingLevelFactory.LOAD_ALL.getLevel())
        {
            if(this.allLoaded)
            {
                this.clearResources();
            }
            
            //If LOAD_EARLY/(new DemoCanvas) never occured but static or size did change
            if(FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().getList().size() == 0)
            {
                this.resourceInitializationArray[this.EARLY_CHANGABLE_RESOURCES].init();
            }

            this.resourceInitializationArray[this.GAME_CHANGABLE_RESOURCES].init();
            this.allLoaded = true;
        }
    }
    
    public boolean resourceAnimationChange()
    {
        ChangedGameFeatureListener changedGameFeatureListener = 
            ChangedGameFeatureListener.getInstance();
        GameConfigurationCentral gameConfigurationCentral = 
            GameConfigurationCentral.getInstance();

        if(changedGameFeatureListener.isChanged(
                gameConfigurationCentral.SCALE))
        {
            return true;
        }

        if (ChangedGameFeatureListener.getInstance().isChanged(
                MainFeatureFactory.getInstance().STATIC))
        {
            return true;
        }
        
        return false;
    }
    
    private void clearResources()
    {
        this.allLoaded = false;
     
        //Release scaled/rotated and other non resource images for reuse
        GameFeatureImageCacheFactory.releaseAll();
        
        FeaturedAnimationInterfaceFactoryInterfaceFactory 
        featuredAnimationInterfaceFactoryInterfaceFactory = 
            FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance();

        featuredAnimationInterfaceFactoryInterfaceFactory.clear();
        FeaturedResourceRelativeRelationshipFactory.getInstance().clear();

    }
    
    protected void setGameInitialized(boolean gameInitialized)
    {
        this.gameInitialized = gameInitialized;
    }

    protected boolean isGameInitialized()
    {
        return gameInitialized;
    }
//
    
    protected int getPortion()
    {
        return portion;
    }

    public void setInitialized(boolean initialized)
    {
        this.initialized = initialized;
    }

    public boolean isInitialized()
    {
        return initialized;
    }
}
