package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import scala.xml.NodeSeq
import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.validation.NoValidator
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable
import com.github.rehei.scala.forms.validation.observe.NoValidationObservable

object Form extends Form()

class Form protected (
    protected val validationObservable: AbstractValidationObservable,
    protected val onSubmitCallback: () => Unit,
    protected val nested: Boolean,
    protected val renderables: Renderable*) {

  def this() = {
    this(NoValidationObservable(), () => Unit, false, List(): _*)
  }

  def render[T](model: AnyRef, markupFactory: AbstractMarkupFactory[T]) = {
    val sub = markupFactory.reduce(renderables.map(_.render(validationObservable, model, markupFactory)))
    markupFactory.renderForm(validationObservable, model, sub, onSubmitCallback, nested)
  }

  def attach(renderable: Renderable) = {
    new Form(validationObservable, onSubmitCallback, nested, (renderables :+ renderable): _*)
  }

  def validateWith(newValidationObservable: AbstractValidationObservable) = {
    new Form(newValidationObservable, onSubmitCallback, nested, renderables: _*)
  }
  
  def validateWith() = {
    validationObservable
  }

  def onSubmit(callback: () => Unit) = {
    new Form(validationObservable, callback, nested, renderables: _*)
  }

  def nested(isNested: Boolean) = {
    new Form(validationObservable, onSubmitCallback, isNested, renderables: _*)
  }

}