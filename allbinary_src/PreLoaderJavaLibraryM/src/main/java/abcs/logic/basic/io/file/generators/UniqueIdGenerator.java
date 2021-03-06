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
package abcs.logic.basic.io.file.generators;

import java.io.IOException;

import abcs.logic.basic.io.AbDataInputStream;
import abcs.logic.basic.io.AbDataOutputStream;
import abcs.logic.basic.io.AbFileInputStream;
import abcs.logic.basic.io.AbFileOutputStream;
import abcs.logic.basic.io.DataOutputStreamFactory;
import abcs.logic.basic.io.StreamUtil;
import abcs.logic.basic.io.file.AbFile;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class UniqueIdGenerator implements IdGeneratorInterface
{
   //private String uniqueIdFile;
   private AbFile newFile;
   private long id = 0;

   public UniqueIdGenerator()
   {
   }
   
   public void initialize(int value)
   {
      try
      {
         newFile.createNewFile();

         AbDataOutputStream idData =
             DataOutputStreamFactory.getInstance().getInstance(newFile);

         idData.writeLong(value);

      }catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.IDLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "initialize", e));
         }
         
      }
   }
   
   public synchronized void setFile(String filePathName) throws Exception
   {
      //uniqueIdFile=filePathName;
      newFile = new AbFile(filePathName);
   }
   
   public synchronized String getNext() throws IOException
   {
	   AbDataInputStream idData = null;
	   AbDataOutputStream idOutData = null;
      try
      {
    	 AbFileInputStream idFile = new AbFileInputStream(this.newFile);
    	 idData = new AbDataInputStream(idFile);
    	 
         id = idData.readLong();
         
         AbFileOutputStream idOutFile = new AbFileOutputStream(this.newFile);
         idOutData = new AbDataOutputStream(idOutFile);
         idOutData.writeLong(id + 1);
         
         Long idLong = new Long(id);
         return idLong.toString();
      }catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.IDLOGGING))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "getNext", e));
         }
         return "Error";
      }
      finally
      {
    	  StreamUtil.getInstance().close(idData);
    	  StreamUtil.getInstance().close(idOutData);
      }      
   }
}
