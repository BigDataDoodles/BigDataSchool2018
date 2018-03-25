package bigdata.project.system

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

class PlatformStats(brokers:String,topics: String) {
  val conf = new SparkConf().setAppName("referee-stats").setMaster("local[2]")
  val ssc = new StreamingContext(conf, Seconds(5))

  val topicsSet = topics.split(",").toSet
  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> brokers,
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "1",
    "auto.offset.reset" -> "latest",
    "enable.auto.commit" -> (false: java.lang.Boolean)
  )

  val messages = KafkaUtils.createDirectStream[String, String](
    ssc,
    LocationStrategies.PreferConsistent,
    ConsumerStrategies.Subscribe[String, String](topicsSet, kafkaParams))

  val data = messages.map(_.value.split(",")).persist().map(record => (record.toArray, 1))
  data.print

  def sortByFrequency(text: RDD[String]): RDD[(String, Int)] = {
    text.map(word => (word, 1)).reduceByKey((accumulator, n) => accumulator + n).sortBy(a=>a._2,false)
  }

  ssc.start()
  ssc.awaitTermination()
}

