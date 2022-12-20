package cz.cvut.fit.bioop.hackernewsclient.pages

trait Page[T]
{
  /**
   * Renders the content of the page in a datatype T
   * @return
   */
  def render(): T
}
