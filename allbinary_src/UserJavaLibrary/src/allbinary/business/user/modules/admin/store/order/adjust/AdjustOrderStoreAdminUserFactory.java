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
package allbinary.business.user.modules.admin.store.order.adjust;

import allbinary.business.user.UserFactoryInterface;
import allbinary.business.user.UserInterface;

import java.util.HashMap;

public class AdjustOrderStoreAdminUserFactory implements UserFactoryInterface
{
   public AdjustOrderStoreAdminUserFactory()
   {
   }

   public UserInterface getInstance() throws Exception
   {
      return new AdjustOrderStoreAdminUser();
   }

   public UserInterface getInstance(HashMap hashMap) throws Exception
   {
      return new AdjustOrderStoreAdminUser(hashMap);
   }
}