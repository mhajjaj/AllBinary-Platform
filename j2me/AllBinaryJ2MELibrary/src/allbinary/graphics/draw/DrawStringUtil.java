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
package allbinary.graphics.draw;

import javax.microedition.lcdui.Graphics;

import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.graphics.Anchor;
import allbinary.graphics.font.MyFont;

/**
 *
 * @author user
 */
public class DrawStringUtil
{
    private static final DrawStringUtil instance = new DrawStringUtil();

    public static DrawStringUtil getInstance()
    {
        return instance;
    }    

    public void paintVerticle(Graphics graphics,
            String string, int x, int y, int anchor)
    {
        final int charHeight = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
        
        int size = string.length();
        for (int index = size - 1; index >= 0; index--)
        {
            graphics.drawChar(string.charAt(index),
                    x, y + (charHeight * index), anchor);
        }
    }

    private int anchor = Anchor.TOP_LEFT;
    
    public void drawCenterString(
            Graphics graphics, String string, int offset, int length, int x, int y)
    {
        int width = (graphics.getFont().substringWidth(string, offset, length) >> 1);
        //int width = (graphics.getFont().stringWidth(string) >> 1);
        //width = (width * length) / string.length();

        try
        {
        graphics.drawSubstring(
                string, offset, length,
                x - width,
                y,
                anchor);
        }
        catch(Exception e)
        {
            PreLogUtil.put("Exception: stringLength: " + string.length() + " offset: " + offset + " currentLength: " + length, graphics, "drawCenterString");
        }
    }
    
    private final String EMPTY_STRING = StringUtil.getInstance().EMPTY_STRING;

    public void drawCenterStrings(
            Graphics graphics, String[] stringArray, int maxWidth, int x, int y)
    {
        final int charHeight = MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
        
        int extraLines = 0;

        for (int index = 0; index < stringArray.length; index++)
        {
            String string = stringArray[index];

            if (string != EMPTY_STRING)
            {
                int width = graphics.getFont().stringWidth(string);

                int minTotalLines = 1;
                if (width > maxWidth)
                {
                    minTotalLines = (width / maxWidth) + 1;
                }

                int linePortion = string.length() / minTotalLines;
                int offset = 0;

                int currentLength = linePortion;

                //for (int lineIndex = 0; lineIndex < minTotalLines; lineIndex++)
                
                int size = string.length();
                while(offset < size)
                {
                    //|| lineIndex == minTotalLines - 1
                    if(offset + currentLength > size)
                    {
                        currentLength = size - offset;
                    }

                    if(offset + currentLength != size)
                    {
                        while (currentLength > 0 && stringArray[index].charAt(offset + currentLength) != ' ')
                        {
                            currentLength--;
                        }

                        //If no spaces after tab size then stick with limit
                        if (currentLength <= 4)
                        {
                            currentLength = linePortion;
                        }
                    }

                    //PreLogUtil.put("stringLength: " + string.length() + " offset: " + offset + " currentLength: " + currentLength, graphics, "drawCenterStrings");

                    this.drawCenterString(graphics,
                            string, offset, currentLength, 
                            x, y + ((index + extraLines++) * charHeight));

                    offset = offset + currentLength;

                    currentLength = linePortion;

                    /*
                    while(offset < size && stringArray[index].charAt(offset) == ' ')
                    {
                        offset++;
                    }
                     */
                }
                extraLines--;
            }
        }
    }
}
