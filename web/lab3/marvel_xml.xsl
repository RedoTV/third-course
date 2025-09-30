<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="/marvel-movies">
    <xsl:variable name="filtered-movies" select="movie[info/year >= 2010]"/>
    
    <xsl:copy>
      <xsl:apply-templates select="@*"/>
      
      <xsl:apply-templates select="$filtered-movies"/>
      
      <summary>
        <totalMovies><xsl:value-of select="count($filtered-movies)"/></totalMovies>
        <totalBudget><xsl:value-of select="sum($filtered-movies/info/budget)"/></totalBudget>
        <averageRating><xsl:value-of select="format-number(sum($filtered-movies/@rating) div count($filtered-movies), '0.00')"/></averageRating>
      </summary>
    </xsl:copy>
  </xsl:template>

  <xsl:template match="movie">
    <xsl:copy>
      <xsl:apply-templates select="@*"/>
      <xsl:attribute name="budgetInMillions">
        <xsl:value-of select="info/budget div 1000000"/>
      </xsl:attribute>
      <xsl:apply-templates select="info"/>
      <casting>
        <xsl:apply-templates select="actor">
            <xsl:sort select="name" order="ascending"/>
        </xsl:apply-templates>
      </casting>
    </xsl:copy>
  </xsl:template>
  
  <xsl:template match="actor">
      <actor>
        <role><xsl:value-of select="@role"/></role>
        <xsl:copy-of select="*"/>
     </actor>
  </xsl:template>
</xsl:stylesheet>