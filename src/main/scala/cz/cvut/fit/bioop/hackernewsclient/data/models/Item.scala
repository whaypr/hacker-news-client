package cz.cvut.fit.bioop.hackernewsclient.data.models

import ujson.Value

import scala.collection.mutable

/**
 * Item data class
 * @param id The item's unique id.
 * @param deleted true if the item is deleted.
 * @param `type` The type of item. One of "job", "story", "comment", "poll", or "pollopt".
 * @param by The username of the item's author.
 * @param time Creation date of the item, in Unix Time.
 * @param text The comment, story or poll text. HTML.
 * @param dead true if the item is dead.
 * @param parent The comment's parent: either another comment or the relevant story.
 * @param poll The pollopt's associated poll.
 * @param kids The ids of the item's comments, in ranked display order.
 * @param url The URL of the story.
 * @param score The story's score, or the votes for a pollopt.
 * @param title The title of the story, poll or job. HTML.
 * @param parts A list of related pollopts, in display order.
 * @param descendants In the case of stories or polls, the total comment count.
 */
case class Item(
  id: Int,
  deleted: Option[Boolean] = None,
  `type`: Option[String] = None,
  by: Option[String] = None,
  time: Option[Int] = None,
  text: Option[String] = None,
  dead: Option[Boolean] = None,
  parent: Option[Int] = None,
  poll: Option[Int] = None,
  kids: Option[Vector[Int]] = None,
  url: Option[String] = None,
  score: Option[Int] = None,
  title: Option[String] = None,
  parts: Option[Vector[Int]] = None,
  descendants: Option[Int] = None
)


object Item {
  //implicit val rw: RW[Item] = macroRW
  //implicit def autoOpt[T](x: T): Option[T] = Option(x) // https://stackoverflow.com/questions/27590756/scala-option-implicit-conversion-bad-practice-or-missing-feature

  /**
   * Builds an item from JSON
   * @param data
   * @return Built item
   */
  def buildFromJson(data: Value.Value): Item = {
    var item: Item = new Item(data("id").toString.toInt)

    val keys: collection.Set[String] = data
      .value
      .asInstanceOf[mutable.LinkedHashMap[String,Any]]
      .keySet

    //keys.foreach( _ match {
    for (k <- keys) { k match {
      case "deleted" => item = item.copy(deleted =
        Some(data(k).bool)
      )
      case "type" => item = item.copy(`type` =
        Some(data(k).toString.stripPrefix("\"").stripSuffix("\""))
      )
      case "by" => item = item.copy(by =
        Some(data(k).toString.stripPrefix("\"").stripSuffix("\""))
      )
      case "time" => item = item.copy(time =
        Some(data(k).toString.toInt)
      )
      case "text" => item = item.copy(text =
        Some(data(k).toString.stripPrefix("\"").stripSuffix("\""))
      )
      case "dead" => item = item.copy(dead =
        Some(data(k).bool)
      )
      case "parent" => item = item.copy(parent =
        Some(data(k).toString.toInt)
      )
      case "poll" => item = item.copy(poll =
        Some(data(k).toString.toInt)
      )
      case "kids" => item = item.copy(kids =
        Some(data(k).arr.map(_.num.toInt).toVector)
      )
      case "url" => item = item.copy(url =
        Some(data(k).toString.stripPrefix("\"").stripSuffix("\""))
      )
      case "score" => item = item.copy(score =
        Some(data(k).toString.toInt)
      )
      case "title" => item = item.copy(title =
        Some(data(k).toString.stripPrefix("\"").stripSuffix("\""))
      )
      case "parts" => item = item.copy(parts =
        Some(data(k).arr.map(_.num.toInt).toVector)
      )
      case "descendants" => item = item.copy(descendants =
        Some(data(k).toString.toInt)
      )
      case _ =>
    }}

    item
  }
}
