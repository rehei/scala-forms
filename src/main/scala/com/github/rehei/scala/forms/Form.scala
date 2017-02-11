package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import scala.xml.NodeSeq
import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.validation.NoValidator

object Form extends Form()

class Form protected (
    val validator: Validator, 
    val onSubmitCallback: () => Unit, 
    val nested: Boolean, 
    val renderables: Renderable*) {

  def this() = {
    this(NoValidator, () => Unit, false, List(): _*)
  }

  def render[T](model: AnyRef,  markupFactory: AbstractMarkupFactory[T]) = {
    val sub = markupFactory.reduce(renderables.map(_.render(model, markupFactory)))
    markupFactory.renderForm(model, validator, sub, onSubmitCallback, nested)
  }

  def attach(renderable: Renderable) = {
    new Form(validator, onSubmitCallback, nested, (renderables :+ renderable): _*)
  } 
  
  def validateWith(validator: Validator) = {
    new Form(validator, onSubmitCallback, nested, renderables: _*)
  } 
  
  def onSubmit(callback: () => Unit) = {
    new Form(validator, callback, nested, renderables: _*)
  }
  
  def nested(isNested: Boolean) = {
    new Form(validator, onSubmitCallback, isNested, renderables: _*)
  }
  
}