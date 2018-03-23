package ru.bigdata.course.system

import org.apache.spark.rdd.RDD



 object Utils {

  def checkSense(array: RDD[String]): Double = {
    var newArray = array.map(_.replaceAll("[^а-яА-Я]+", "")).map(word => (word, 1)).reduceByKey((x, y) => x + y)
    val arrayWords = newArray.filter(word => Application.extractedWords.value.contains(word._1))
    val relationship = arrayWords.count / newArray.count()
    relationship

  }

}


