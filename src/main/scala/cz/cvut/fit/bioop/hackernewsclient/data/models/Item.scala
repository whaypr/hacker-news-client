package cz.cvut.fit.bioop.hackernewsclient.data.models

import ujson.Value

import scala.collection.mutable

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
