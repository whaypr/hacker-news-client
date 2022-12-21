package cz.cvut.fit.bioop.hackernewsclient.data

import cz.cvut.fit.bioop.hackernewsclient.data.models.{Item, User}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class DataFetcherTest extends AnyFlatSpec with should.Matchers {
  val df = new DataFetcher()

  "DataFetcher" should "fetch an item" in {
    val story = df.getItem(3)
    val comment = df.getItem(7833)

    story.id shouldEqual 3
    story.`type` shouldEqual Some("story")
    story.by shouldEqual Some("phyllis")
    story.time shouldEqual Some(1160419233)

    comment.id shouldEqual 7833
    comment.`type` shouldEqual Some("comment")
    comment.by shouldEqual Some("pg")
    comment.time shouldEqual Some(1175359749)

    val error = intercept[Exception] {
      df.getItem(-1)
    }
    error.getMessage shouldEqual "Item with this id does not exist!"
  }

  "DataFetcher" should "fetch an user" in {
    val user1 = df.getUser("pivo")

    user1.id shouldEqual "pivo"
    user1.created shouldEqual 1196083612

    val error = intercept[Exception] {
      df.getUser("PatrikDrbal")
    }
    error.getMessage shouldEqual "User with this username does not exist!"
  }
}
