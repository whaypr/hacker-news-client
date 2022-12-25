package cz.cvut.fit.bioop.hackernewsclient.commandExecution.commands.clearCache

import cz.cvut.fit.bioop.hackernewsclient.commandExecution.Command

import java.io.IOException
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, Files, Path, Paths, SimpleFileVisitor}

/**
 * Class representing the clear cache command
 */
class ClearCacheCommand extends Command {
  override def execute: Unit = {
    val path = Paths.get("./.hnc_cache")
    delete(path)
  }


  /**
   *
   * @param root
   */
  // from https://www.baeldung.com/scala/delete-directories-recursively
  private def delete(root: Path): Unit = {
    Files.walkFileTree(
      root,
      new SimpleFileVisitor[Path] {
        override def visitFile(
                                file: Path,
                                attrs: BasicFileAttributes
                              ): FileVisitResult = {
          Files.delete(file)
          FileVisitResult.CONTINUE
        }

        override def postVisitDirectory(
                                         dir: Path,
                                         exc: IOException
                                       ): FileVisitResult = {
          Files.delete(dir)
          FileVisitResult.CONTINUE
        }
      }
    )
  }

}
