package cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete

import cz.cvut.fit.bioop.hackernewsclient.Utils
import cz.cvut.fit.bioop.hackernewsclient.data.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.data.models.Item
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.TextPage
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.HTMLParser

class ItemPage(id: Int, comments: Boolean) extends TextPage {
  override def render(): String = {
    val item = DataFetcher.getItem(id)

    if (!comments)
      return itemPage(item)

    if (item.kids.isDefined) {
      var res = ""

      Utils.getSlice(item.kids.get).foreach(x =>
        res += itemPage(DataFetcher.getItem(x)) + '\n'
      )

      res
    } else
      "~NO COMMENTS~\n"
  }


  protected def itemPage(item: Item): String = {
    item.`type`.getOrElse("") match {
      case "story" => storyPage(item)
      case "comment" => commentPage(item)
      case _ => item.toString
    }
  }


  protected def storyPage(story: Item): String = {
    var res = ""

    res += "----------- STORY -----------\n"

    val title = Utils.bold(story.title.getOrElse("~UNKNOWN~"))
    val url = {
      if (story.url.isDefined)
        s"(${story.url.get})"
      else
        ""
    }
    val score = {
      if (story.score.isDefined)
        story.score.get + " score"
      else
        "~UNKNOWN~"
    }
    val author = story.by.getOrElse("~UNKNOWN~")
    val comments = story.kids.getOrElse(Seq()).length

    res += s"$title $url\n"
    res += s"  $score by $author | $comments comments\n"

    res
  }


  protected def commentPage(comment: Item): String = {
    var res = ""

    res+= "----------- COMMENT -----------\n"
    res+= Utils.bold("Replying to:\n")
    res+= "\t" + comment.id + "\n"
    res+= Utils.bold("Content:\n")
    res+= "\t" + HTMLParser.parse(comment.text.getOrElse("")) + "\n"

    res
  }
}
