package cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app._
import org.scalatest.funsuite.AnyFunSuite

class UserExecutorTest extends AnyFunSuite {

  var executor = new UserExecutor
  executor.addCommand(new AppHelpCommand)
  val error = intercept[Exception] {
    executor.execute
  }

  var executor2 = new UserExecutor
  executor2.setId("pepe")
  executor2.stories = true

  test("id is not set") {
    assert(error.getMessage == "User id must be set!")
  }

  test("executed successfully") {
    assert(executor2.execute == RetCode.terminate)
  }
}
