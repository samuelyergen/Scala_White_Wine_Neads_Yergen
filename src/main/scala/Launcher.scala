import com.github.tototoshi.csv.CSVReader

import java.io.File

object Launcher {
  def main(args: Array[String]): Unit = {

    //reader for CSV files
    val reader = CSVReader.open(new File("C:\\ProjetTest\\WhiteWine - Copie.csv"))

    //make a list with the csv data and drop the first line which is header line
    val firstlist = reader.toStream.toList.drop(1)

    reader.close()

    //use the list from CSV file and make a list of all wines and a list of all wineries
    val winesList = firstlist.map( w => Wine(w(0), Location(w(1),w(2)), w(3), w(4).toDouble,w(5).toInt,
      w(6).toDouble, if(w(7).equals("N.V."))0 else w(7).toInt, (Math.random()*100).toInt, if(w(6).toDouble > 30.0)true else false))
    val wineriesList = firstlist.map(w => Winery(w(3),Location(w(1),w(2)))).distinct

    //create list of wines for each winery
    wineriesList.foreach(winery => winery.list = winery.sortList(winesList))

    //display all wineries of a specific region
    val terreDiChieti = Location("Italy", "Terre di Chieti")
    val terreDiChietiWineries = terreDiChieti.findWineriesPerRegion(wineriesList)
    println("wineries of region " + terreDiChieti.region + " : ")
    terreDiChietiWineries.foreach(winery => println(winery.name))
    println(" ")

    //print the list of wines of a specific winery (Krämer - Straight)
    val kramer = wineriesList.find(winery => winery.name.equals("Krämer - Straight")).get
    println("list for Krämer - Straight : " + kramer.list)


    //average rating for all wines of Krämer winery
    val average = kramer.averageWinesRate()
    println("average rating for all wines from winery " + kramer.name + " : " +average)


    //total number of ratings for all wines of Krämer winery
    val totnb = kramer.totalRates()
    println("total number of rating for winery " + kramer.name + " : " + totnb)

    //find the best rating attributed to a wine of Krämer winery
    val bestRating = kramer.findBestRating()
    println("best rating attributed to a wine from " + kramer.name + " : " + bestRating)

    println(" ")

    //average rating of wines per region
    val avTerreDiChieti = terreDiChieti.averageRatingPerRegion(wineriesList)
    //number of ratings per region
    val nbRatingsTerreDiChieti = terreDiChieti.numberOfRatingsPerRegion(wineriesList)
    println("average rating for all wine from region " + terreDiChieti.region + " : " +avTerreDiChieti)
    println("total number of ratings for region " + terreDiChieti.region + " : " +nbRatingsTerreDiChieti)
    println(" ")

    //average rating of wines per country
    val avItaly = terreDiChieti.averageRatingPerCountry(wineriesList)
    //number of votes per country
    val nbRatingsItaly = terreDiChieti.numberOfRatingsPerCountry(wineriesList)
    println("average rating for " + terreDiChieti.country + " : " + avItaly)
    println("total number of ratings for " + terreDiChieti.country + " : " + nbRatingsItaly)
    println(" ")

    //find best and worst wine of Farnese winery
    val farnese = wineriesList.find(winery => winery.name.equals("Farnese")).get
    val bestWine = farnese.findBestRatedWine()
    val worstWine = farnese.findWorstRatedWine()
    println("best rated wine of " + farnese.name + " winery : " +bestWine)
    println("worst rated wine of " + farnese.name + " winery : " +worstWine)
    println(" ")

    //best and worst wine for year 2018 for Farnese Winery
    val bestWineFor2018 = farnese.findBestRatedWinePerYear(2018)
    val worstWineFor2018 = farnese.findWorstRatedWinePerYear(2018)
    println("best wine for year 2018 : " + bestWineFor2018)
    println("worst wine for year 2018 : " +worstWineFor2018)
    println(" ")

    //create new wine and add it to the list of Farnese Winery
    val newWine = Wine("test",Location("test","test"),"Farnese",4.1,41,41.0,2019, 50, true)
    farnese.addNewWine(newWine)
    println("new list for Farnese : ")
    println(farnese.list)
    println(" ")

    //sell 3 bottles of the best 2018 wine of Farnese winery
    println("stock before selling : " + bestWineFor2018.stock)
    farnese.sellWine(bestWineFor2018, 5)
    println("stock after selling : " + bestWineFor2018.stock)
    println(" ")

    //restock of 3 bottles of the best 2018 wine of Farnese winery
    println("stock before restock : " + bestWineFor2018.stock)
    farnese.restock(bestWineFor2018, 100)
    println("stock after restock : " + bestWineFor2018.stock)
    println(" ")

    //edit wine stock
    println("stock before editing : " + bestWineFor2018.stock)
    farnese.editWineStock(bestWineFor2018, 300)
    println("stock after editing : " + bestWineFor2018.stock)
    println(" ")

    //remove a wine from the list
    farnese.removeWine(bestWineFor2018)
    println("new farnese list after removing a wine : ")
    println(farnese.list)
    println(" ")

    //get the percentage of expensive wine to sell at Farnese
    val percentage = farnese.percentageOfExpensiveWine()
    println("percentage of expensive wine for " + farnese.name + " : " + percentage + "%")





  }
}
