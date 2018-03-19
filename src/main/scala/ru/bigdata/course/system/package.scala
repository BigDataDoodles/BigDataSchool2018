package ru.bigdata.course

import org.apache.spark.rdd.RDD

package object system {

  def processCorpus(rdd: RDD[String], stopwords: Set[String]): RDD[(String, Int)] = {
    rdd.flatMap(line => line.split(" "))
      .map(_.replaceAll("""[\p{Punct}]""", "").trim.toLowerCase)
      .filter(!_.isEmpty)
      .filter(!stopwords.contains(_))
      .map((_, 1))
      .reduceByKey(_ + _)

  }

  def processStopwords(rdd: RDD[String]): Set[String] = {
    rdd
      .flatMap(x => x.split("\n"))
      .collect.toSet
  }

}
