package com.briank.test.snippet

import xml.{Text, Group, NodeSeq}
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.SetHtml
import net.liftweb.http.{SessionVar, RequestVar, SHtml}

class AjaxButton {
  def render(xhtml: Group): NodeSeq = {
    var value = ""
    object rValue extends RequestVar[String]("")

    def doSubmit(): JsCmd = {
      SetHtml("response",
        <span><strong>The var says you typed:</strong> {value}</span><br/>
        <span><strong>The RequestVar says you typed:</strong> {rValue.is}</span>)
    }

    SHtml.ajaxForm(
      bind("field", xhtml,
         "text" -%> SHtml.text(value, v => { println("value = " + v); rValue(v); value = v }),
         "submit" -> SHtml.submit("Submit", doSubmit _)
        ) ++ SHtml.hidden(doSubmit _)
    )
  }
}
