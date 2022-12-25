package cz.cvut.fit.bioop.hackernewsclient.ui.pages

/**
 * Page which can be rendered somewhere
 * @tparam T
 */
trait Page[T]
{
  /**
   * Renders the content of the page in a datatype T
   * @return
   */
  def render(): T
}
