case class Wine(name:String, location:Location, wineryName:String, rate:Double, numberOfRate:Int, price:Double, year:Int, var stock:Int = 0, isExpensive:Boolean) extends Rateable {
  override var rating: Double = rate
  override var numberOfRates: Int = numberOfRate

  def nbExpensiveWine(wine:Wine): Int = wine.isExpensive match{
    case true => 1
    case false => 0
  }

}

