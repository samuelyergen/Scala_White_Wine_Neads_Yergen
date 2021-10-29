case class Location(country:String, region:String){

    def findWineriesPerRegion(list:List[Winery]): List[Winery] ={
      val wineries = list.map(winery => if(winery.location.region.equals(this.region))winery else null).filter(_ != null)
      wineries
    }

    def findWineriesPerCountry(list:List[Winery]): List[Winery] ={
      val wineries = list.map(winery => if(winery.location.country.equals(this.country))winery else null).filter(_ != null)
      wineries
    }

    def calculateAverage(list:List[Winery]): Double ={
      val wineList = list.map(winery => winery.list)
      val rateList = wineList.map(list => list.map(w => w.rating).sum)
      val lengthSum = wineList.map(list => list.length).sum
      val sumRates = rateList.map(rate => rate).sum
      sumRates/lengthSum
    }

    def calculateNbRatings(list:List[Winery]): Int ={
      val wineList = list.map(winery => winery.list)
      val n = wineList.map(list => list.map(w => w.numberOfRates).sum)
      val number = n.map(n => n).sum
      number
    }

    def averageRatingPerRegion(list:List[Winery]): Double ={
      val wineries = findWineriesPerRegion(list)
      val av = calculateAverage(wineries)
      av
    }

    def numberOfRatingsPerRegion(list:List[Winery]): Int ={
      val wineries = findWineriesPerRegion(list)
      val nbRatings = calculateNbRatings(wineries)
      nbRatings
    }

    def numberOfRatingsPerCountry(list:List[Winery]): Int ={
      val wineries = findWineriesPerCountry(list)
      val nbRatings = calculateNbRatings(wineries)
      nbRatings
    }

    def averageRatingPerCountry(list:List[Winery]): Double ={
      val wineries = findWineriesPerCountry(list)
      val av = calculateAverage(wineries)
      av
    }



}
