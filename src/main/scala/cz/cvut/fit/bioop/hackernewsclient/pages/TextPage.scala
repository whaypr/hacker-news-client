package cz.cvut.fit.bioop.hackernewsclient.pages

trait TextPage extends Page[String]
{
  /**
   * Renders a page into a string
   *  @return
   */
  def render(): String
}
