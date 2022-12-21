package cz.cvut.fit.bioop.hackernewsclient.ui.parsing

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands._
import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.commandExecution._
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.commands.{AppCommandHandler, ClearCacheCommandHandler, ItemCommandHandler, NewsCommandHandler, UserCommandHandler}
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.CommandHandler
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.errors.{AppErrorHandler, ItemErrorHandler, NewsErrorHandler, UserErrorHandler}
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.app.{HelpFlagHandler, PageFlagHandler, PageSizeFlagHandler, TimeToLiveFlagHandler, VersionFlagHandler}
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.news.{AsksFlagHandler, FirstFlagHandler, JobsFlagHandler, ShowsFlagHandler, StoriesFlagHandler}
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.item.{CommentsFlagHandler, IdFlagHandler}
import cz.cvut.fit.bioop.hackernewsclient.ui.parsing.handlers.flags.user
import cz.cvut.fit.bioop.hackernewsclient.{App, Utils}
import responsibilityChain.Handler

class CommandLineParser(args: Array[String]) {

  private val it = args.iterator

  def parse: Unit = {
    // parse app flags
    val appExecutor = new AppExecutor
    val appChain = new AppCommandHandler(appExecutor)

    appChain
      .setNext(new HelpFlagHandler)
      .setNext(new VersionFlagHandler)
      .setNext(new PageFlagHandler(it))
      .setNext(new PageSizeFlagHandler(it))
      .setNext(new TimeToLiveFlagHandler(it))
      .setNext(new AppErrorHandler)

    var arg = CommandLineParser.nextArg(it)

    if (arg.isEmpty)
      appExecutor.addCommand(new AppHelpCommand) // there are no arguments

    while (arg.isDefined && arg.get.startsWith("-")) {
      Handler.resolveAll(appChain, arg.get)
      arg = CommandLineParser.nextArg(it)
    }

    if (appChain.execute == RetCode.terminate)
      App.exit

    // parse command flags
    val commandChain: CommandHandler = arg.getOrElse("news").toLowerCase() match { // default behaviour
      // news
      case "news" | "n" =>
        val executor = new NewsExecutor
        val handler = new NewsCommandHandler(executor)

        handler
          .setNext(new FirstFlagHandler)
          .setNext(new StoriesFlagHandler(it))
          .setNext(new AsksFlagHandler(it))
          .setNext(new ShowsFlagHandler(it))
          .setNext(new JobsFlagHandler(it))
          .setNext(new NewsErrorHandler)

        if (!it.hasNext)
          executor.addCommand(new NewsStoriesCommand("top")) // default behaviour

        handler

      // item
      case "item" | "i" =>
        val executor = new ItemExecutor
        val handler = new ItemCommandHandler(executor)

        handler
          .setNext(new IdFlagHandler(it))
          .setNext(new CommentsFlagHandler)
          .setNext(new ItemErrorHandler)

        handler

      // user
      case "user" | "u" =>
        val executor = new UserExecutor
        val handler = new UserCommandHandler(executor)

        handler
          .setNext(new user.IdFlagHandler(it))
          .setNext(new user.StoriesFlagHandler)
          .setNext(new UserErrorHandler)

        handler

      // clear cache
      case "clear-cache" | "cc" =>
        null // TODO

      // error
      case _ => throw new Exception("Invalid command option!")
    }

    arg = CommandLineParser.nextArg(it)
    while (arg.isDefined) {
      Handler.resolveAll(commandChain, arg.get)
      arg = CommandLineParser.nextArg(it)
    }

    commandChain.execute
  }
}


object CommandLineParser {
  def nextArg(it: Iterator[String]): Option[String] = {
    if (it.hasNext)
      return Some(it.next)
    None
  }
}