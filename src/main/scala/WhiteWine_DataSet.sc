import com.github.tototoshi.csv.CSVReader

import java.io.File



trait Rateable{
  var rating:Double
  var numberOfRates:Int
  def rate(rate:Int)
}

case class Location(country:String, region:String){

}

case class Wine(name:String, price:Int, rate:Double, numberOfRate:Int, year:Int, stock:Int, location:Location) extends Rateable with Product {
  override var rating: Double = rate
  override var numberOfRates: Int = numberOfRate

  override def rate(rate: Int): Unit = {
  }

  def app(name:String, price:Int, rate:Double, numberOfRate:Int, year:Int, stock:Int, location:Location): Unit ={
    new Wine(name,price,rate, numberOfRate, stock, year, location)
  }
}

case class Winery(name:String, wines:List[Wine]){
  def sellWine(wine:Wine, number:Int) = {
    var i = 0
    while(wines(i).equals(wine)) i += 1
    val newStock = wines(i).stock - number
    if(newStock <= 0) println("no stock")
    else println(newStock)
  }

  def createWine(wine:Wine) = {
    val newList = wine :: wines
    copy(wines = newList)
  }
}



val loc = Location("Switzerland", "Saint-Maurice")
val fendant = Wine("fendant", 30,4.6, 25,2020, 80, loc)


val pinot = Wine("pinot", 12, 4.1,14,2015,30, loc)
val chardonnay = Wine("chardonnay", 12, 4.1,14,2015,30, loc)
val list =List(fendant,pinot)

val cave = Winery("cave", list)

cave.wines.foreach(wine => println(wine.name))


val cave2 = cave.createWine(chardonnay)


cave2.wines.foreach(wine => println(wine.name + " : " + wine.stock))

cave2.sellWine(pinot, 5)
val reader = CSVReader.open(new File("C:\\ProjetTest\\WhiteWine.csv"))
var wineList = reader.all().foreach(list => println(list) )

