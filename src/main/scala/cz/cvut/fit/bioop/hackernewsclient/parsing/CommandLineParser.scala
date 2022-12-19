package cz.cvut.fit.bioop.hackernewsclient.parsing

import cz.cvut.fit.bioop.hackernewsclient.CommandExecution.Command._
import cz.cvut.fit.bioop.hackernewsclient.CommandExecution.Executor.RetCode
import cz.cvut.fit.bioop.hackernewsclient.CommandExecution._
import cz.cvut.fit.bioop.hackernewsclient.UI.{ItemDisplayer, NewsDisplayer, UserDisplayer}
import cz.cvut.fit.bioop.hackernewsclient.{App, Utils}
import cz.cvut.fit.bioop.hackernewsclient.parsing.handlers.CommandHandler
import cz.cvut.fit.bioop.hackernewsclient.parsing.handlers.commands.app.{AppCommandHandler, HelpFlagHandler, PageFlagHandler, PageSizeFlagHandler, TimeToLiveFlagHandler, VersionFlagHandler}
import cz.cvut.fit.bioop.hackernewsclient.parsing.handlers.commands.clear_cache.ClearCacheCommandHandler
import cz.cvut.fit.bioop.hackernewsclient.parsing.handlers.commands.item.ItemCommandHandler
import cz.cvut.fit.bioop.hackernewsclient.parsing.handlers.commands.news.{FirstFlagHandler, NewsCommandHandler}
import cz.cvut.fit.bioop.hackernewsclient.parsing.handlers.commands.user.UserCommandHandler
import responsibilityChain.Handler

class CommandLineParser(args: Array[String]) {
/*
  def parse: Unit = {
    // parse app flags
    val appExecutor = new AppExecutor
    val appChain = new AppCommandHandler(appExecutor)
    appChain.setNext(new HelpFlagHandler(appExecutor))
      .setNext(new VersionFlagHandler(appExecutor))
      .setNext(new PageFlagHandler(appExecutor, it))
      .setNext(new PageSizeFlagHandler(appExecutor, it))
      .setNext(new TimeToLiveFlagHandler(appExecutor, it))

    var arg = CommandLineParser.nextArg(it)
    while (arg.nonEmpty && arg.get.startsWith("-")) {
      Handler.resolveAll(appChain, arg)
      arg = CommandLineParser.nextArg(it)
    }

    if (appChain.execute == RetCode.terminate || arg.isEmpty)
      App.exit

    // parse command flags
    val commandChain: CommandHandler = arg.get.toLowerCase() match {
      case        "news" ||  "n" =>
        val newsExecutor = new NewsExecutor
        val newsDisplayer = new NewsDisplayer
        val handler = new NewsCommandHandler(newsExecutor)

        handler
          .setNext(new FirstFlagHandler(newsExecutor, newsDisplayer))

        if (!it.hasNext) // default behaviour
          newsExecutor.addCommand(NewsStoriesCommand(newsDisplayer,"top"))

        App.displayer = Some(newsDisplayer)
        handler
      case        "item" ||  "i" => new ItemCommandHandler()
      case        "user" ||  "u" => new UserCommandHandler()
      case "clear-cache" || "cc" => new ClearCacheCommandHandler()
      case _ => throw new Exception("Invalid command option!")
    }

    arg = CommandLineParser.nextArg(it)
    while (arg.nonEmpty) {
      Handler.resolveAll(commandChain, arg)
      arg = CommandLineParser.nextArg(it)
    }
    commandChain.execute
  }


  private val it = args.iterator

 */
}


object CommandLineParser {
  def nextArg(it: Iterator[String]): Option[String] = {
    if (it.hasNext)
      return Some(it.next)
    None
  }
}