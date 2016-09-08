package org.dgrald

/**
  * Created by dylangrald on 9/8/16.
  */
object FollowUpCorrector extends Corrector {
  override def correct(input: String): String = {
    val regex = "[Ff]ollow up".r
    regex.replaceAllIn(input, (toReplace) => {
      if(StringUtils.isCapitalized(toReplace.toString())) "Follow-up" else "follow-up"
    })
  }
}
