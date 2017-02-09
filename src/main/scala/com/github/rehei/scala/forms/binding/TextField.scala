package com.github.rehei.scala.forms.binding

import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.decorators.AutoSizeDecorator
import com.github.rehei.scala.forms.decorators.PlaceholderDecorator
import com.github.rehei.scala.forms.decorators.PlaceholderDecorator

case class TextField() extends AbstractBinding[TextField] {

  def bind[T](context: Bindable, model: AnyRef, markupFactory: MarkupFactory[T]) = {
    markupFactory.renderBinding(context, model, this)
  }

  def placeholder(placeholder: String) = {
    this.decorateWith(PlaceholderDecorator(placeholder))
  }
  
  def placeholder() = {
    this.getDecorator[PlaceholderDecorator].map(_.placeholder)
  }
  
  def autosize(enable: Boolean) = {
    this.decorateWith(AutoSizeDecorator(enable))
  }
  
  def autosize() = {
    this.getDecorator[AutoSizeDecorator].getOrElse(false)
  }

}