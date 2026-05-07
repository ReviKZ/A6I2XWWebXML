<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" encoding="UTF-8" indent="yes"/>

	<xsl:template match="/">

		<html lang="hu">
			<head>
				<meta charset="UTF-8"/>
				<title>A6I2XW Órarend – 2026. II. félév</title>

				<style>
					body{
					font-family: Arial;
					background:#f4f7fb;
					padding:30px;
					}

					h1{
					text-align:center;
					color:#1d4ed8;
					}

					table{
					width:100%;
					border-collapse:collapse;
					background:white;
					}

					th{
					background:#1d4ed8;
					color:white;
					padding:12px;
					}

					td{
					border:1px solid #d1d5db;
					padding:10px;
					}

					tr:nth-child(even){
					background:#f9fafb;
					}
				</style>
			</head>

			<body>

				<h1>A6I2XW Órarend – 2026. II. félév</h1>

				<table>

					<tr>
						<th>ID</th>
						<th>Típus</th>
						<th>Kurzus</th>
						<th>Nap</th>
						<th>Időpont</th>
						<th>Helyszín</th>
						<th>Oktató</th>
						<th>Szak</th>
					</tr>

					<xsl:for-each select="/A6I2XW_orarend/ora">

						<tr>

							<td>
								<xsl:value-of select="@id"/>
							</td>

							<td>
								<xsl:value-of select="@tipus"/>
							</td>

							<td>
								<xsl:value-of select="kurzus"/>
							</td>

							<td>
								<xsl:value-of select="idopont/nap"/>
							</td>

							<td>
								<xsl:value-of select="idopont/tol"/>
								-
								<xsl:value-of select="idopont/ig"/>
							</td>

							<td>
								<xsl:value-of select="helyszin"/>
							</td>

							<td>
								<xsl:value-of select="oktato"/>
							</td>

							<td>
								<xsl:value-of select="szak"/>
							</td>

						</tr>

					</xsl:for-each>

				</table>

			</body>
		</html>

	</xsl:template>
</xsl:stylesheet>