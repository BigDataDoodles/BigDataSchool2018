package ru.bigdata.course

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import ru.bigdata.course.system.Application.stats1

package object utils {

  def processCorpus(rdd: RDD[String]): RDD[(String, Int)] =
    rdd.flatMap(line => line.split(" "))
      .map(_.replaceAll("""[^a-zA-Zа-яА-Я\\*\\-]+""", "").trim.toLowerCase)
      .filter(!_.isEmpty)
      .filter(_ != "-")
      .map((_, 1))
      .reduceByKey(_ + _)

  def removeStopwords(rdd: RDD[(String,Int)],stopwords: Set[String]): RDD[(String,Int)] =
  {
    rdd.filter(item => !stopwords.contains(item._1))
  }

  def processStopwords(rdd: RDD[String]): Set[String] = {
    rdd
      .flatMap(x => x.split("\n"))
      .collect.toSet
  }

  def isLatin(word: String): Boolean =

    word.toList.forall(c => (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))


  def isCyrillic(word: String): Boolean =

    "\\p{IsCyrillic}*".r.findFirstIn(word).map(_.size).contains(word.size)


  def isObscene(word: String,obscene: Set[String]): Boolean =
    word.contains("*") | obscene.contains(word)

  def getStats(name:String,corpus:RDD[String],stopwords:Set[String],obscene:Set[String]): List[Any] = {
    val words = processCorpus(corpus)
    val cleanedCorpus = removeStopwords(words,stopwords)
    List(
      name,
      words.count(),
      "%.3f".format(cleanedCorpus.count().toDouble/words.count()),
      "%.3f".format(cleanedCorpus.filter(item => isCyrillic(item._1)).count().toDouble/ cleanedCorpus.count()),
        "%.3f".format(cleanedCorpus.filter(item => isLatin(item._1)).count().toDouble/ cleanedCorpus.count()),
        "%.3f".format(cleanedCorpus.filter(item => isObscene(item._1, obscene)).count().toDouble/ cleanedCorpus.count())
    )
  }
}
