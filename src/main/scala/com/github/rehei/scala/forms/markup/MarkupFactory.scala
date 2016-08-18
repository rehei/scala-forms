package com.github.rehei.scala.forms.markup

import com.github.rehei.scala.forms.Form
import com.github.rehei.scala.forms.Section
import com.github.rehei.scala.forms.SubRendering
import scala.xml.NodeSeq

trait MarkupFactory {

  def renderForm(subRendering: SubRendering): NodeSeq

  def renderSection(subRendering: SubRendering): NodeSeq

  def renderTextbox(): NodeSeq

}