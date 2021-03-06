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
package admin.taghelpers;

import java.util.Vector;

import abcs.business.user.role.BasicUserRole;
import abcs.globals.URLGLOBALS;
import abcs.logic.basic.path.AbPath;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.role.UserRole;
import allbinary.logic.communication.http.file.upload.HttpFilePermissions;
import allbinary.logic.communication.http.request.HttpRequestUtil;

public class AuthenticationHelperUtil
{
    private static final AuthenticationHelperUtil instance = new AuthenticationHelperUtil();

    public static AuthenticationHelperUtil getInstance()
    {
        return instance;
    }

    public final boolean isAuthorized(
        AuthenticationHelper authenticationHelper, String filePath)
        throws Exception
    {
        if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
        {
            LogUtil.put(LogFactory.getInstance("Start: " + filePath, this, "isAuthorized()"));
        }

        int endIndex = HttpRequestUtil.getInstance().getLastSeparatorIndex(filePath);

        AbPath fullPath = new AbPath(URLGLOBALS.getWebappPath() + filePath.substring(0, endIndex));

        HttpFilePermissions downloadFilePermissions = new HttpFilePermissions(fullPath);

        Vector userRoles = downloadFilePermissions.getUserRoles();

        UserRole userRole = authenticationHelper.getRole();

        BasicUserRole basicUserRole = userRole.getBasicUserRole();

        if (userRoles.contains(basicUserRole))
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Authorized", this, "isAuthorized()"));
            }
        	
            return true;
        } else
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
                LogUtil.put(LogFactory.getInstance("Not Authorized: " + basicUserRole.toString(), this, "isAuthorized()"));
            }

            return false;
        }
    }
}
