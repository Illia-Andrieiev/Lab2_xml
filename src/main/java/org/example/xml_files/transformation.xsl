<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes" />
    <xsl:key name="byAttribute" match="Flowers" use="@Origin" />

    <xsl:template match="/Flower">
        <Flower>
            <xsl:for-each select="Flowers[generate-id() = generate-id(key('byAttribute', @Origin)[1])]">
                <xsl:element name="{@Origin}">
                    <xsl:for-each select="key('byAttribute', @Origin)">
                        <xsl:copy-of select="." />
                    </xsl:for-each>
                </xsl:element>
            </xsl:for-each>
        </Flower>
    </xsl:template>
</xsl:stylesheet>
