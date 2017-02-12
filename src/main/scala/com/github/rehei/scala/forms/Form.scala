package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import scala.xml.NodeSeq
import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.validation.NoValidator
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable

object Form extends Form()

class Form protected (
    protected val validator: Validator,
    protected val onSubmitCallback: () => Unit,
    protected val nested: Boolean,
    protected val renderables: Renderable*) {

  def this() = {
    this(NoValidator, () => Unit, false, List(): _*)
  }

  def render[T](model: AnyRef, markupFactory: AbstractMarkupFactory[T]) = {
    
    val validationObservable = markupFactory.createValidationObservable(validator, model)
    
    def beforeDataBound = {
      validationObservable.reset()
    }
    
    def postDataBound = {
      val success = validationObservable.validate() 
      if (success) {
        onSubmitCallback()
      }
    }

    val sub = markupFactory.reduce(renderables.map(_.render(validationObservable, model, markupFactory)))
    markupFactory.renderForm(validationObservable, model, sub, beforeDataBound _, postDataBound _, nested) 
  }

  def attach(renderable: Renderable) = {
    new Form(validator, onSubmitCallback, nested, (renderables :+ renderable): _*)
  }

  def validateWith(validator: Validator) = {
    new Form(validator, onSubmitCallback, nested, renderables: _*)
  }
  
  def validateWith() = {
    validator
  }

  def onSubmit(callback: () => Unit) = {
    new Form(validator, callback, nested, renderables: _*)
  }

  def nested(isNested: Boolean) = {
    new Form(validator, onSubmitCallback, isNested, renderables: _*)
  }

}