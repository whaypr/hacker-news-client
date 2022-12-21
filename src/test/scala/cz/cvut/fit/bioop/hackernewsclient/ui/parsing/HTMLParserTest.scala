package cz.cvut.fit.bioop.hackernewsclient.ui.parsing

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class HTMLParserTest extends AnyFlatSpec with should.Matchers {

  "HTMLParser" should "parse text and replace HTML codes with corresponding symbols " in {
    val p1 = HTMLParser.parse("Hello &quot;Patrik&quot;, how are you &#63;")
    p1 shouldEqual "Hello \"Patrik\", how are you ?"

    val p2 = HTMLParser.parse("https:&#x2F;&#x2f;www.lorem&#47;ipsum.com&amp;foo=bar")
    p2 shouldEqual "https://www.lorem/ipsum.com&foo=bar"
  }

  "HTMLParser" should "parse text and replace HTML tags with corresponding symbols " in {
    val p1 = HTMLParser.parse("<p>Some <b>text</b></p>heh")
    p1 shouldEqual "\nSome \u001b[1mtext\u001b[0m\nheh"

    val p2 = HTMLParser.parse("12<undefined_tag> test</blah>3")
    p2 shouldEqual "12 test3"
  }
}
