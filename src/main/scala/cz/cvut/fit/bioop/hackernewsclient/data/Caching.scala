package cz.cvut.fit.bioop.hackernewsclient.data

import cz.cvut.fit.bioop.hackernewsclient.App
import cz.cvut.fit.bioop.hackernewsclient.data.models.{Item, User}

import java.nio.file.{Files, Path, Paths}
import java.nio.file.Files

/**
 * Extends DataFetcher with the caching functionality
 */
trait Caching extends DataFetcher {

  override def fetchNews(endpoint: String) = {

    super.fetchNews(endpoint)
  }


  override def getItem(id: Int): Item = {
    implicit val itemRW = upickle.default.macroRW[Item]

    val path = Paths.get(s"$pathName/items/$id")
    val timestamp = System.currentTimeMillis() / 1000

    // item is cached and it is not too old yet --> use this one
    if (Files.exists(path)) {
      val fileContent = Files.readAllLines(path)

      if (timestamp - fileContent.get(0).toLong < App.ttl)
        return upickle.default.read[Item](fileContent.get(1))
    }

    // item is not cached or is cached for too long --> get the new one and cache it
    createDirectories
    val item = super.getItem(id)
    Files.writeString(path, s"$timestamp\n${upickle.default.write(item)}")

    item
  }


  override def getUser(username: String): User = {
    implicit val userRW = upickle.default.macroRW[User]

    val path = Paths.get(s"$pathName/users/$username")
    val timestamp = System.currentTimeMillis() / 1000

    // item is cached and it is not too old yet --> use this one
    if (Files.exists(path)) {
      val fileContent = Files.readAllLines(path)

      if (timestamp - fileContent.get(0).toLong < App.ttl)
        return upickle.default.read[User](fileContent.get(1))
    }

    // item is not cached or is cached for too long --> get the new one and cache it
    createDirectories
    val user = super.getUser(username)
    Files.writeString(path, s"$timestamp\n${upickle.default.write(user)}")

    user
  }


  private val pathName = "./.hnc_cache"

  /**
   * Creates a directory structure where all cached items will be stored
   */
  private def createDirectories: Unit = {
    Vector("", "items", "users").foreach{ x =>
      val path = Paths.get(s"$pathName/$x")
      if (!Files.exists(path))
        Files.createDirectory(path)
    }
  }
}
