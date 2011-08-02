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
package allbinary.business.time;

public class Time
{
   private Long timeLong;

   public Time(String time)
   {
      this.timeLong = new Long(time);
   }

   public Time(long time)
   {
      this.timeLong = new Long(time);
   }
   
   public Long getLong()
   {
      return this.timeLong;
   }
   
   public long getlong()
   {
      return this.timeLong.longValue();
   }
   
   public String toString()
   {
      return this.getLong().toString();
   }
}
