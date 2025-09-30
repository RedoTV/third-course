<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:param name="min-rating" select="7.5"/>
  <xsl:param name="min-actor-age" select="40"/>

  <xsl:template match="/">
    <html>
      <head>
        <title>Фильмы Marvel</title>
        <style>
          body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #1a1a1a;
            color: #e0e0e0;
            padding: 20px;
          }
          h1 {
            width: 100%;
            text-align: center;
            color: #1e88e5;
            margin-bottom: 40px;
          }
          .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 25px;
          }
          .card {
            background: #2c2c2c;
            border: 1px solid #444;
            border-radius: 8px;
            padding: 20px;
            width: 320px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.5);
            transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
          }
          .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(30, 136, 229, 0.4);
          }
          .card-title {
            font-size: 1.5em;
            font-weight: bold;
            color: #1e88e5;
            margin-bottom: 15px;
            padding-bottom: 10px;
            border-bottom: 2px solid #1e88e5;
          }
          .info-line {
            margin-bottom: 10px;
            font-size: 0.95em;
          }
          .label {
            font-weight: bold;
            color: #90caf9;
            min-width: 120px;
            display: inline-block;
          }
          .notes {
            color: #ffab40;
            font-weight: bold;
            font-style: italic;
          }
          ul {
            list-style-type: none;
            padding-left: 0;
            margin-top: 5px;
          }
          li {
            padding: 4px;
            border-radius: 4px;
            background-color: rgba(255, 255, 255, 0.05);
            margin-bottom: 5px;
          }
        </style>
      </head>
      <body>
        <h1>Фильмы Marvel (рейтинг ≥ <xsl:value-of select="$min-rating"/>)</h1>
        
        <div class="card-container">
            <xsl:for-each select="/marvel-movies/movie[number(@rating) >= $min-rating]">
                <xsl:sort select="number(@rating)" order="descending"/>
                <xsl:sort select="info/year" order="ascending"/>

                <div class="card">
                    <div class="card-title"><xsl:value-of select="info/name"/></div>

                    <div class="info-line"><span class="label">Год:</span><xsl:value-of select="info/year"/></div>
                    <div class="info-line"><span class="label">Длительность:</span><xsl:value-of select="concat(info/duration, ' мин.')"/></div>
                    <div class="info-line"><span class="label">Бюджет:</span><xsl:value-of select="concat(info/budget div 1000000, ' млн $')"/></div>
                    
                    <div class="info-line">
                        <span class="label">Заметки:</span>
                        <span class="notes">
                            <xsl:choose>
                                <xsl:when test="info/duration > 140">Очень длинный</xsl:when>
                                <xsl:when test="info/budget > 20000000">Блокбастер</xsl:when>
                                <xsl:otherwise>-</xsl:otherwise>
                            </xsl:choose>
                        </span>
                    </div>

                    <div class="info-line">
                        <span class="label">Актеры (возраст ≥ <xsl:value-of select="$min-actor-age"/>):</span>
                        <ul>
                            <xsl:for-each select="actor[number(age) >= $min-actor-age]">
                                <xsl:sort select="name"/>
                                <li><xsl:value-of select="concat(name, ' (', @role, ')')"/></li>
                            </xsl:for-each>
                        </ul>
                    </div>
                </div>
            </xsl:for-each>
        </div>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>