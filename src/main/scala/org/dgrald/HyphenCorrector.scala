package org.dgrald

/**
  * Created by dylangrald on 8/30/16.
  */
object HyphenCorrector extends Corrector {
  override def correct(input: String): String = {
    val prefixesToHyphenate = List("pre", "post", "intra", "anti")

    prefixesToHyphenate.foldRight(List(input))((nextPrefix, toReturn) => {
      val nextRegex = s"$nextPrefix[a-zA-Z]+".r
      val nextOutput = nextRegex.replaceAllIn(toReturn.head, (someMatch) => {
          s"$nextPrefix-${someMatch.toString.split(nextPrefix).last}"
      })
      nextOutput +: toReturn
    }).head
  }
}
