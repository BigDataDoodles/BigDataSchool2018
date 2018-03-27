package ru.bigdata.course.system

import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Application extends App {

  val sc = new SparkContext(new SparkConf().setMaster("local[2]").setAppName("app"))

  val pathToExtractedWords = "./src/main/resources/extracted_words.txt"

  var extractedWords = sc.broadcast(sc.textFile(pathToExtractedWords).flatMap(_.split(" ")).collect())

  val pathToSongOX = "./src/main/resources/song.txt"

  var song_1: RDD[String] = sc.textFile(pathToSongOX).flatMap(_.split(" "))

  song_1.map(_.replaceAll("[^а-яА-Я]", "")).foreach(println)

  val result = Utils.checkSense(song_1) + " часть слов песни содержат смысл"
  println(result)
  sc.stop()
}

