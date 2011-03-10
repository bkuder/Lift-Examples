package bootstrap.liftweb

import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import net.liftweb.http.LiftRules

class Boot {
  def boot {
    // Snippet package
    LiftRules.addToPackages("com.briank.test")

    // Build SiteMap
    LiftRules.setSiteMap(SiteMap(Menu(Loc("Index", "index" :: Nil, "Index")),
                                 Menu(Loc("AjaxButton", "ajaxButton" :: Nil, "Ajax Button")),
                                 Menu(Loc("RunMode", "runMode" :: Nil, "Run Mode"))))
  }
}
