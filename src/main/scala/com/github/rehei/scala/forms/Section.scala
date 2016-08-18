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

  def render(model: AnyRef, markupFactory: MarkupFactory) = {
    val subRendering = new SubRendering(model: AnyRef, markupFactory: MarkupFactory, renderables: _*)
    markupFactory.renderSection(subRendering)
  }

}