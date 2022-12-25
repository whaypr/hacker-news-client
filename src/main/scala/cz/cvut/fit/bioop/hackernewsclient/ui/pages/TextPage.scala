package cz.cvut.fit.bioop.hackernewsclient.ui.pages

/**
 * Page of text
 */
trait TextPage extends Page[String]
{
  /**
   * Renders a page into a string
   *  @return
   */
  def render(): String
}
