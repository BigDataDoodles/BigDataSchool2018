package ru.bigdata.course.system

/**
  * Created by gennady on 01/03/18.
  * extended by tendai on 13/03/18
  * Assert that a sensible song is one that contains as much less stop words as possible
  * Утвердите, что разумная песня - это та, которая содержит как можно меньше слов в списке stopwords.txt
  */
object Application extends App{
  val songFile = "resources/station-to-station.lyrics"
  val stopWordsFile = "resources/small-stopwords.txt"

  val textStats = new TextStats(songFile)

  val sensibilityRate = textStats.getWeanedWordsRatio(stopWordsFile)
  println(s"соотношение значащих слов $sensibilityRate")

  if(sensibilityRate > 0.6)
    println("песня наполнена смыслом")
  else
    println("песня полна мусора")
}

