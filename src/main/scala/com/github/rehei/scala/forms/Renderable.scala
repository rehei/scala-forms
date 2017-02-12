package com.github.rehei.scala.forms

import scala.xml.NodeSeq
import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable

abstract class Renderable {
  
  def render[T](validationObservable: AbstractValidationObservable, model: AnyRef, markupFactory: AbstractMarkupFactory[T]): T
  
}