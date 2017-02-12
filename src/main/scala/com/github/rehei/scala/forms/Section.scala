package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable

object Section extends Section()

class Section protected (val renderables: Renderable*) extends Renderable {

  def this() = {
    this(List(): _*)
  }

  def attach(renderable: Renderable) = {
    new Section((renderables :+ renderable): _*)
  }

  def render[T](validationObservable: AbstractValidationObservable, model: AnyRef, markupFactory: AbstractMarkupFactory[T]) = {
    val sub = markupFactory.reduce(renderables.map(_.render(validationObservable, model, markupFactory)))
    markupFactory.renderSection(sub)
  }

}