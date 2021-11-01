case class Wine(name:String, location:Location, wineryName:String, rate:Double, numberOfRate:Int, price:Double, year:Int, stock:Int ) extends Rateable {
  override var rating: Double = rate
  override var numberOfRates: Int = numberOfRate

  def addWineStock(quantity: Int): Wine ={
    quantity match {
      case _ if quantity < 0 => throw new IllegalArgumentException(" You can't add negative number")
      case _ => Wine(name,location,wineryName,rate,numberOfRate,price,year,quantity + stock)
    }
  }
  def sellWine(quantity: Int): Wine ={
    quantity match {
      case _ if quantity < 0 => throw new IllegalArgumentException(" You can't add negative number")
      case _ if stock < quantity => throw new IllegalArgumentException ("There is not enough wine stock")
      case _ => Wine(name,location,wineryName,rate,numberOfRate,price,year,stock - quantity)
    }
  }

}

