package cz.cvut.fit.bioop.hackernewsclient.pages.concrete

import cz.cvut.fit.bioop.hackernewsclient.DataFetcher.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.UI.HTMLParser
import cz.cvut.fit.bioop.hackernewsclient.Utils
import cz.cvut.fit.bioop.hackernewsclient.models.User
import cz.cvut.fit.bioop.hackernewsclient.pages.TextPage

class UserPage(username: String, stories: Boolean) extends TextPage
{
  override def render(): String = {
    val user = DataFetcher.getUser(username)

    if (!stories)
      return userPage(user)

    var res = ""

    Utils.getSlice(user.submitted.get).foreach(x =>
      res += new ItemPage(x, false) + "\n"
    )

    res
  }


  protected def userPage(user: User): String = {
    var res = ""

    res += "----------- USER -----------\n"
    val id = Utils.bold(user.id)
    val about = HTMLParser.parse(user.about.getOrElse("~NO DESCRIPTION~\n"))
    val created = user.created
    val karma = user.karma
    val submitted = user.submitted.getOrElse(Seq()).length

    res += s"$id ($karma)\n"
    res += s"  created at $created | $submitted posts\n"
    res += s"$about\n"

    res
  }
}
