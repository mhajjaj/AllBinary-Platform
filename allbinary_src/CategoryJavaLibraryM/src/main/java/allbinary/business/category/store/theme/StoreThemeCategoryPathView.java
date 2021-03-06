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
package allbinary.business.category.store.theme;

import allbinary.business.category.CategoryView;
import allbinary.data.tree.dom.DomNodeInterface;
import allbinary.logic.visual.theme.ThemeInterface;
import allbinary.logic.visual.theme.ThemePropertiesView;
import allbinary.logic.visual.theme.ThemeValidation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.Iterator;

public class StoreThemeCategoryPathView extends CategoryView implements DomNodeInterface
{
   public StoreThemeCategoryPathView(StoreThemeCategoryInterface categoryInterface)
   {
      super((StoreThemeCategoryInterface) categoryInterface);
   }

   public Node toXmlNode(Document document) throws Exception
   {
      Node node = super.toXmlNode(document);

      StoreThemeCategoryInterface storeThemeCategoryInterface = 
         (StoreThemeCategoryInterface) this.getCategoryInterface();
      
      Iterator iter = storeThemeCategoryInterface.getThemes().iterator();
      while(iter.hasNext())
      {
         ThemeValidation themeValidation = (ThemeValidation) iter.next();

         DomNodeInterface domNodeInterface = (DomNodeInterface)
            new ThemePropertiesView((ThemeInterface) themeValidation);

         node.appendChild(domNodeInterface.toXmlNode(document));
      }

      return node;
   }
}
