case class Location(country:String, region:String){

    def averageRatePerRegion(list:List[Winery]): Double ={
        var sum = 0.0
        var i = 0
        list.foreach(winery => if(winery.location.region == this.region){
            winery.list.foreach(wine => sum = sum + wine.rate)
            winery.list.foreach(wine => i = i + 1)
        })
        val average = sum / i
        average
    }

    def numberOfRatePerRegion(list:List[Winery]): Int ={
        var sum = 0
        val summm = list.map(winery => if(winery.location.region == this.region)
          winery.list.map(wine => wine.numberOfRate).sum
        )
       list.foreach(winery => if(winery.location.region == this.region)sum=sum+
            winery.list.map(wine => wine.numberOfRate).sum
        )
        sum
    }
}
