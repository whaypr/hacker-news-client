package cz.cvut.fit.bioop.hackernewsclient

object Utils {

  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }


  def bold(text: String): String = s"\u001b[1m${text}\u001b[0m"


  def getSlice[T](data: Seq[T]) = {
    val idStart = (App.page - 1) * App.pageSize
    data.slice(idStart, idStart + App.pageSize)
  }
}
