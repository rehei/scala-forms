package com.github.rehei.scala.forms.markup

import com.github.rehei.scala.forms.Form
import com.github.rehei.scala.forms.Section
import com.github.rehei.scala.forms.SubRendering
import scala.xml.NodeSeq

trait MarkupFactory[T] {

  def renderForm(subRendering: SubRendering[T]): T

  def renderSection(subRendering: SubRendering[T]): T

  def renderTextbox(): T

}