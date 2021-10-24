import com.github.tototoshi.csv.CSVReader

import java.io.File

object Launcher {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("C:\\ProjetTest\\WhiteWine.csv"))
    val allWinesAList = reader.toStream.map( w => Wine(w(0), Location(w(1),w(2)), w(3), w(4).toDouble,w(5).toInt,
      w(6).toDouble, w(7).toInt))
    //for(wine <- allWinesAList)println(wine)
    allWinesAList.foreach(w => println(w))
    println(" ")
    val allWineries = reader.toStream.map(w => Winery(w(3))).distinct
    //allWineries.foreach(println)

  }
}
