package org.dgrald.word.correctors

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/21/16.
  */
class CaseInsensitiveCorrectorSpec extends Specification {

  "Should replace" >> {
    Fragment.foreach(List("EUROSCORE", "euroScore", "EuroScore")) { toReplace =>
      s"'$toReplace' with 'EuroSCORE'" ! {
        val input = s"Something with $toReplace in it."

        val output = CaseInsensitiveCorrector.correct(input)

        output must_== "Something with EuroSCORE in it."
      }
    }
  }

}
