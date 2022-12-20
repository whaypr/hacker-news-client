package cz.cvut.fit.bioop.hackernewsclient.pages.concrete

import cz.cvut.fit.bioop.hackernewsclient.pages.TextPage

class VersionPage extends TextPage
{
  override def render(): String = {
    val version = "1.0"
    version
  }
}
