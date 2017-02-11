package com.github.rehei.scala.forms

import scala.xml.NodeSeq
import com.github.rehei.scala.forms.markup.AbstractMarkupFactory

abstract class Renderable {
  
  def render[T](model: AnyRef, markupFactory: AbstractMarkupFactory[T]): T
  
}