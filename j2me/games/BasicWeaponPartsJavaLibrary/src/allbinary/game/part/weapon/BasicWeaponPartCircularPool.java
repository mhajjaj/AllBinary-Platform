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
package allbinary.game.part.weapon;

import allbinary.game.combat.weapon.WeaponProperties;
import allbinary.game.score.ScoreableInterface;
import allbinary.graphics.RelativeRelationship;
import allbinary.layer.AllBinaryLayer;

public class BasicWeaponPartCircularPool 
{
   //private CircularIndexUtil circularIndexUtil;
   //TODO TWB only one item in pool is stupid
   //private final int MAX = 1;
   //private BasicWeaponPart[] VECTOR_GRAPHIC_ARRAY = new BasicWeaponPart[MAX];
    
    private BasicWeaponPart basicWeaponPart;

   public void init(
           BasicWeaponPartFactoryInterface basicWeaponPartFactoryInterface)
           throws Exception {
       basicWeaponPart = basicWeaponPartFactoryInterface.getInstance();
       /*
      for (int index = 0; index < MAX; index++) {
         VECTOR_GRAPHIC_ARRAY[index] = basicWeaponPartFactoryInterface.getInstance();
      }
      this.circularIndexUtil = CircularIndexUtil.getInstance(MAX - 1);
      */
   }

   public synchronized BasicWeaponPart getInstance(
           AllBinaryLayer sourceLayerInterface,
           WeaponProperties weaponProperties, 
           ScoreableInterface scoreableInterface, 
           RelativeRelationship relativeRelationship
           )
           throws Exception {

      //BasicWeaponPart basicWeaponPart = (BasicWeaponPart) VECTOR_GRAPHIC_ARRAY[this.circularIndexUtil.getIndex()];

      basicWeaponPart.init(sourceLayerInterface, weaponProperties, 
              scoreableInterface, relativeRelationship);

      //this.circularIndexUtil.next();

      return basicWeaponPart;
   }
}
