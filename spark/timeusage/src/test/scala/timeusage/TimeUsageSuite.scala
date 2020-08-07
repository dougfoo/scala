package timeusage

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.rdd.RDD
import org.junit._
import org.junit.Assert.assertEquals
import java.io.File

object TimeUsageSuite {
  val conf: SparkConf = new SparkConf().setMaster("local").setAppName("TimeUsage")
  val sc: SparkContext = new SparkContext(conf)
}

class TimeUsageSuite {
  import TimeUsageSuite._

  @Test def `testObject can be instantiated`: Unit = {
    val instantiatable = try {
      true
    } catch {
      case _: Throwable => false
    }
    assert(instantiatable, "Can't instantiate a TimeUsage object")
  }

  @Test def `testVector build can be instantiated`: Unit = {
    val lines = sc.textFile("src/main/resources/timeUsage/atussum_500.csv")
    assert(lines.count() == 500, "Incorrect number of lines: " + lines.count())
  }

  @Rule def individualTestTimeout = new org.junit.rules.Timeout(100 * 1000)
}
