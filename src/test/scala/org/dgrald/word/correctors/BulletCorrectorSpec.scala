package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/18/16.
  */
class BulletCorrectorSpec extends Specification {

  val bulletUnicode = "\u2022"

  "Should correct" >> {
    Fragment.foreach(List(
      (s"Something line one: $bulletUnicode  \t  something else", "Something line one: \nsomething else"),
      (s"Something line one: ${bulletUnicode}something else", "Something line one: \nsomething else"))) {
      case (input: String, expected: String) => s"'$input' to '$expected'" ! {
        val output = BulletCorrector.correct(input)

        output must_== expected
      }
    }
  }
}
