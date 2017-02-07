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

  def render[T](model: AnyRef, markupFactory: MarkupFactory[T], callback: () => Unit) = {
    val sub = markupFactory.reduce(renderables.map(_.render(model, markupFactory)))
    markupFactory.renderForm(sub, callback)
  }

  def attach(renderable: Renderable) = {
    new Form((renderables :+ renderable): _*)
  } 
  
}