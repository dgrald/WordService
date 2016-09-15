package org.dgrald.word.correctors

import org.dgrald.StringUtils

/**
  * Created by dylangrald on 8/31/16.
  */
object PreWordCorrector extends Corrector {
  override def correct(input: String): String = {
    val preWordsToReplace = List("existing", "procedural", "procedure", "loaded", "load", "formed", "form", "specified", "specify", "operative", "operatively", "closure", "close", "crimped", "crimp", "dilatation", "dilate", "dilated", "dilates", "dilation", "intervention", "sizing", "shaped", "determined", "assembled", "assemble", "stented", "stent", "stenting", "medicate", "medicated", "implant", "implantation")

    preWordsToReplace.foldRight(List(input))((nextPreWord, listOfOutputs) => {
      val regex = s"[Pp]re$nextPreWord".r
      regex.replaceAllIn(listOfOutputs.head, (regexMatch) => {
        if(StringUtils.isCapitalized(regexMatch.toString())) {
          s"Pre-$nextPreWord"
        } else {
          s"pre-$nextPreWord"
        }
      }) +: listOfOutputs
    }).head
  }
}
