package ru.bigdata.course.system

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by gennady on 01/03/18.
  */
object Application extends App {

  val conf = new SparkConf()
    .setAppName("Vocabulary Battle")
    .setMaster("local[*]")
  val sc = new SparkContext(conf)
  sc.setLogLevel("ERROR")


  val stopwordsInputRu = sc.textFile("./src/main/resources/stop-words-russian.txt")
  val stopwordsInputEn = sc.textFile("./src/main/resources/stop-words-english.txt")
  val stopwordsRu = processStopwords(stopwordsInputRu)
  val stopwordsEn = processStopwords(stopwordsInputEn)
  val stopwords = stopwordsEn.union(stopwordsRu)

  val author1 = sc.textFile("./src/main/resources/oxxy.txt")
  val author2 = sc.textFile("./src/main/resources/slava.txt")
  val words_author1 = processCorpus(author1, stopwords)
  val words_author2 = processCorpus(author2, stopwords)

  println("Vocabulary Sizes")
  println(words_author1.count(), words_author2.count())

  words_author1.filter(_._2 > 5).foreach(pair => println(pair))
  //words_author1.sortByKey().
  //words_author1.subtractByKey(words_author2).saveAsTextFile("./src/main/resources/results.txt")
  //words_author1.saveAsTextFile("./src/main/resources/author1.txt")

}
