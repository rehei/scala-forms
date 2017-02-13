package com.github.rehei.scala.forms.binding

import scala.collection.mutable.ListBuffer

import com.github.rehei.scala.forms.BindableComponent
import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import com.github.rehei.scala.forms.decorators.LabelDecorator
import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil
import scala.xml.NodeSeq
import scala.xml.Text
import scala.language.existentials
import scala.reflect.runtime.universe._
import com.github.rehei.scala.forms.decorators.LabelDecorator
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable

abstract class AbstractBinding[X <: Decoratable[_]] extends Decoratable[X] {

  def bind[T](validationObservable: AbstractValidationObservable, context: BindableComponent, model: AnyRef, markupFactory: AbstractMarkupFactory[T]): T = {
    markupFactory.renderBinding(validationObservable, context, model, this)
  }

  def label() = {
    getDecorator[LabelDecorator].map(_.label)
  }

  def label(label: String) = {
    this.decorateWith(LabelDecorator(label))
  }

}