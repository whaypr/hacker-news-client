package cz.cvut.fit.bioop.hackernewsclient.data.models

import ujson.Value

import scala.collection.mutable

/**
 * User data class
 * @param id The user's unique username. Case-sensitive. Required.
 * @param created Creation date of the user, in Unix Time.
 * @param karma The user's karma.
 * @param about The user's optional self-description. HTML.
 * @param submitted List of the user's stories, polls and comments.
 */
case class User(
  id: String,
  created: Int,
  karma: Int,
  about: Option[String] = None,
  submitted: Option[Vector[Int]] = None
)


object User {

  /**
   * Builds a user from JSON
   * @param data
   * @return Built user
   */
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
