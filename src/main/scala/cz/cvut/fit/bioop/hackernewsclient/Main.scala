package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.Cacher.Cacher
import cz.cvut.fit.bioop.hackernewsclient.DataFetcher.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.UI.Displayer
import cz.cvut.fit.bioop.hackernewsclient.parsing.CLArgsParser

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

    new Cacher()
  }
}
