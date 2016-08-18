package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.MarkupFactory

class SubRendering(model: AnyRef, markupFactory: MarkupFactory, renderables: Renderable*) {

  def render() = {
    renderables.flatMap(_.render(model, markupFactory))
  }

}