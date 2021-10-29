

case class Winery(name:String, location:Location, var list:List[Wine] = null){

    //method used to build the winery's wine list
    def sortList(list:List[Wine]): List[Wine] ={
      val newList = list.map(wine => if(wine.wineryName.equals(this.name))wine else null).filter(_ != null)
      newList
    }

    //calculate the average rating
    def averageWinesRate(): Double ={
      val newList = this.list.map(x => (x.rating,1))
      val sum = newList.reduce((a,b) => ( a._1 + b._1,
        a._2 + b._2 ))
      sum._1/sum._2
    }

    //total of number of ratings for all wines of a winery
    def totalRates(): Int ={
      val sum = this.list.map(wine => wine.numberOfRates).sum
      sum
    }

    //calculate the percentage of expensive wines (+30.-) produced by the winery
    def percentageOfExpensiveWine(): Double ={
     val nbExpensiveWine = this.list.map(wine => wine.nbExpensiveWine(wine)).sum
      nbExpensiveWine*100/this.list.length
    }

    def findBestRatedWine(): Wine ={
      val sortList = this.list.sortBy(_.rate)(Ordering[Double].reverse)
      sortList.head
    }

    def findWorstRatedWine(): Wine ={
      val sortList = this.list.sortBy(_.rate)
      sortList.head
    }

    def findBestRatedWinePerYear(year:Int) ={
      val l = this.list.filter(wine => wine.year.equals(year))
      if(!l.isEmpty){
        val sortList = l.sortBy(_.rate)(Ordering[Double].reverse)
        sortList.head
      }else{
        throw new Exception("no wine for this year")
      }
    }

    def findWorstRatedWinePerYear(year:Int)={
      val l = this.list.filter(wine => wine.year.equals(year))
      if(!l.isEmpty){
        val sortList = l.sortBy(_.rate)
        sortList.head
      }else{
        throw new Exception("no wine for this year")
      }
    }

    def addNewWine(wine:Wine): Unit ={
      val newList = wine :: this.list
      newList
      this.list = newList
    }

    def sellWine(win:Wine, number:Int): Unit ={
      /*val newList = this.list.map(wine => if(wine.name.equals(win.name))
        if(wine.stock < number)wine.stock = 0
        else wine.stock = wine.stock-number)*/

      val newList = this.list.map(wine => {
        if (wine.name.equals(win.name)) {
          if(wine.stock < number)
          wine.stock = 0
          else
            wine.stock = wine.stock-number
        }
        wine
      })
      this.list = newList
    }

    def restock(win:Wine, number:Int): Unit ={
      val newList = this.list.map(wine => {
        if (wine.name.equals(win.name)) {
          wine.stock = wine.stock + number
        }
         wine
      })
      //newList
      this.list = newList
    }

    def removeWine(win:Wine): Unit ={
      val newList = this.list.filter(wine => !wine.equals(win))
      //newList
      this.list = newList
    }

    def editWineStock(win:Wine, number:Int): Unit ={
      val newList = this.list.map(wine => {
        if (wine.name.equals(win.name)) {
          wine.stock = number
        }
        wine
      })
      //newList
      this.list = newList
    }

    def getLargest[Double](rating: List[Double])(implicit ev$1: Double => Ordered[Double]): (Double, List[Double]) =
    rating match {
      case head :: Nil  => (head, Nil)
      case head :: tail =>
        val (large, remaining) = getLargest(tail)
        if (large > head)
          (large, head :: remaining)
        else
          (head, large :: remaining)
    }

  //recursive method to sort a list of double
    def bubbleSort[Double](rating: List[Double])(implicit ev$1: Double => Ordered[Double]): List[Double] = {
      rating match {
        case Nil => Nil
        case _   =>
          val (greatest, tail) = getLargest(rating)
          bubbleSort(tail) ::: List(greatest)
      }
    }

  //use the bubble sort algorithm to sort a list by the wine's rating
  //then return the best rating
    def findBestRating(): Double ={
      val ratingsList = this.list.map(wine => wine.rating)
      val sortedList = bubbleSort(ratingsList)
      sortedList.last
    }

}
