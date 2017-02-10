package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.markup.MarkupFactory
import scala.xml.NodeSeq
import com.github.rehei.scala.forms.validation.Validator

object Form extends Form()

class Form protected (val renderables: Renderable*) {

  def this() = {
    this(List(): _*)
  }

  def render[T](model: AnyRef, validator: Validator, markupFactory: MarkupFactory[T], callback: () => Unit, isRootForm: Boolean) = {
    val sub = markupFactory.reduce(renderables.map(_.render(model, markupFactory)))
    markupFactory.renderForm(model, validator, sub, callback, isRootForm)
  }

  def attach(renderable: Renderable) = {
    new Form((renderables :+ renderable): _*)
  } 
  
}