package cz.cvut.fit.bioop.hackernewsclient.ui.parsing

object HTMLParser {

  def parse(text: String): String = {
    /*
    text
      .split(s"((?<=(&[^;]+;))|(?=(&[^;]+;)))")
      .map {w => htmlCodes.getOrElse(w, w)}
      .mkString("")
     */

    var res = ""

    val it = text.iterator
    while (it.hasNext) res += (it.next match {
      case '&'   => parseCode(it)
      case '<'   => parseTag(it)
      case other => other
    })

    res
  }

  /* ---------- HELPERS ---------- */

  private def parseCode(it: Iterator[Char]): String = {
    // assumes that code is properly ended
    var code = ""

    var c = it.next()
    while (c != ';') {
      code += c
      c = it.next()
    }

    codes.getOrElse(code.toLowerCase, "")
  }

  private def parseTag(it: Iterator[Char]): String = {
    // assumes that tag is properly ended
    var tag = ""

    var c = it.next()
    while (c != '>') {
      tag += c
      c = it.next()
    }

    tags.getOrElse(tag.toLowerCase, "")
  }

  private val codes = Map(
    "#32" -> " ",
    "#x20" -> " ",
    "#33" -> "!",
    "#x21" -> "!",
    "#34" -> "\"",
    "#x22" -> "\"",
    "quot" -> "\"",
    "#35" -> "#",
    "#x23" -> "#",
    "#36" -> "$",
    "#x24" -> "$",
    "#37" -> "%",
    "#x25" -> "%",
    "#38" -> "&",
    "#x26" -> "&",
    "amp" -> "&",
    "#39" -> "'",
    "#x27" -> "'",
    "#40" -> "(",
    "#x28" -> "(",
    "#41" -> ")",
    "#x29" -> ")",
    "#42" -> "*",
    "#x2a" -> "*",
    "#43" -> "+",
    "#x2b" -> "+",
    "#44" -> ",",
    "#x2c" -> ",",
    "#45" -> "-",
    "#x2d" -> "-",
    "#46" -> ".",
    "#x2e" -> ".",
    "#47" -> "/",
    "#x2f" -> "/",
    "#58" -> ":",
    "#x3a" -> ":",
    "#59" -> ";",
    "#x3b" -> ";",
    "#60" -> "<",
    "#x3c" -> "<",
    "lt" -> "<",
    "#61" -> "=",
    "#x3d" -> "=",
    "#62" -> ">",
    "#x3e" -> ">",
    "gt" -> ">",
    "#63" -> "?",
    "#x3f" -> "?",
    "#64" -> "@",
    "#x40" -> "@",
    "#91" -> "[",
    "#x5b" -> "[",
    "#92" -> "\\",
    "#x5c" -> "\\",
    "#93" -> "]",
    "#x5d" -> "]",
    "#94" -> "^",
    "#x5e" -> "^",
    "#123" -> "{",
    "#x7b" -> "{",
    "#124" -> "|",
    "#x7c" -> "|",
    "#125" -> "}",
    "#x7d" -> "}",
    "#126" -> "~",
    "#x7e" -> "~",
    "#169" -> "©",
    "#xa9" -> "©",
    "copy" -> "©",
    "#176" -> "°",
    "#xb0" -> "°",
    "deg" -> "°"
  )

  private val ansiEND = "\u001b[0m"
  private def ansiSTART(n: Int) = s"\u001b[${n}m"
  private val tags = Map(
    "p" -> "\n",
    "/p" -> "\n",
    "i" -> ansiSTART(3),
    "em" -> ansiSTART(3),
    "/i" -> ansiEND,
    "/em" -> ansiEND,
    "b" -> ansiSTART(1),
    "strong" -> ansiSTART(1),
    "/b" -> ansiEND,
    "/strong" -> ansiEND
  )
}
