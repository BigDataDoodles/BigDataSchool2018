package ru.bigdata.course.system

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types._
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}
import ru.bigdata.course.utils._


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

  val obscene = processStopwords(sc.textFile("./src/main/resources/swear-words-russian.txt"))

  val corpus_author1 = sc.textFile("./src/main/resources/oxxxy.txt")
  val corpus_author2 = sc.textFile("./src/main/resources/slava.txt")

  val stats1 = getStats("Oxxxymiron",corpus_author1,stopwords,obscene)
  val stats2 = getStats("Slava KPSS",corpus_author2,stopwords,obscene)

  val row1 = Row.fromSeq(stats1)
  val row2 = Row.fromSeq(stats2)
  val rdd = sc.makeRDD(List(row1,row2))
  val fields = List(
    StructField("Name", StringType, nullable = false),
    StructField("Total", LongType, nullable = false),
    StructField("Cleaned", StringType, nullable = false),
    StructField("Cyrillic",  StringType,nullable = false),
    StructField("Latin",  StringType, nullable = false),
    StructField("Obscene", StringType, nullable = false)
  )
  val spark = SparkSession.builder().getOrCreate()
  import spark.implicits._
  val ds = spark.createDataFrame(rdd, StructType(fields))
  ds.show()


}
