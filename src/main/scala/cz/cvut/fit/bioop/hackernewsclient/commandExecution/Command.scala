package cz.cvut.fit.bioop.hackernewsclient.commandExecution

import cz.cvut.fit.bioop.hackernewsclient.ui.pages.TextPage

trait Command {
  def execute: Unit

  def render(renderer: TextPage): Unit = {
    val output = renderer.render()
    println(output)
  }
}
