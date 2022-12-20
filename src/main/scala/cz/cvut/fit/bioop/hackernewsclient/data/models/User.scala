package cz.cvut.fit.bioop.hackernewsclient.data.models

import ujson.Value

import scala.collection.mutable

case class User(
  id: String,
  created: Int,
  karma: Int,
  about: Option[String] = None,
  submitted: Option[Vector[Int]] = None
)

object User {
  def buildFromJson(data: Value.Value): User = {
    var user: User = new User(
      data("id").toString.stripPrefix("\"").stripSuffix("\""),
      data("created").toString.toInt,
      data("karma").toString.toInt
    )

    val keys: collection.Set[String] = data
      .value
      .asInstanceOf[mutable.LinkedHashMap[String,Any]].keySet

    for (k <- keys) { k match {
      case "about" => user = user.copy(about =
        Some(data(k).toString.stripPrefix("\"").stripSuffix("\""))
      )
      case "submitted" => user = user.copy(submitted =
        Some(data(k).arr.map(_.num.toInt).toVector)
      )
      case _ =>
    }}

    user
  }
}
