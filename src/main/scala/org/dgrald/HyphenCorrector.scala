package org.dgrald

/**
  * Created by dylangrald on 8/30/16.
  */
object HyphenCorrector extends Corrector {
  override def correct(input: String): String = {
    val prefixesToHyphenate = List("pre", "post", "intra", "anti")
    val exceptions = List("previous", "presentation", "presenter", "presented", "present", "preclinical", "press", "pregnant", "prevent", "prevented", "prevents")

    prefixesToHyphenate.foldRight(List(input))((nextPrefix, toReturn) => {
      val prefixRegex = s"[${nextPrefix.head}${nextPrefix.head.toUpper}]${nextPrefix.tail}"
      val nextRegex = s"$prefixRegex[a-zA-Z]+".r
      val nextOutput = nextRegex.replaceAllIn(toReturn.head, (someMatch) => {
        if(exceptions.contains(someMatch.toString().toLowerCase)) {
          someMatch.toString
        } else {
          val prefix = if(StringUtils.isCapitalized(someMatch.toString)) StringUtils.capitalize(nextPrefix) else nextPrefix
          s"$prefix-${someMatch.toString.split(prefixRegex).last}"
        }
      })
      nextOutput +: toReturn
    }).head
  }
}
