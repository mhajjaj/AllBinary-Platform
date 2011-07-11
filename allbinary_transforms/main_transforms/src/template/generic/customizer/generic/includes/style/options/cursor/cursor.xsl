<?xml version="1.0" encoding="UTF-8" ?>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

<xsl:template name="genericCursorOptions" >
   <xsl:param name="default" />
   
   <option value="{$default}" ><xsl:value-of select="$default" /></option>
   <option value="default" >Default</option>
   <option value="hand" >Hand</option>
   <option value="pointer" >Pointer</option>
   
</xsl:template>

</xsl:stylesheet> 