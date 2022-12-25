package cz.cvut.fit.bioop.hackernewsclient.commandExecution

import cz.cvut.fit.bioop.hackernewsclient.ui.pages.TextPage

/**
 * Class representing commands and flags
 */
trait Command {

  /**
   * Executes the command
   */
  def execute: Unit

  /**
   * Renders the command's output
   * @param renderer Renderer which will be used to perform the rendering
   */
    // TODO refactor: this class is redundant and should not be here
  def render(renderer: TextPage): Unit = {
    val output = renderer.render()
    println(output)
  }
}
