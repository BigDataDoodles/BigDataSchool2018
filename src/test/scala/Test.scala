import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.FunSuite
import ru.bigdata.course.system.Utils

object Test extends FunSuite {

  val sc = new SparkContext(new SparkConf().setMaster("local[2]").setAppName("Test"))
  val pathToTestSong = "./src/main/resources/testSong.txt"
  var songBeforeCheck: RDD[String] = sc.textFile(pathToTestSong).flatMap(_.split(" "))
  private val actualResult = Utils.checkSense(songBeforeCheck)
  private val expectedResult = 11 / 27

  assert(actualResult == expectedResult)
}
