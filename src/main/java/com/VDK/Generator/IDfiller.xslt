<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:vlx="http://www.i2group.com/Schemas/2001-12-07/VLXSchema">
<!--	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/> -->
	<xsl:template match="/vlx:vlx/content/ends/end">
		<xsl:copy>
			<xsl:variable name="catType" select="@catType"/>
			<xsl:attribute name="id">	
				<xsl:call-template name="CleanID">
					<xsl:with-param name="ID" select="properties/*[local-name() = /vlx:vlx/typeCatalogue/endTypes/endType[@localName=$catType]/property[@pGUID = /vlx:vlx/typeCatalogue/@identityProperty]/@localName]"/>
				</xsl:call-template>
			</xsl:attribute>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	<xsl:template match="/vlx:vlx/content/links/link">
		<xsl:copy>
			<xsl:variable name="catType" select="@catType"/>
			<xsl:attribute name="id">	
				<xsl:call-template name="CleanID">
					<xsl:with-param name="ID" select="properties/*[local-name() = /vlx:vlx/typeCatalogue/linkTypes/linkType[@localName=$catType]/property[@pGUID = /vlx:vlx/typeCatalogue/@identityProperty]/@localName]"/>
				</xsl:call-template>
			</xsl:attribute>
			<xsl:attribute name="end1id">
				<xsl:call-template name="CleanID">
					<xsl:with-param name="ID" select="@end1identity"/>
				</xsl:call-template>
			</xsl:attribute>
			<xsl:attribute name="end2id">
				<xsl:call-template name="CleanID">
					<xsl:with-param name="ID" select="@end2identity"/>
				</xsl:call-template>
			</xsl:attribute>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>

	<xsl:template name="CleanID">
		<xsl:param name="ID"/>id-<xsl:call-template name="ProcessIDChar"><xsl:with-param name="ID" select="$ID"/></xsl:call-template>
	</xsl:template>
	<xsl:template name="ProcessIDChar">
		<xsl:param name="ID"/>
		<xsl:variable name="char" select="substring($ID,1,1)"/>
		<xsl:choose>
			<!-- CleanID must not contain any of: "'()*?{}[]+\/!$%^&=:;@~|<>, -->
			<xsl:when test="$char = '&quot;'">-quote-</xsl:when>
			<xsl:when test="$char = &quot;'&quot; ">-apos-</xsl:when>
			<xsl:when test="$char = '`'">-btick-</xsl:when>
			<xsl:when test="$char = '('">-lparen-</xsl:when>
			<xsl:when test="$char = ')'">-rparen-</xsl:when>
			<xsl:when test="$char = '*'">-asterix-</xsl:when>
			<xsl:when test="$char = '?'">-qmark-</xsl:when>
			<xsl:when test="$char = '{'">-lbrack-</xsl:when>
			<xsl:when test="$char = '}'">-rbrack-</xsl:when>
			<xsl:when test="$char = '['">-lsbrack-</xsl:when>
			<xsl:when test="$char = ']'">-rsbrack-</xsl:when>
			<xsl:when test="$char = '+'">-plus-</xsl:when>
			<xsl:when test="$char = '\'">-bslash-</xsl:when>
			<xsl:when test="$char = '/'">-fslash-</xsl:when>
			<xsl:when test="$char = '!'">-excl-</xsl:when>
			<xsl:when test="$char = '$'">-dollar-</xsl:when>
			<xsl:when test="$char = '%'">-pct-</xsl:when>
			<xsl:when test="$char = '^'">-circ-</xsl:when>
			<xsl:when test="$char = '&amp;'">-amp-</xsl:when>
			<xsl:when test="$char = '='">-equall-</xsl:when>
			<xsl:when test="$char = ':'">-scolon-</xsl:when>
			<xsl:when test="$char = ';'">-colon-</xsl:when>
			<xsl:when test="$char = '@'">-at-</xsl:when>
			<xsl:when test="$char = '~'">-tilde-</xsl:when>
			<xsl:when test="$char = '|'">-pipe-</xsl:when>
			<xsl:when test="$char = '&lt;'">-lt-</xsl:when>
			<xsl:when test="$char = '&gt;'">-gt-</xsl:when>
			<xsl:when test="$char = ','">-comma-</xsl:when>
			<xsl:when test="$char = ' '">-space-</xsl:when>
			<xsl:otherwise><xsl:value-of select="$char"/></xsl:otherwise>
		</xsl:choose>
		<xsl:if test="substring($ID,2)">
			<xsl:call-template name="ProcessIDChar"><xsl:with-param name="ID" select="substring($ID,2)"/></xsl:call-template>
		</xsl:if>
	</xsl:template>

	<xsl:template match="*">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>
