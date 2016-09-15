package org.dgrald.word.correctors

/**
  * Created by dylangrald on 8/28/16.
  */
trait Corrector {
  def correct(input: String): String
}

object WordServiceCorrector {
  def correct(input: String, newLines: Boolean): String = {
    val correctors: List[Corrector] = getCorrectors(newLines)

    correctors.foldRight(List(input))((corrector, resultList) => {
      corrector.correct(resultList.head) +: resultList
    }).head
  }

  def getCorrectors(newLines: Boolean): List[Corrector] = {
    val beforeNewLines = List(PreWordCorrector, FollowUpCorrector, HyphenCorrector, VersusCorrector, ComparisonSymbolCorrector, MultipleSpaceCorrector, RemoveNewLineAndTabCorrector)
    val afterNewLines = List(WordToOrdinalCorrector, WordToNumberCorrector, PlusAndMinusCorrector, PercentageSpaceCorrector, MonthAbbreviationCorrector)
    if (newLines) {
      afterNewLines ++ List(NewLineCorrector) ++ beforeNewLines
    } else {
      afterNewLines ++ beforeNewLines
    }
  }
}
