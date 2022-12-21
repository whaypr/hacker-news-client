package cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app._
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.item.ItemCommand
import org.scalatest.funsuite.AnyFunSuite

class ItemExecutorTest extends AnyFunSuite {

  var executor = new ItemExecutor
  executor.addCommand(new AppHelpCommand)
  val error = intercept[Exception] {
    executor.execute
  }

  var executor2 = new ItemExecutor
  executor2.setId(4)
  executor2.comments = true

  test("id is not set") {
    assert(error.getMessage == "Item id must be set!")
  }

  test("executed successfully") {
    assert(executor2.execute == RetCode.terminate)
  }
}
