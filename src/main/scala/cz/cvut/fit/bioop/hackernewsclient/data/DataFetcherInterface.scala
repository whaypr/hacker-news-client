package cz.cvut.fit.bioop.hackernewsclient.data

import cz.cvut.fit.bioop.hackernewsclient.data.models.{Item, User}

trait DataFetcherInterface {
  protected var url_base: String
  protected def url_item: String = s"$url_base/item/"
  protected def url_user: String = s"$url_base/user/"

  def fetchNews(endpoint: String): Seq[Int]

  def getItem(id: Int): Item

  def getUser(username: String): User

  def updateUrl(url: String) = url_base = url
}
