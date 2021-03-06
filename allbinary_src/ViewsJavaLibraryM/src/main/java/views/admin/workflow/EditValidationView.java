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
package views.admin.workflow;

import abcs.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;


import abcs.logic.communication.log.LogUtil;

import allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;

import allbinary.logic.control.workflow.WorkFlowData;

import allbinary.data.tables.workflow.WorkFlowEntityFactory;


public class EditValidationView extends WorkFlowView implements ValidationComponentInterface
{
   private String workFlowName;
   
   public EditValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      TransformInfoHttpInterface httpTransformInfoInterface = 
         (TransformInfoHttpInterface) this.getTransformInfoInterface();

      this.workFlowName = httpTransformInfoInterface.getPageContext().getRequest().getParameter(
         WorkFlowData.getInstance().NAME);
   }
   
   public Boolean isValid()
   {
      try
      {         
         if(this.workFlowName==null)
         {
            return Boolean.FALSE;
         }
         
         if(WorkFlowEntityFactory.getInstance().get(
            this.workFlowName, 
            this.getTransformInfoInterface().getStoreName())==null)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
               LogUtil.put(LogFactory.getInstance("WorkFlow does not exist.",this,"isValid()"));
            }
            return Boolean.FALSE;
         }
                  
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form",this,"isValid()",e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         if(this.workFlowName==null)
         {
            stringBuffer.append("WorkFlow name is invalid<br />");
         }
         
         if(WorkFlowEntityFactory.getInstance().get(
            this.workFlowName, 
            this.getTransformInfoInterface().getStoreName())==null)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
               LogUtil.put(LogFactory.getInstance("WorkFlow does not exist.",this,"validationInfo()"));
            }
            stringBuffer.append("WorkFlow does not exist<br />");
         }
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
         }
         return "Error Validating Form";
      }
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
}
