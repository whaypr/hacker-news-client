package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor
import responsibilityChain.Handler

trait CommandHandler extends Handler[String]{
  override def handle(item: String): Option[Handler[String]] = nextHandler

  // TODO refactor
  def setNext(nextHandler: FlagHandler): FlagHandler = {
    this.nextHandler = Some(nextHandler)
    nextHandler.executor =  Some(executor)
    nextHandler
  }

  // TODO refactor
  def setNext(nextHandler: ErrorHandler): ErrorHandler = {
    nextHandler.executor = Some(executor)
    this.nextHandler = Some(nextHandler)
    nextHandler
  }

  def execute: Executor.RetCode.RetCode = executor.execute

  val executor: Executor
}
