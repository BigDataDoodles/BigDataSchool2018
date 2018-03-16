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

  //wean stop words out of the text
  val weanedText = textStats.weanStopWords(stopWordsFile)
  val weanedTextCount = weanedText.collect().length
  val allTextCount = textStats.getAllWords().collect().length

  val sensibilityRate = weanedTextCount.toDouble/allTextCount.toDouble
  println(s"соотношение значащих слов $sensibilityRate")

  if(sensibilityRate > 0.6)
    println("песня наполнена смыслом")
  else
    println("песня полна мусора")
}

