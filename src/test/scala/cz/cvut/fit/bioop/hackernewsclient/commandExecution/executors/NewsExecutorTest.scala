package cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app._
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.news.{NewsAsksCommand, NewsFirstCommand, NewsJobsCommand}
import org.scalatest.funsuite.AnyFunSuite

class NewsExecutorTest extends AnyFunSuite {

  var executor = new NewsExecutor
  executor.addCommand(new NewsAsksCommand)
  val error = intercept[Exception] {
    executor.addCommand(new NewsFirstCommand)
  }

  var executor2 = new NewsExecutor
  executor2.addCommand(new NewsJobsCommand)

  test("set multiple flags") {
    assert(error.getMessage == "Command option already set!")
  }

  test("executed successfully") {
    assert(executor2.execute == RetCode.terminate)
  }
}
