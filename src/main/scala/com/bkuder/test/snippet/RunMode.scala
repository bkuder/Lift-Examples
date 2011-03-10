package com.briank.test.snippet

import xml.{Group, NodeSeq}
import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmd
import net.liftweb.http.{RequestVar, SHtml}
import net.liftweb.http.js.JsCmds.{CmdPair, SetHtml}
import net.liftweb.http.js.jquery.JqJsCmds
import net.liftweb.http.js.JE.{JsRaw, JsFunc}

class RunMode {
  def render(xhtml: Group): NodeSeq = {
    object colors extends RequestVar[List[String]](Nil)
    def appendColor(c: String) = colors(c :: colors.is)

    def doSubmit(): JsCmd = {
      println("do submit!")
      SetHtml("response",
        <span><strong>Your rainbow has some pretty colors:</strong> {colors.is.reverse.mkString(", ")}</span>)
    }

    def renderColor =
      <div class="color">
        {SHtml.text("", c => { println("add color = " + c); appendColor(c) })}
      </div>

    SHtml.ajaxForm(
      bind("rainbow", xhtml,
           "color" -> renderColor,
           AttrBindParam("addColor",
             CmdPair(
              SHtml.ajaxInvoke(() => {
                JqJsCmds.AppendHtml("rainbow",renderColor)
              })._2.cmd,JsRaw("return false").cmd).toJsCmd,
            "onclick")
          ) ++ SHtml.hidden(doSubmit _)
    )
  }
}
