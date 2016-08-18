package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.MarkupFactory

object Section extends Section()

class Section protected (val renderables: Renderable*) extends Renderable {

  def this() = {
    this(List(): _*)
  }

  def attach(renderable: Renderable) = {
    new Section((renderables :+ renderable): _*)
  }

  def render[T](model: AnyRef, markupFactory: MarkupFactory[T]) = {
    val subRendering = new SubRendering(model, markupFactory, renderables: _*)
    markupFactory.renderSection(subRendering)
  }

}