package org.dgrald

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 8/30/16.
  */
class VersusCorrectorSpec extends Specification {

  "Should correct" >> {
    Fragment.foreach(List("versus", "vs", "vs.")) { versus =>
      versus + " to vs." ! {
        val input = s"Something with $versus in it."
        VersusCorrector.correct(input) must_== "Something with vs. in it."
      }
    }
  }
}
