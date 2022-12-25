package cz.cvut.fit.bioop.hackernewsclient.commandExecution

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode.RetCode

/**
 * Command executor
 */
trait Executor {

  /**
   * Add a command to be handled by this executor
   * @param command
   */
  def addCommand(command: Command): Unit

  /**
   * Executes commands
   * @return
   */
  def execute: RetCode
}


object Executor {

  /**
   * Return codes enumeration for executorss
   */
  object RetCode extends Enumeration {
    type RetCode = Value

    val terminate = Value("Terminate") // ID = 0
    val continue = Value("Continue") // ID = 1
  }
}
