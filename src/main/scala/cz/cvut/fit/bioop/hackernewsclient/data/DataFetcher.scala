package cz.cvut.fit.bioop.hackernewsclient.data

import cz.cvut.fit.bioop.hackernewsclient.data.models.{Item, User}
import upickle.default.read

import scala.io.Source
import scala.util.Using

/**
 * Class used for getting information from the Hacker News API
 * @param url_base API url
 */
class DataFetcher(protected var url_base: String = "https://hacker-news.firebaseio.com/v0/") extends DataFetcherInterface {

  /**
   * Fetches news from some of the endpoints
   * @param endpoint Endpoint from which it fetches the data
   * @return Sequence of integer IDs of the fetched items
   */
  def fetchNews(endpoint: String): Seq[Int] = {
    val json = Using(Source.fromURL(s"$url_base/$endpoint.json")) { source =>
      source.mkString
    }

    try read[Seq[Int]](json.get) // TODO refactor
    catch {
      case _: Throwable => Seq(read[Int](json.get))
    }
  }


  /**
   * Fetches an item
   * @param id Id of the item to fetch
   * @return Fetched item
   */
  def getItem(id: Int): Item = {
    val json = Using(Source.fromURL(s"$url_item/$id.json")) { source =>
      source.mkString
    }

    if (json.get == "null")
      throw new Exception("Item with this id does not exist!")

    //read[Item](json.get)
    Item.buildFromJson(ujson.read(json.get))
  }


  /**
   * Fetches an user
   * @param username Username of the user to fetch
   * @return Fetched user
   */
  def getUser(username: String): User = {
    val json = Using(Source.fromURL(s"$url_user/$username.json")) { source =>
      source.mkString
    }

    if (json.get == "null")
      throw new Exception("User with this username does not exist!")

    User.buildFromJson(ujson.read(json.get))
  }
}


/**
 * A companion object used for getting the DataFetcher instance with Caching mixin
 */
object DataFetcher {
  private val dataFetcher: DataFetcher = new DataFetcher with Caching

  def get: DataFetcher = dataFetcher
}


























