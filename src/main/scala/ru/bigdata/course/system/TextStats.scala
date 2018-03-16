package ru.bigdata.course.system
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/*require the test source file and the source of the stop words */
class TextStats(src: String) {
  private val spark = SparkSession.builder.master("local[2]").appName("Text Stats").getOrCreate().sparkContext
  private val allWords = spark.textFile(src).flatMap(word => word.split(" "))

  def weanStopWords(stopFile: String = ""): RDD[String] = {
    // Flatten, collect, and broadcast.
    val stopWords = if (!stopFile.isEmpty())
      spark.textFile(stopFile).flatMap(word => word.toLowerCase.split(",")).map(_.trim)
    else
      spark.parallelize(Array(""))

    val broadcastStopWords = spark.broadcast(stopWords.collect.toSet)

    val punctuationStrippedWords = this.weanPunctuation(allWords)

    val cleanedWords = punctuationStrippedWords.filter(word => !broadcastStopWords.value.contains(word.toLowerCase()))
    cleanedWords
  }
  def weanPunctuation(text : RDD[String]):RDD[String]={
    text.map(word => word.replaceAll("""([\p{Punct}&&[^.]]|\b\p{IsLetter}{1,2}\b)\s*""", ""))
  }
  def sortByFrequency(text: RDD[String]): RDD[(String, Int)] = {
    text.map(word => (word, 1)).reduceByKey((accumulator, n) => accumulator + n).sortBy(a=>a._2,false)
  }
  def sortByLengthOfWords(text: RDD[String], ascending: Boolean = true):RDD[String] = {
    text.sortBy(word => word.length,ascending)
  }
  def getAllWords(): RDD[String]={
    return allWords
  }
}
