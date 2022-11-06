package cz.cvut.fit.bioop.hackernewsclient.CLArgsParser

import cz.cvut.fit.bioop.hackernewsclient.{App, Utils}
import cz.cvut.fit.bioop.hackernewsclient.CommandExecution.{AppExecutor, ItemExecutor, NewsExecutor, RetCode, UserExecutor}
import cz.cvut.fit.bioop.hackernewsclient.CommandExecution.Command._
import cz.cvut.fit.bioop.hackernewsclient.UI.{ItemDisplayer, NewsDisplayer, UserDisplayer}

import scala.util.control.Breaks.break

class CLArgsParser(args: Array[String]) {

  private val it = args.iterator


  def parse: Unit = {
    var arg = nextArg.getOrElse({
      (new AppHelpCommand).execute // there are no arguments
      App.exit
    })

    /* Parse app options */
    val appExecutor = new AppExecutor

    while (arg.startsWith("-")) {
      arg match {
        case "--help" | "-h" => appExecutor.addCommand(new AppHelpCommand)
        case "--version" | "-v" => appExecutor.addCommand(new AppVersionCommand)
        case "--page" | "-pg" =>
          arg = nextArg.getOrElse(throw new Exception("Page must have a number value!"))

          val tmp = Utils.toInt(arg)
          appExecutor.addCommand(AppPageCommand(
            tmp.getOrElse(throw new Exception("Page must be a number!"))
          ))
        case "--page-size" | "-ps" =>
          arg = nextArg.getOrElse(throw new Exception("Page-size must have a number value!"))

          val tmp = Utils.toInt(arg)
          val value = tmp.getOrElse(throw new Exception("Page-size must be a number!"))

          appExecutor.addCommand(AppPageSizeCommand(value))
        case _ => appExecutor.invalidOption
      }

      arg = nextArg.getOrElse({
        appExecutor.execute
        App.exit
      })
    }

    if (appExecutor.execute == RetCode.terminate)
      App.exit

    /* Parse command and its options */
    val commandArg = arg
    commandArg match {
      case "news" => parseNews
      case "item" => parseItem
      case "user" => parseUser
      case _ => throw new Exception("Invalid command option!")
    }
  }


  /* ---------- HELPERS ---------- */

  // NEWS
  private def parseNews: Unit = {
    val newsExecutor = new NewsExecutor
    val newsDisplayer = new NewsDisplayer

    if (!it.hasNext)
      newsExecutor.addCommand(NewsStoriesCommand(newsDisplayer,"top")) // default behaviour

    while (it.hasNext) {
      var arg = nextArg.get

      arg match {
        case "--first" => newsExecutor.addCommand(new NewsFirstCommand(newsDisplayer))
        case "--stories" | "-s" =>
          arg = nextArg.getOrElse({
            NewsStoriesCommand(newsDisplayer,"top").execute // default behaviour
            App.displayer = Some(newsDisplayer)
            return
          })
          arg match {
            case "top" | "new" | "best" => newsExecutor.addCommand(NewsStoriesCommand(newsDisplayer,arg))
            case _ => throw new Exception("Stories must have one of the following values: top | new | best")
          }
        case "--asks" | "-a" => newsExecutor.addCommand(new NewsAsksCommand(newsDisplayer))
        case "--shows" | "-o" => newsExecutor.addCommand(new NewsShowsCommand(newsDisplayer))
        case "--jobs" | "-j" => newsExecutor.addCommand(new NewsJobsCommand(newsDisplayer))
        case _ => throw new Exception("Invalid news option!")
      }
    }

    newsExecutor.execute
    App.displayer = Some(newsDisplayer)
  }


  // ITEM
  private def parseItem = {
    val itemExecutor = new ItemExecutor
    val itemDisplayer = new ItemDisplayer

    while (it.hasNext) {
      val arg = it.next()
      arg match {
        case "--id" =>
          itemExecutor.addCommand(ItemIdCommand(
            itemDisplayer,
            Utils.toInt(
              nextArg.getOrElse(
                throw new Exception("Id must have a number value!"))).getOrElse(
              throw new Exception("Id must be a number!"))))
        case "--comments" | "-c" => itemExecutor.addCommand(new ItemCommentsCommand(itemDisplayer))
        case _ => throw new Exception("Invalid item option!")
      }
    }

    itemExecutor.execute
    App.displayer = Some(itemDisplayer)
  }


  // USER
  private def parseUser = {
    val userExecutor = new UserExecutor
    val userDisplayer = new UserDisplayer

    while (it.hasNext) {
      val arg = it.next()
      arg match {
        case "--id" =>
          userExecutor.addCommand(UserIdCommand(
            userDisplayer,
            nextArg.getOrElse(
              throw new Exception("Id must have a string value!"))))
        case "--submit" => userExecutor.addCommand(new UserStoriesCommand(userDisplayer))
        case _ => throw new Exception("Invalid user option!")
      }
    }

    userExecutor.execute
    App.displayer = Some(userDisplayer)
  }


  //
  private def nextArg: Option[String] = {
    if (it.hasNext)
      return Some(it.next)
    None
  }
}