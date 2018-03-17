package ru.bigdata.course.system

class TextStatsTest extends org.scalatest.FunSuite {
  /*
  * POSSIBLE LOCATIONS OF THE WAREHOUSE:
  * A fish processing/packaging plant in the warehouse district of a large
  * Russian city
  * */
  val songFile = "resources/test/conspiracy.txt"
  /*the,of,a,in*/
  val stopWordsFile = "resources/test/stopwords.txt"

  val songFileRdd = Array("POSSIBLE", "LOCATIONS", "OF", "THE", "WAREHOUSE:",
    "A", "fish", "processing/packaging", "plant", "in", "the", "warehouse",
    "district", "of", "a", "large", "Russian", "city")
  val songFileRddNoPunctuation = Array("POSSIBLE", "LOCATIONS", "OF", "THE", "WAREHOUSE",
    "A", "fish", "processing/packaging", "plant", "in", "the", "warehouse",
    "district", "of", "a", "large", "Russian", "city")
  val songFileRddNoStopWords = Array("POSSIBLE", "LOCATIONS", "WAREHOUSE",
    "fish", "processing/packaging", "plant", "warehouse", "district", "large", "Russian", "city")

  val songFileRddWeaned = Array("POSSIBLE", "LOCATIONS", "WAREHOUSE",
    "fish", "processing/packaging", "plant", "warehouse", "district", "large", "Russian", "city")

  val stopWordsRdd = Array("the","of","a","in")


  val textStats = new TextStats(songFile)
  val allWords = textStats.getAllWords()

  //test correctness of file read

  assert(allWords.collect() == songFileRdd)
  assert(allWords.collect().sameElements(songFileRdd))

  //test correctness of RDD stripped of punctuation
  assert(textStats.weanPunctuation(allWords).collect().sameElements(songFileRddNoPunctuation))
  //test correctness of RDD stripped of stop words
  //assert(textStats.weanStopWords(stopWordsFile).collect().sameElements(songFileRddNoStopWords))

  //test correctness of weanedWordsRatio
  assert(textStats.getAllWords().collect().length == songFileRdd.length)
  //assert(textStats.getWeanedWordsRatio(stopWordsFile) == songFileRddWeaned.length.toDouble/songFileRdd.length
  // .toDouble)

}
