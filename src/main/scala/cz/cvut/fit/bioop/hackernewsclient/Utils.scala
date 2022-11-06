package cz.cvut.fit.bioop.hackernewsclient

object Utils {

  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }
}
