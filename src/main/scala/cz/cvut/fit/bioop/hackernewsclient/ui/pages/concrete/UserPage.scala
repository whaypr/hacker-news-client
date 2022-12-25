package cz.cvut.fit.bioop.hackernewsclient.ui.pages.concrete

import cz.cvut.fit.bioop.hackernewsclient.Utils
import cz.cvut.fit.bioop.hackernewsclient.data.DataFetcher
import cz.cvut.fit.bioop.hackernewsclient.data.models.User
import cz.cvut.fit.bioop.hackernewsclient.ui.pages.TextPage
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.HTMLParser

/**
 * Page with an user info
 * @param id Id of the user
 * @param stories Should stories be rendered or not
 */
class UserPage(username: String, stories: Boolean) extends TextPage
{
  override def render(): String = {
    val user = DataFetcher.get.getUser(username)

    if (!stories)
      return userPage(user)

    var res = ""

    Utils.getSlice(user.submitted.get).foreach(x =>
      res += new ItemPage(x, false).render + "\n"
    )

    res
  }


  /**
   * Renders an user
   * @param user
   * @return
   */
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
