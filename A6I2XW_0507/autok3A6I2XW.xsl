<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html lang="hu">
            <head><meta charset="UTF-8"/><title>Dokumentum elemszám</title></head>
            <body>
                <h2>A dokumentum elemeinek száma</h2>
                <p><xsl:value-of select="count(//*)"/></p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
