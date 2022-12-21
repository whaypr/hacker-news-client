package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.caching.Cacher
import ui.parsing.CommandLineParser

object Main {
  def main(args: Array[String]): Unit = {
    val parser = new CommandLineParser(args);

    try {
      parser.parse
    } catch {
      case e: Exception => println("\nException in parser:\n" + e + "\n")
    }
  }
}
