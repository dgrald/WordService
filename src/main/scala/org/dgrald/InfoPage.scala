package org.dgrald

/**
  * Created by dylangrald on 9/22/16.
  */
object InfoPage {
  def info = {
    <div>This tool has the following optional corrections:</div>
      <ul>
        <li>Remove all line breaks</li>
        <li>Add an asterisk before each line</li>
        <li>Split input into sentences and place each onto its own line</li>
        <ul>
          <li>Removes the period at the end of the sentence</li>
          <li>Uses periods and bullet points to distinguish sentences</li>
          <li>Splitting by periods takes into account other uses of periods, such as abbreviations, but doesn’t capture all cases where a period is followed by a capital letter but is not the end of a sentence</li>
        </ul>
        <li>Allows the user to replace any string with another string</li>
      </ul>
      <div>And performs the following corrections:</div>
      <ul>
        <li>Removes spaces between an equality comparison (e.g. “&gt; 2 mm” becomes “&gt;2 mm”)</li>
        <li>Replaces “versus” with “vs.”</li>
        <li>Removes duplicate spaces</li>
        <li>Replaces decimals commas to decimals points</li>
        <ul>
          <li>Not perfect: 1,324 will not be changed to 1.324</li>
        </ul>
        <li>Removes plus or minus values (e.g. “2 +/- 5 mm” becomes “2 mm”)</li>
        <li>Removes spaces between number and percentage sign</li>
        <li>For numbers 0-10, replaces the spelled out word with the digits</li>
        <li>For ordinal numbers 1-10, replaces the spelled out word with the shortened ordinal representation (e.g. “first row” becomes “1st row”; however, “first 1 mm” remains unchanged)</li>
        <li>Formats some dates (e.g. “January” becomes “Jan.”; also “9/2010” becomes “Sept. 2010”)</li>
        <li>For all words beginning in “post”, “intra”, or “anti”, it adds a hyphen after the prefix (e.g. “postprocedural” becomes “post-procedural”)</li>
        <li>For some words beginning in “pre”, it adds a hyphen after “pre”</li>
        <ul>
          <li>pre-existing, pre-procedural, pre-procedure, pre-loaded, pre-load, pre-formed, pre-form, pre-specified, pre-specify, pre-operative, pre-operatively, pre-closure, pre-close, pre-crimped, pre-crimp, pre-dilatation, pre-dilate, pre-dilated, pre-dilates, pre-dilation, pre-intervention, pre-sizing, pre-shaped, pre-determined, pre-assembled, pre-assemble, pre-stented, pre-stent, pre-stenting, pre-medicate, pre-medicated, pre-implant, pre-implantation</li>
        </ul>
        <li>Removes bullet points</li>
        <li>Makes these corrections:</li>
        <ul>
          <li>&quot;left ventricular end diastolic volume&quot; becomes &quot;LVEDV&quot;</li>
          <li>&quot;left ventricular outflow tract&quot; becomes &quot;LVOT&quot;</li>
          <li>&quot;perioperative&quot; becomes &quot;peri-operative&quot;</li>
          <li>&quot;trans-apical&quot; becomes &quot;transapical&quot;</li>
          <li>&quot;left ventricular end diastolic diameter&quot; becomes &quot;LVEDD&quot;</li>
          <li>&quot;prehospital&quot; becomes &quot;pre-hospital&quot;</li>
          <li>&quot;left ventricular assist device&quot; becomes &quot;LVAD&quot;</li>
          <li>&quot;surgical aortic valve replacement&quot; becomes &quot;SAVR&quot;</li>
          <li>&quot;transthoracic echocardiography&quot; becomes &quot;TTE&quot;</li>
          <li>&quot;transesophageal echocardiogram&quot; becomes &quot;TEE&quot;</li>
          <li>&quot;transcatheter aortic valve implantation&quot; becomes &quot;TAVI&quot;</li>
          <li>&quot;valve in valve&quot; becomes &quot;valve-in-valve&quot;</li>
          <li>&quot;transcatheter aortic valve replacement&quot; becomes &quot;TAVR&quot;</li>
          <li>&quot;left ventricular ejection fraction&quot; becomes &quot;LVEF&quot;</li>
          <li>&quot;trans-femoral&quot; becomes &quot;transfemoral&quot;</li>
          <li>&quot;periprocedurally&quot; becomes &quot;peri-procedurally&quot;</li>
          <li>&quot;approximately&quot; becomes &quot;appx.&quot;</li>
          <li>&quot;pts&quot; becomes &quot;patients&quot;</li>
          <li>&quot;periprocedural&quot; becomes &quot;peri-procedural&quot;</li>
          <li>&quot;right bundle branch block&quot; becomes &quot;RBBB&quot;</li>
          <li>&quot;left ventricular end diastolic dimension&quot; becomes &quot;LVEDD&quot;</li>
          <li>&quot;left ventricular end systolic volume&quot; becomes &quot;LVESV&quot;</li>
          <li>&quot;transesophageal echocardiography&quot; becomes &quot;TEE&quot;</li>
          <li>&quot;transthoracic echocardiogram&quot; becomes &quot;TTE&quot;</li>
          <li>&quot;trans-septal&quot; becomes &quot;transseptal&quot;</li>
          <li>&quot;left ventricular end systolic dimension&quot; becomes &quot;LVESD&quot;</li>
          <li>&quot;multivessel&quot; becomes &quot;multi-vessel&quot;</li>
          <li>&quot;trans-aortic&quot; becomes &quot;transaortic&quot;</li>
          <li>&quot;left ventricular end systolic diameter&quot; becomes &quot;LVESD&quot;</li>
          <li>&quot;follow up&quot; becomes &quot;follow-up&quot;</li>
          <li>&quot;left bundle branch block&quot; becomes &quot;LBBB&quot;</li>
        </ul>
      </ul>
  }
}
