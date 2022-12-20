package cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor

trait FlagHandler extends Handler[String]{
  def flagNames: Vector[String]
  protected val executor: Executor
}
