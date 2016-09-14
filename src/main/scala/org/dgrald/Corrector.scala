package org.dgrald

/**
  * Created by dylangrald on 8/28/16.
  */
trait Corrector {
  def correct(input: String): String
}

object WordServiceCorrector {
  def correct(input: String): String = {
    val correctors = List(PercentageSpaceCorrector, MonthAbbreviationCorrector, NewLineCorrector, PreWordCorrector, FollowUpCorrector, HyphenCorrector, VersusCorrector, ComparisonSymbolCorrector, MultipleSpaceCorrector, RemoveNewLineAndTabCorrector)
    correctors.foldRight(List(input))((corrector, resultList) => {
      corrector.correct(resultList.head) +: resultList
    }).head
  }
}
