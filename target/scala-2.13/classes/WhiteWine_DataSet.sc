import com.github.tototoshi.csv.CSVReader

import java.io.File
import scala.::


trait Rateable{
  var rating:Double
  var numberOfRates:Int
  def rate(rate:Int)
}

case class Location(country:String, region:String){

}

case class Wine(name:String, location:Location, wineryName:String, rate:Double, numberOfRate:Int, price:Double, year:Int) extends Rateable {
  override var rating: Double = rate
  override var numberOfRates: Int = numberOfRate


  override def rate(rate: Int): Unit = {
  }

}

case class Winery(name:String){
 /* def sellWine(wine:Wine, number:Int) = {
    var i = 0
    while(wines(i).equals(wine)) i += 1
    val newStock = wines(i).stock - number
    if(newStock <= 0) println("no stock")
    else println(newStock)
  }*/


  def createNewWine(wine:Wine) = {
    val newList = wine :: (wines)
    copy(wines = newList)
  }
}



/*val loc = Location("Switzerland", "Saint-Maurice")
val fendant = Wine("fendant", loc, 4.6,25, 25.30,2020, 80 )


val pinot = Wine("pinot", loc, 4.6,25, 25.30,2020, 80)
val chardonnay = Wine("chardonnay", loc, 4.6,25, 25.30,2020, 80)
val list =List(fendant,pinot)

val cave = Winery("cave", list)

cave.wines.foreach(wine => println(wine.name))


val cave2 = cave.createWine(chardonnay)


cave2.wines.foreach(wine => println(wine.name + " : " + wine.stock))

cave2.sellWine(pinot, 5)*/

val reader = CSVReader.open(new File("C:\\ProjetTest\\WhiteWine.csv"))

var allWinesAList = reader.toStream.map( w => Wine(w(0), Location(w(1),w(2)), w(3), w(4).toDouble,w(5).toInt,
  w(6).toDouble, w(7).toInt))
allWinesAList.foreach(wine => println(wine.name))
var allWineries = reader.toStream.map(w => Winery(w(3))).distinct
allWineries.foreach(winery => println(winery.name))
//val list = allWinesAList.map(w => w.wineryName.equals(allWineries(0).name))





