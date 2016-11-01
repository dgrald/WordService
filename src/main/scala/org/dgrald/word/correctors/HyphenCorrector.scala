package org.dgrald.word.correctors

import org.dgrald.StringUtils

/**
  * Created by dylangrald on 8/30/16.
  */
object HyphenCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any] = List()): String = {
    val prefixesToHyphenate = List("post", "intra", "anti")

    val exceptions = List("posterior[a-z]*", "anticipat[a-z]*\\b")

    prefixesToHyphenate.foldRight(List(input))((nextPrefix, toReturn) => {
      val prefixRegex = s"[${nextPrefix.head}${nextPrefix.head.toUpper}]${nextPrefix.tail}"
      val nextRegex = s"\\b$prefixRegex[a-zA-Z]+".r
      val nextOutput = nextRegex.replaceAllIn(toReturn.head, (someMatch) => {
        val prefix = if(StringUtils.isCapitalized(someMatch.toString)) StringUtils.capitalize(nextPrefix) else nextPrefix
        if(exceptions.exists(ex => someMatch.toString().matches(ex))) {
          someMatch.toString()
        } else {
          s"$prefix-${someMatch.toString.split(prefixRegex).last}"
        }
      })
      nextOutput +: toReturn
    }).head
  }
}
