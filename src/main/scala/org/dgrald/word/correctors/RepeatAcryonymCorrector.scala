package org.dgrald.word.correctors

/**
  * Created by dylangrald on 10/30/16.
  */
object RepeatAcryonymCorrector extends Corrector {
  override def correct(input: String, otherInstructions: List[Any]): String = {
    val regex = "[\\S]* \\(\\S+\\)".r
    regex.replaceAllIn(input, (regexMatch) => {
      val split = regexMatch.toString().split(" ")
      if(split.head.equals(split.last.replaceAll("\\(|\\)", ""))) {
        split.head
      } else {
        regexMatch.toString()
      }
    })
  }
}
