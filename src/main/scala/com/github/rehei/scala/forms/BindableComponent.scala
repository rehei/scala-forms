package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil
import com.github.rehei.scala.forms.binding.AbstractBinding
import scala.xml.NodeSeq
import scala.xml.Text
import scala.language.existentials
import scala.reflect.runtime.universe._
import com.github.rehei.scala.forms.decorators.LabelDecorator
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable

class BindableComponent(val undeterminedBindable: Bindable,
            val binding: AbstractBinding[_]) extends Renderable {

  def render[T](validationObservable: AbstractValidationObservable, model: AnyRef, markupFactory: AbstractMarkupFactory[T]) = {
    binding.bind(validationObservable, this, model, markupFactory)
  }
  
  def query = undeterminedBindable.query
  
  def getter = undeterminedBindable.getter
  def setter = undeterminedBindable.setter

  def getFirstParameterizedTypeArgument() = undeterminedBindable.getFirstParameterizedTypeArgument()
  def getBindableType(): Class[_] = undeterminedBindable.getBindableType()

}
  