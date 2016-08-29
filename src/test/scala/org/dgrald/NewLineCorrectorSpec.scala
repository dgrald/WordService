package org.dgrald

import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 8/28/16.
  */
class NewLineCorrectorSpec extends Specification {

  "Should strip instances of multiple new line characters" >> {
    Fragment.foreach(Range(2,5)) { numberOfNewLines =>
      s"with $numberOfNewLines new lines" ! {
        val input = s"Something with multiple new lines${getNumberOfNewLines(numberOfNewLines)} in a string."

        var output = NewLineCorrector.correct(input)

        output must_== "Something with multiple new lines in a string."
      }
    }
  }

  "Should not replace instances of single new line characters" in {
    val input = "Something with one new line \ncharacter."

    val output = NewLineCorrector.correct(input)

    output must_== input
  }

  def getNumberOfNewLines(numberOfNewLines: Int): String = {
    val newLines = (for {
      _ <- Range(0, numberOfNewLines)
    } yield "\n").mkString("")
    newLines
  }
}
