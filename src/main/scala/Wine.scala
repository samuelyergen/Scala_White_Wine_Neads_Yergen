case class Wine(name:String, location:Location, wineryName:String, rate:Double, numberOfRate:Int, price:Double, year:Int) extends Rateable
  with Ordered[Wine] {
  override def compare(that: Wine) = this.rate compare that.rate
  override var rating: Double = rate
  override var numberOfRates: Int = numberOfRate


  override def rate(rate: Int): Unit = {
  }

}
