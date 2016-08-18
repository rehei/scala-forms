package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.MarkupFactory

class SubRendering[T](model: AnyRef, markupFactory: MarkupFactory[T], renderables: Renderable*) {

  def render() : Seq[T] = {
    renderables.map(_.render(model, markupFactory))
  }

}