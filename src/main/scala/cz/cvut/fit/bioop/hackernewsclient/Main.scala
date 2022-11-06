package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.CLArgsParser.CLArgsParser
import cz.cvut.fit.bioop.hackernewsclient.DataFetcher.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.UI.Displayer

object Main {
  def main(args: Array[String]): Unit = {
    val parser = new CLArgsParser(args);

    try {
      parser.parse
    } catch {
      case e: Exception => println("\nException in parser:\n" + e + "\n")
    }

    if (App.displayer.isDefined)
      App.displayer.get.display
  }
}
