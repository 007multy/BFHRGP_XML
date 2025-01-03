﻿<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">

    <xsl:template match="/">
		<html>
			<body>
				<h1>Hallgatok adatai - for-each, value-of</h1>
				<table border="4">
					<tr bgcolor ="#9acd32">
						<th>ID</th>
						<th>Vezeteknev</th>
						<th>Keresztnev</th>
						<th>Becenev</th>
						<th>Kor</th>
						<th>Osztondij</th>
					</tr>
					
					<xsl:for-each select="/class/student">
						<tr>
							<td>
								<xsl:value-of select="@id"/>
							</td>
							<td>
								<xsl:value-of select="vezeteknev"/>
							</td>
							<td>
								<xsl:value-of select="keresztnev"/>
							</td>
							<td>
								<xsl:value-of select="becenev"/>
							</td>
							<td>
								<xsl:value-of select="kor"/>
							</td>
							<td>
								<xsl:value-of select="osztondij"/>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
