package org.dgrald

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 8/31/16.
  */
class PreCorrectorSpec extends Specification {

  val preWordsToReplace = List("existing", "procedural", "procedure", "loaded", "load", "formed", "form", "specified", "specify", "operative", "operatively", "closure", "close", "crimped", "crimp", "dilatation", "dilate", "dilated", "dilates", "dilation", "intervention", "sizing", "shaped", "determined", "assembled", "assemble", "stented", "stent", "stenting", "medicate", "medicated", "implant", "implantation")

  "Should correct" >> {
    Fragment.foreach(preWordsToReplace) { preWord =>
      s"pre$preWord to pre-$preWord"  ! {
        val input = s"Something with pre$preWord."
        PreWordCorrector.correct(input) must_== s"Something with pre-$preWord."
      }
    }
  }

  "Should correct multiple instances of the pre words that should be hyphenated" in {
    val first = preWordsToReplace.head
    val last = preWordsToReplace.last
    val input = s"Pre$first and then pre$last."

    val output = PreWordCorrector.correct(input)

    output must_== s"Pre-$first and then pre-$last."
  }
}
