import com.github.tototoshi.csv.CSVReader

import java.io.File

object Launcher {
  def main(args: Array[String]): Unit = {

    //reader for CSV files
    val reader = CSVReader.open(new File("C:\\ProjetTest\\WhiteWine - Copie.csv"))

    //make a list with the csv data and drop the first line which is header line
    val firstlist = reader.toStream.toList.drop(1)

    //use the list from CSV file and make a list of all wines and a list of all wineries
    val winesList = firstlist.map( w => Wine(w(0), Location(w(1),w(2)), w(3), w(4).toDouble,w(5).toInt,
      w(6).toDouble, if(w(7).equals("N.V."))0 else w(7).toInt))
    /*val winesList = reader.toStream.toList.map( w => Wine(w(0), Location(w(1),w(2)), w(3), w(4).toDouble,w(5).toInt,
      w(6).toDouble, if(w(7).equals("N.V."))0 else w(7).toInt))*/

    val wineriesList = firstlist.map(w => Winery(w(3),Location(w(1),w(2)))).distinct
    //val wineriesList = reader2.toStream.toList.map(w => Winery(w(3),Location(w(1),w(2)))).distinct
    reader.close()

    //create list of wines for each winery and set it as attributes
    wineriesList.foreach(winery => winery.sortList(winesList))

    //print the list of wines of a specific winery
    var specificWinery = wineriesList.find(winery => winery.name.equals("Krämer - Straight"))
    //specificWinery is an Option, it works like list, we need to use foreach or map or flatmap...
    //we cannot do specificWinery.list
    specificWinery.foreach(winery => println(winery.list))
    println(" ")

    //test display all wineries of a specific region
    wineriesList.foreach(winery => if(winery.location.region == "Terre di Chieti")println(winery))
    println(" ")

    //test average rate for all wines of a specific winery
    val average = wineriesList(3).averageWinesRate()
    println("average rate for all wines from winery " + wineriesList(3).name + " : " +average)

    //create a location
    val loc = Location("Italy","Terre di Chieti")
    //Test the average rate of wines per region
    val averageRheinhessen = loc.averageRatePerRegion(wineriesList)
    //Test number of rate per region
    val numberOfRate = loc.numberOfRatePerRegion(wineriesList)
    println(" ")
    println("average rate for all wine from region " + loc.region + " : " +averageRheinhessen)
    println("total number of rate for region " + loc.region + " : " +numberOfRate)
    println(" ")

    //Test the average rate of wines per region
    val famigliaCastellani = wineriesList.find(winery => winery.name.equals("Farnese"))
    val av = famigliaCastellani.map(winery => winery.location.averageRatePerRegion(wineriesList))
    println(av)

    //test find best and worst wine of Farnese winery
    val bestWine = famigliaCastellani.map(winery => winery.findBestRatedWine())
    val worstWine = famigliaCastellani.map(winery => winery.findWorstRatedWine())
    println(bestWine)
    println(worstWine)
    println(" ")

    //test create new wine and add it to the list of Farnses Winery
    val newWine = Wine("test",Location("test","test"),"Farnese",4.2,25,20.3,2019)
    famigliaCastellani.foreach(winery => winery.addNewWine(newWine))
    famigliaCastellani.foreach(winery => println(winery.list))
    println(" ")

    //test best and worst wine per year for Farnese Winery
    val bestWineFor2018 = famigliaCastellani.map(winery => winery.findBestRatedWinePerYear(2018))
    val worstWineFor2018 = famigliaCastellani.map(winery => winery.findWorstRatedWinePerYear(2018))
    println(bestWineFor2018)
    println(worstWineFor2018)


  }
}
