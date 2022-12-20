package cz.cvut.fit.bioop.hackernewsclient.data

import cz.cvut.fit.bioop.hackernewsclient.data.models.{Item, User}
import upickle.default.read

import scala.io.Source
import scala.util.Using

object DataFetcher {
  private val url_base: String = "https://hacker-news.firebaseio.com/v0/"
  private val url_item: String = s"$url_base/item/"
  private val url_user: String = s"$url_base/user/"

  def fetchNews(endpoint: String): Seq[Int] = {
    val json = Using(Source.fromURL(s"$url_base/$endpoint.json")) { source =>
      source.mkString
    }

    try read[Seq[Int]](json.get)
    catch {
      case _ => Seq(read[Int](json.get))
    }
  }

  def getItem(id: Int): Item = {
    val json = Using(Source.fromURL(s"$url_item/$id.json")) { source =>
      source.mkString
    }
    //read[Item](json.get)
    Item.buildFromJson(ujson.read(json.get))
  }

  def getUser(username: String): User = {
    val json = Using(Source.fromURL(s"$url_user/$username.json")) { source =>
      source.mkString
    }
    User.buildFromJson(ujson.read(json.get))
  }
}
