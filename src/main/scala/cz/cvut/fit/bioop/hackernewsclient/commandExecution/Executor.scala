package cz.cvut.fit.bioop.hackernewsclient.commandExecution

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode.RetCode

trait Executor {
  def addCommand(command: Command): Unit
  def execute: RetCode
}


object Executor {
  object RetCode extends Enumeration {
    type RetCode = Value

    val terminate = Value("Terminate") // ID = 0
    val continue = Value("Continue") // ID = 1
  }
}
