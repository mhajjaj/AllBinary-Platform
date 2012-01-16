package org.allbinary.business.advertisement;

import abcs.logic.basic.string.StringUtil;

public class AppNameLicensingAdConfiguration extends AdConfiguration
{
    public AppNameLicensingAdConfiguration()
    {
        super(new Object[] {
            StringUtil.getInstance().EMPTY_STRING,
            StringUtil.getInstance().EMPTY_STRING,
            StringUtil.getInstance().EMPTY_STRING
        });
    }
}
