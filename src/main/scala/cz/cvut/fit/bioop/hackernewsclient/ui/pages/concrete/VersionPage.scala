package cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete

import cz.cvut.fit.bioop.hackernewsclient.ui.pages.TextPage

/**
 * Page with the application version infos
 */
class VersionPage extends TextPage
{
  override def render(): String = {
    val version = "1.0"
    version
  }
}
