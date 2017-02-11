package com.github.rehei.scala.forms.markup

import com.github.rehei.scala.forms.binding.AbstractBinding
import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.validation.Validator

abstract class MarkupFactory[T] {

  def renderForm(model: AnyRef, validator: Validator, sub: T, callback: () => Unit, isRootForm: Boolean): T

  def renderSection(sub: T): T

  def renderBinding(context: Bindable, model: AnyRef, binding: AbstractBinding[_]): T

  def createInlineMarkupFactory(): AbstractInlineMarkupFactory[T]

  def reduce(in: Iterable[T]): T

}