package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.AppHelpCommand
import responsibilityChain.Handler

trait FlagHandler extends Handler[String]{
  override def handle(flag: String): Option[Handler[String]] = {
    if (!flagNames.contains(flag))
      return nextHandler // another handle's work --> pass to the next
    None
  }

  // TODO refactor
  def setNext(nextHandler: FlagHandler): FlagHandler = {
    this.nextHandler = Some(nextHandler)
    nextHandler.executor = executor
    nextHandler
  }

  // TODO refactor
  def setNext(nextHandler: ErrorHandler): ErrorHandler = {
    nextHandler.executor = executor
    this.nextHandler = Some(nextHandler)
    nextHandler
  }

  def flagNames: Vector[String]

  var executor: Option[Executor] = None
}
