<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="text" encoding="UTF-8"/>

  <xsl:param name="report-title" select="'Подробный отчет по фильмам Marvel'"/>

  <xsl:template match="/">
    <xsl:value-of select="$report-title"/>
    <xsl:text>&#10;========================================&#10;&#10;</xsl:text>

    <xsl:for-each select="/marvel-movies/movie">
      <xsl:sort select="info/name" data-type="text"/>

      <xsl:value-of select="concat('* ', info/name, ' (', info/year, ')')"/>
      <xsl:text>&#10;</xsl:text>
      <xsl:text>  ID: </xsl:text><xsl:value-of select="@id"/>
      <xsl:text> | Рейтинг: </xsl:text><xsl:value-of select="@rating"/>
      <xsl:text> | Возрастной допуск: </xsl:text><xsl:value-of select="@age-rating"/>
      <xsl:text>&#10;</xsl:text>
      
      <xsl:text>  Длительность: </xsl:text><xsl:value-of select="info/duration"/><xsl:text> мин.</xsl:text>
      <xsl:text> | Бюджет: </xsl:text><xsl:value-of select="info/budget div 1000000"/><xsl:text> млн $</xsl:text>
      <xsl:text>&#10;</xsl:text>
      
      <xsl:text>  Заметки: </xsl:text>
      <xsl:choose>
          <xsl:when test="info/duration > 140">Очень длинный фильм</xsl:when>
          <xsl:when test="info/budget > 20000000">Блокбастер</xsl:when>
          <xsl:otherwise>—</xsl:otherwise>
      </xsl:choose>
      <xsl:text>&#10;</xsl:text>

      <xsl:text>  Актерский состав:</xsl:text>
      <xsl:text>&#10;</xsl:text>
      <xsl:for-each select="actor">
          <xsl:sort select="name"/>
          <xsl:value-of select="concat('    - ', name, ' (Роль: ', @role, ', возраст: ', age, ')')"/>
          <xsl:text>&#10;</xsl:text>
      </xsl:for-each>

      <xsl:text>&#10;----------------------------------------&#10;&#10;</xsl:text>
    </xsl:for-each>
    
    <xsl:variable name="all-movies" select="/marvel-movies/movie"/>
    <xsl:text>Общая статистика:&#10;</xsl:text>
    <xsl:text>========================================&#10;</xsl:text>
    <xsl:value-of select="concat('Всего фильмов в базе: ', count($all-movies))"/>
    <xsl:text>&#10;</xsl:text>
    <xsl:value-of select="concat('Суммарный бюджет всех фильмов: ', sum($all-movies/info/budget) div 1000000, ' млн $')"/>
    <xsl:text>&#10;</xsl:text>
  </xsl:template>
</xsl:stylesheet>