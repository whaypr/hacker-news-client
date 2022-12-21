package cz.cvut.fit.bioop.hackernewsclient.commandExecution.executors

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.app.{AppHelpCommand, AppPageCommand, AppPageSizeCommand, AppTimeToLiveCommand, AppVersionCommand}
import org.scalatest.funsuite.AnyFunSuite

class AppExecutorTest extends AnyFunSuite {

  class TestAppExecutor extends AppExecutor {
    def numberOfCommands: Int = {
      var res = otherCommands.length

      if (helpCommand.isDefined)
        res += 1
      if (versionCommand.isDefined)
        res += 1

      res
    }
  }

  var executor = new TestAppExecutor
  executor.addCommand(new AppVersionCommand)
  executor.addCommand(new AppPageSizeCommand(5))
  executor.addCommand(new AppHelpCommand)
  executor.addCommand(new AppTimeToLiveCommand(10))

  var executor2 = new TestAppExecutor
  executor2.addCommand(new AppVersionCommand)

  var executor3 = new TestAppExecutor
  executor3.addCommand(new AppVersionCommand)
  executor3.addCommand(new AppPageCommand(3))

  val executor4 = new TestAppExecutor
  executor4.invalidOption
  val error = intercept[Exception] {
    executor4.execute()
  }

  test("number of commands") {
    assert(executor.numberOfCommands == 4)
  }

  test("execution return code with help and version and other flags") {
    assert(executor.execute() == RetCode.terminate)
  }

  test("execution return code with only version flag") {
    assert(executor2.execute() == RetCode.terminate)
  }

  test("execution return code with version flag and other flags") {
    assert(executor3.execute() == RetCode.terminate)
  }

  test("execution with invalid option") {
   assert(error.getMessage == "Invalid app option!")
  }
}
