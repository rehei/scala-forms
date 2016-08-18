package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.markup.MarkupFactory
import scala.xml.NodeSeq

object Form extends Form()

class Form protected (val renderables: Renderable*) {

  def this() = {
    this(List(): _*)
  }

  def render(model: AnyRef, markupFactory: MarkupFactory): NodeSeq = {
    val subRendering = new SubRendering(model, markupFactory, renderables: _*)
    markupFactory.renderForm(subRendering)
  }

  def attach(renderable: Renderable) = {
    new Form((renderables :+ renderable): _*)
  } 
  
}