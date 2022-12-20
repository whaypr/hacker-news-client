package cz.cvut.fit.bioop.hackernewsclient.caching

import java.nio.file.{Files, Path, Paths}

class Cacher {
  
  val path: Path = Paths.get("./.hnc_cache")
  if (!Files.exists(path))
    Files.createDirectory(path)
}
