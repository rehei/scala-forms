package com.github.rehei.scala.forms

import scala.xml.NodeSeq
import com.github.rehei.scala.forms.markup.MarkupFactory

abstract class Renderable {
  
  def render(model: AnyRef, markupFactory: MarkupFactory): NodeSeq
  
}