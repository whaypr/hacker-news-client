package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode

trait CommandHandler extends Handler[String]{
  def execute: Executor.RetCode.RetCode = { RetCode.continue } //executor.execute

  def commandName: String
  //protected var executor: Executor
}
