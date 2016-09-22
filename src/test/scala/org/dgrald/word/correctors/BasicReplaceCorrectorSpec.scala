package org.dgrald.word.correctors

import org.dgrald.StringUtils
import org.specs2.mutable.Specification
import org.specs2.specification.core.Fragment

/**
  * Created by dylangrald on 9/19/16.
  */
class BasicReplaceCorrectorSpec extends Specification {

  val toReplace = Map("transthoracic echocardiogram" -> "TTE",
    "transthoracic echocardiography" -> "TTE",
    "left ventricular end systolic volume" -> "LVESV",
    "left ventricular end diastolic volume" -> "LVEDV",
    "approximately" -> "appx.",
    "left ventricular end systolic diameter" -> "LVESD",
    "trans-septal" -> "transseptal",
    "left ventricular assist device" -> "LVAD",
    "trans-apical" -> "transapical",
    "surgical aortic valve replacement" -> "SAVR",
    "multivessel" -> "multi-vessel",
    "perioperative" -> "peri-operative",
    "pts" -> "patients",
    "right bundle branch block" -> "RBBB",
    "left ventricular end diastolic diameter" -> "LVEDD",
    "left bundle branch block" -> "LBBB",
    "left ventricular end diastolic dimension" -> "LVEDD",
    "valve in valve" -> "valve-in-valve",
    "trans-femoral" -> "transfemoral",
    "left ventricular end systolic dimension" -> "LVESD",
    "periprocedurally" -> "peri-procedurally",
    "trans-aortic" -> "transaortic",
    "transcatheter aortic valve implantation" -> "TAVI",
    "prehospital" -> "pre-hospital",
    "left ventricular ejection fraction" -> "LVEF",
    "transcatheter aortic valve replacement" -> "TAVR",
    "left ventricular outflow tract" -> "LVOT",
    "transesophageal echocardiogram" -> "TEE",
    "transesophageal echocardiography" -> "TEE",
    "periprocedural" -> "peri-procedural",
    "follow up" -> "follow-up")

  val caseIrrelevantToReplace = Map(
    "FU" -> "follow-up",
    "x-ray" -> "X-ray",
    "Kaplan Meier" -> "Kaplan-Meier",
    "New York Heart Association" -> "NYHA"
  )

  val capitalizedToReplace = toReplace.toList.map(member => member match { case (first: String, second: String) => (StringUtils.capitalize(first), StringUtils.capitalize(second))})

  "Should replace" >> {
    Fragment.foreach(toReplace.toList) {
      case (toReplace: String, replacement: String) =>
        s"'$toReplace' with '$replacement'" ! {
          val input = s"Something with $toReplace in it."

          val output = BasicReplaceCorrector.correct(input)

          output must_== s"Something with $replacement in it."
        }
    }
  }

  "Should replace" >> {
    Fragment.foreach(toReplace.toList) {
      case (toReplace: String, replacement: String) =>
        s"'${StringUtils.capitalize(toReplace)}' with '$replacement'" ! {
          val input = s"Something with ${StringUtils.capitalize(toReplace)} in it."

          val output = BasicReplaceCorrector.correct(input)

          output must_== s"Something with ${StringUtils.capitalize(replacement)} in it."
        }
    }
  }

  "Should replace" >> {
    Fragment.foreach(caseIrrelevantToReplace.toList) {
      case (toReplace: String, replacement: String) =>
        s"'$toReplace' with '$replacement'" ! {
          val input = s"Something with $toReplace in it."

          val output = BasicReplaceCorrector.correct(input)

          output must_== s"Something with $replacement in it."
        }
    }
  }

}
