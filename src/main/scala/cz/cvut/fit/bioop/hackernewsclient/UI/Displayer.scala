package cz.cvut.fit.bioop.hackernewsclient.UI

import cz.cvut.fit.bioop.hackernewsclient.App
import cz.cvut.fit.bioop.hackernewsclient.DataFetcher.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.models.{Item, User}

trait Displayer {
  def display: Unit
}


// NEWS
class NewsDisplayer extends Displayer {
  var ids: Seq[Int] = Seq()

  override def display = {

    val items: Seq[Item] = Displayer.getSlice(ids)
      .map(DataFetcher.getItem)

    items.foreach(Displayer.printItem)
  }
}


// ITEM
class ItemDisplayer extends Displayer {
  var id = 0
  var showComments = false

  override def display: Unit = {
    val item = DataFetcher.getItem(id)

    if (!showComments) {
      Displayer.printItem(item)
      return
    }

    //item.kids.get.map(DataFetcher.getItem).foreach(Displayer.printItem)
    if (item.kids.isDefined)
      Displayer.getSlice( item
        .kids
        .get )
        .foreach(x => Displayer.printItem(DataFetcher.getItem(x)))
    else
      println("~NO COMMENTS~")
  }
}


// USER
class UserDisplayer extends Displayer {
  var username = ""
  var showStories = false

  override def display: Unit = {
    val user = DataFetcher.getUser(username)

    if (!showStories) {
      Displayer.printUser(user)
      return
    }

    Displayer.getSlice(user.submitted.get).foreach(x => Displayer.printItem(DataFetcher.getItem(x)))
  }
}


/* HELPERS */

object Displayer {
  private def bold(text: String): String = s"\u001b[1m${text}\u001b[0m"

  def printItem(item: Item) = {
    item.`type`.getOrElse("") match {
      case "story" => printStory(item)
      case "comment" => printComment(item)
      case _ => println(item)
    }
  }

  def printUser(user: User) = {
    println("----------- USER -----------")
    val id = bold(user.id)
    val about = HTMLParser.parse(user.about.getOrElse("~NO DESCRIPTION~"))
    val created = user.created
    val karma = user.karma
    val submitted = user.submitted.getOrElse(Seq()).length

    println(s"$id ($karma)")
    println(s"  created at $created | $submitted posts")
    println(s"$about")
  }

  private def printStory(item: Item) = {
    println("----------- STORY -----------")
    val title = bold(item.title.getOrElse("~UNKNOWN~"))
    val url = {
      if (item.url.isDefined)
        s"(${item.url.get})"
      else
        ""
    }
    val score = {
      if (item.score.isDefined)
        item.score.get + " score"
      else
        "~UNKNOWN~"
    }
    val author = item.by.getOrElse("~UNKNOWN~")
    val comments = item.kids.getOrElse(Seq()).length

    println(s"$title $url")
    println(s"  $score by $author | $comments comments")
  }

  private def printComment(item: Item) = {
    println("----------- COMMENT -----------")
    println(bold("Replying to: "))
    println("\t" + item.id)
    println(bold("Content: "))
    println("\t" + HTMLParser.parse(item.text.getOrElse("")))
  }

  def getSlice[T](data: Seq[T]) = {
    val idStart = (App.page - 1) * App.pageSize
    data.slice(idStart, idStart + App.pageSize)
  }
}
