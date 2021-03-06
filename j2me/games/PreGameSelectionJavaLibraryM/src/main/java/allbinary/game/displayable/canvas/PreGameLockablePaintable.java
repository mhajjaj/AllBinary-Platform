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
package allbinary.game.displayable.canvas;

import javax.microedition.lcdui.Graphics;

import org.allbinary.image.GameFeatureImageCacheFactory;

import abcs.logic.system.security.licensing.LockedResources;
import abcs.logic.system.security.licensing.LockedUtil;
import allbinary.graphics.form.LockablePaintable;
import allbinary.graphics.form.ScrollCurrentSelectionForm;

public class PreGameLockablePaintable 
    extends LockablePaintable
{
    private final int halfWidth;

    private final ScrollCurrentSelectionForm paintableForm;
        
    public PreGameLockablePaintable(ScrollCurrentSelectionForm paintableForm, int lockedIndex) 
    throws Exception
    {
        super(lockedIndex);

        //ScrollCurrentSelectionForm paintableForm
        this.paintableForm = paintableForm;
                
        this.halfWidth = GameFeatureImageCacheFactory.getInstance().get(
                LockedResources.getInstance().LOCKED_DEMO_GAME_FEATURE_RESOURCE).getWidth()/2;
    }
    
    public void paint(Graphics graphics, int currentIndex, int x, int y)
    {
        if(LockedUtil.getInstance().isLockedFeature() && 
                this.getPaintableForm().getSelectedIndex() >= this.getLockedIndex())
        {
            //98 - 64
            this.getAnimation().paint(graphics, 
                    this.getPaintableForm().getDx() - this.halfWidth, 
                    this.getPaintableForm().getDy() + 34); //x, y);
        }
    }
    
    protected ScrollCurrentSelectionForm getPaintableForm()
    {
        return paintableForm;
    }

}
