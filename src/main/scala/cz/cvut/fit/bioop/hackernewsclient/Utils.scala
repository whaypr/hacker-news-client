package cz.cvut.fit.bioop.hackernewsclient

object Utils {

  /**
   * Casts a string to integer
   * @param s String to be casted
   * @return Some integer if possible, or None
   */
  def toInt(s: String): Option[Int] = {
    try {
      Some(s.toInt)
    } catch {
      case e: Exception => None
    }
  }


  /**
   * Makes string bold (in terminal)
   * @param text
   * @return
   */
  def bold(text: String): String = s"\u001b[1m${text}\u001b[0m"


  /**
   * Slices passed sequence 
   * @param data
   * @tparam T
   * @return
   */
  def getSlice[T](data: Seq[T]) = {
    val idStart = (App.page - 1) * App.pageSize
    data.slice(idStart, idStart + App.pageSize)
  }
}
