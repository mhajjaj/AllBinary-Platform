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
package allbinary.data.tables.user.address.billing;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.data.tables.user.address.StreetAddressesEntity;

public class BillingAddressesEntityFactory
{
   private static final BillingAddressesEntityFactory instance =
           new BillingAddressesEntityFactory();

    /**
     * @return the instance
     */
    public static BillingAddressesEntityFactory getInstance() {
        return instance;
    }

   //private final String CLASSNAME = "allbinary.data.tables.BillingAddressesEntity";
   
   private BillingAddressesEntityFactory()
   {
   }
   
   public StreetAddressesEntity getInstance(String userName) //throws LicensingException
   {
      try
      {
      /*
      Object params[] = new Object[1];
      Class classes[] = new Class[1];
       
      classes[0] = userName.getClass();
       
      params[0] = (Object) userName;
      Object object = AbeFactory.getInstance(CLASSNAME,classes, params);
      return (StreetAddressesEntityInterface) InterfaceCastProxy.newInstance(object);
       */
         return new BillingAddressesEntity(userName);
      }
      /*
      catch(LicensingException e)
      {
         String error = "Failed to get instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(error,"BillingAddressEntityFactory","getInstance()",e);
         }
         throw e;
      }
       */
      catch(Exception e)
      {
         String error = "Failed get Instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.ENTITYFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,"BillingAddressEntityFactory","getInstance()",e));
         }
         return null;
      }
   }
   
}
