package cz.cvut.fit.bioop.hackernewsclient.ui.parsing

import org.scalatest.funsuite.AnyFunSuite

class CommandLineParserTest extends AnyFunSuite {
  val args = Array("-pg", "2", "-ps", "3", "-ttl", "2", "news", "--stories", "best")
  val parser = new CommandLineParser(args)

  val error2 = intercept[Exception] {
    new CommandLineParser(Array("user", "-huh")).parse
  }

  val error3 = intercept[Exception] {
    new CommandLineParser(Array("item", "--gimme")).parse
  }

  val error4 = intercept[Exception] {
    new CommandLineParser(Array("--helloThere")).parse
  }

  val error5 = intercept[Exception] {
    new CommandLineParser(Array("news", "--stories", "pokemon")).parse
  }

  val error6 = intercept[Exception] {
    new CommandLineParser(Array("item", "--id", "RRRRRRRRRrrrrrrrrrr")).parse
  }

  val error7 = intercept[Exception] {
    new CommandLineParser(Array("user", "--id")).parse
  }

  val error8 = intercept[Exception] {
    new CommandLineParser(Array("news", "-a", "-o")).parse
  }


  test("parse finish correctly") {
    parser.parse
  }

  test ("invalid syntax") {
    assert(error2.getMessage == "Invalid user option!")
    assert(error3.getMessage == "Invalid item option!")
    assert(error4.getMessage == "Invalid app option!")
    assert(error5.getMessage == "Stories must have one of the following values: top | new | best")
    assert(error6.getMessage == "Id must be a number!")
    assert(error7.getMessage == "Id must have a value!")
    assert(error8.getMessage == "Command option already set!")
  }
}
