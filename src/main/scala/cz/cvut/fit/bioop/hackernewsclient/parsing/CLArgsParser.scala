package cz.cvut.fit.bioop.hackernewsclient.parsing

import cz.cvut.fit.bioop.hackernewsclient.CommandExecution.Command._
import cz.cvut.fit.bioop.hackernewsclient.CommandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.CommandExecution._
import cz.cvut.fit.bioop.hackernewsclient.{App, Utils}

class CLArgsParser(args: Array[String]) {

  private val it = args.iterator


  def parse: Unit = {
    /* Parse app options */
    val appExecutor = new AppExecutor

    var argOpt = nextArg
    if (argOpt.isEmpty)
      appExecutor.addCommand(new AppHelpCommand) // there are no arguments

    while ( argOpt.isDefined && argOpt.get.startsWith("-")) {
      argOpt.get match {
        case "--help" | "-h" => appExecutor.addCommand(new AppHelpCommand)
        case "--version" | "-v" => appExecutor.addCommand(new AppVersionCommand)
        case "--page" | "-pg" =>
          val tmpStr = nextArg.getOrElse(throw new Exception("Page must have a number value!"))

          val tmp = Utils.toInt(tmpStr)
          appExecutor.addCommand(new AppPageCommand(
            tmp.getOrElse(throw new Exception("Page must be a number!"))
          ))
        case "--page-size" | "-ps" =>
          val tmpStr = nextArg.getOrElse(throw new Exception("Page-size must have a number value!"))

          val tmp = Utils.toInt(tmpStr)
          val value = tmp.getOrElse(throw new Exception("Page-size must be a number!"))

          appExecutor.addCommand(new AppPageSizeCommand(value))
        case "--time-to-live" | "-ttl" =>
          val tmpStr = nextArg.getOrElse(throw new Exception("TTL must have a number value!"))

          val tmp = Utils.toInt(tmpStr)
          val value = tmp.getOrElse(throw new Exception("TTL must be a number!"))

          appExecutor.addCommand(new AppTimeToLiveCommand(value))
        case _ => appExecutor.invalidOption
      }

      argOpt = nextArg
    }

    if (appExecutor.execute == RetCode.terminate)
      App.exit

    /* Parse command and its options */
    val commandArg = argOpt.getOrElse("news") // default behaviour
    commandArg match {
      case "news" => parseNews
      case "item" => parseItem
      case "user" => parseUser
      case "clear-cache" | "cc" => clearCache
      case _ => throw new Exception("Invalid command option!")
    }
  }


  /* ---------- HELPERS ---------- */

  // NEWS
  private def parseNews: Unit = {
    val newsExecutor = new NewsExecutor

    if (!it.hasNext)
      newsExecutor.addCommand(new NewsStoriesCommand("top")) // default behaviour

    while (it.hasNext) {
      var arg = nextArg.get

      arg match {
        case "--first" => newsExecutor.addCommand(new NewsFirstCommand())
        case "--stories" | "-s" =>
          arg = nextArg.getOrElse("top") // default behaviour
          arg match {
            case "top" | "new" | "best" => newsExecutor.addCommand(new NewsStoriesCommand(arg))
            case _ => throw new Exception("Stories must have one of the following values: top | new | best")
          }
        case "--asks" | "-a" => newsExecutor.addCommand(new NewsAsksCommand())
        case "--shows" | "-o" => newsExecutor.addCommand(new NewsShowsCommand())
        case "--jobs" | "-j" => newsExecutor.addCommand(new NewsJobsCommand())
        case _ => throw new Exception("Invalid news option!")
      }
    }

    newsExecutor.execute
  }


  // ITEM
  private def parseItem = {
    val itemExecutor = new ItemExecutor

    while (it.hasNext) {
      val arg = it.next()
      arg match {
        case "--id" =>
          val id = Utils.toInt(
            nextArg.getOrElse(
              throw new Exception("Id must have a number value!"))).getOrElse(
            throw new Exception("Id must be a number!"))

          itemExecutor.setId(id)
        case "--comments" | "-c" => itemExecutor.comments = true
        case _ => throw new Exception("Invalid item option!")
      }
    }

    itemExecutor.execute
  }


  // USER
  private def parseUser = {
    val userExecutor = new UserExecutor

    while (it.hasNext) {
      val arg = it.next()
      arg match {
        case "--id" =>
          val id = nextArg.getOrElse(
            throw new Exception("Id must have a string value!")
          )

          userExecutor.setId(id)
        case "--submit" => userExecutor.stories = true
        case _ => throw new Exception("Invalid user option!")
      }
    }

    userExecutor.execute
  }


  // CACHE
  private def clearCache = {
    //TODO
  }


  //
  private def nextArg: Option[String] = {
    if (it.hasNext)
      return Some(it.next)
    None
  }
}
