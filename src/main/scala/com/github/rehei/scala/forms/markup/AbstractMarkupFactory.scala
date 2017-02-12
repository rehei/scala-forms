package com.github.rehei.scala.forms.markup

import com.github.rehei.scala.forms.binding.AbstractBinding
import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.binding.InlineBinding
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable

abstract class AbstractMarkupFactory[T] {

  def renderForm(validationObservable: AbstractValidationObservable, model: AnyRef, sub: T, callback: () => Unit, isRootForm: Boolean): T

  def renderSection(sub: T): T

  def renderBinding(validationObservable: AbstractValidationObservable, context: Bindable, model: AnyRef, binding: AbstractBinding[_]): T

  def createInlineMarkupFactory(validationObservable: AbstractValidationObservable, inlineBinding: InlineBinding): AbstractInlineMarkupFactory[T]

  def reduce(in: Iterable[T]): T

}