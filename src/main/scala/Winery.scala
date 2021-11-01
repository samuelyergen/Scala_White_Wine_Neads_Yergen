

case class Winery(name:String, location:Location,  list:List[Wine] = Nil){ // Nil = liste vide

    //method used to build the winery's wine list
    def sortList(): List[Wine] ={
      //val newList = list.map(wine => if(wine.wineryName.equals(this.name))wine else null).filter(_ != null)
      list.filter(wine => wine.wineryName.equals(this.name))
    }

    //calculate the average rating
    def averageWinesRate(): Double ={
      val wineRatingList = this.list.map(wine => wine.rating)
      //val sum = newList.reduce((a,b) => ( a._1 + b._1, a._2 + b._2 ))
      val sum = wineRatingList.reduceLeft((a,b) => a+b)
      sum/list.length
    }

    //total of number of ratings for all wines of a winery
    def totalRates(): Int ={
      //list.foldLeft(0)(_+_.numberOfRates) //Shorter version of line 22
      list.foldLeft(0)((sum, wine) => sum + wine.numberOfRates)
      //val sum = this.list.map(wine => wine.numberOfRates).sum
    }

    //calculate the percentage of expensive wines (+30.-) produced by the winery
    def percentageOfExpensiveWine(): Double ={
     val listOfExpensiveWine = list.filter(wine => wine.price >= 30.0 )
     listOfExpensiveWine.length*100/this.list.length
    }

    private def sortByRating(wineList: List[Wine]): List[Wine] ={
      wineList.sortBy(_.rate)
    }

    def findBestRatedWine(): Wine ={
      val sortList = sortByRating(list)//(Ordering[Double].reverse)
      sortList.last

      //sortByRating(list).last
    }

    def findWorstRatedWine(): Wine ={
      //val sortList = this.list.sortBy(_.rate)
      val sortList = sortByRating(list)
      sortList.head

      //sortByRating(list).head
    }

  //TODO Voir pour sortir la fonction filter by year

    def findBestRatedWinePerYear(year:Int):Wine ={
      val wineListByYear = this.list.filter(wine => wine.year == year)
      wineListByYear match {
        case _ if wineListByYear.nonEmpty => sortByRating(wineListByYear).last
        // case _  => throw new Exception (s"no wine found for year $year")
        case _  => throw new Exception ("no wine found for year " + year)
      }
    }

    def findWorstRatedWinePerYear(year:Int):Wine={
      val wineListByYear = this.list.filter(wine => wine.year == year)

      wineListByYear match {
        case _ if wineListByYear.nonEmpty => sortByRating(wineListByYear).head
        // case _  => throw new Exception (s"no wine found for year $year")
        case _  => throw new Exception ("no wine found for year " + year)
      }
    }

    def addNewWine(wine:Wine): Winery ={
      Winery(name,location, wine :: this.list)
    }

    def sellWine(wineSold:Wine, number:Int): Winery ={
      val newList = this.list.map(wine => {
        wine.name match {
          case wineSold.name => wineSold.sellWine(number)
          case _ => wine
        }
      })
      Winery(name, location,newList)
    }

    def restock(wineRestock:Wine, number:Int): Winery ={
      val newList = this.list.map(wine => {
        wine.name match {
          case wineRestock.name => wineRestock.addWineStock(number)
          case _ => wine
        }
      })
      Winery(name, location,newList)
    }

    def removeWine(removedWine:Wine): Winery ={
      val newList = this.list.filter(wine => wine != removedWine)
      Winery(name,location,newList)
    }

    def editWineStock(editWine:Wine, number:Int): Winery ={
      val newList = this.list.map(wine => {
        if (wine.name.equals(editWine.name)) {
          if(editWine.stock > number){
            editWine.sellWine(editWine.stock-number)
          } else {
            editWine.addWineStock(number-editWine.stock)
          }
        } else {
          wine
        }
      })
      Winery(name,location,newList)
    }

    def getLargest[Double](rating: List[Double])(implicit ev$1: Double => Ordered[Double]): (Double, List[Double]) = rating match {
      case head :: Nil  => (head, Nil)
      case head :: tail =>
        val (large, remaining) = getLargest(tail)
        if (large > head)
          (large, head :: remaining)
        else
          (head, large :: remaining)
    }

  //recursive method to sort a list of double
    def bubbleSort[Double](rating: List[Double])(implicit ev$1: Double => Ordered[Double]): List[Double] = { rating match {
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
