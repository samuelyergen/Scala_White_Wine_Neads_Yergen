case class Winery(name:String, location:Location, var list:List[Wine] = null){


  def sortList(list:List[Wine]): Unit ={
    val l = list.map(wine => if(wine.wineryName.equals(this.name))wine else null).filter(_ != null)
    this.list = l
  }

  def averageWinesRate(): Double ={
    val li = this.list.map(wine => wine.rate).sum
    li/list.length
  }

  /*def rateAWine(rate:Double, wineName:String): Double ={
    var sum = 0.0
    val wine = this.list.find(wine => wine.name.equals(wineName))
    val lr = wine.map(wine => sum = wine.rate + rate)
    sum/2
  }*/

  def findBestRatedWine(): Wine ={
    val sortList = this.list.sortBy(_.rate)(Ordering[Double].reverse)
    sortList(0)
  }

  def findWorstRatedWine(): Wine ={
    val sortList = this.list.sortBy(_.rate)
    sortList(0)
  }

  def addNewWine(wine:Wine): Unit ={
    val newList = wine :: this.list
    this.list = newList
  }

}
