package com.github.rehei.scala.forms.markup

import com.github.rehei.scala.forms.binding.AbstractBinding
import com.github.rehei.scala.forms.BindableComponent
import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.binding.InlineBinding
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable
import com.github.rehei.scala.forms.validation.observe.DefaultValidationObservable

abstract class AbstractMarkupFactory[T] {

  def renderForm(validationObservable: AbstractValidationObservable, model: AnyRef, sub: T, beforeDataBound: () => Unit, postDataBound: () => Unit, isRootForm: Boolean): T

  def renderSection(sub: T): T

  def renderBinding(validationObservable: AbstractValidationObservable, context: BindableComponent, model: AnyRef, binding: AbstractBinding[_]): T

  def createInlineMarkupFactory(validationObservable: AbstractValidationObservable, inlineBinding: InlineBinding): AbstractInlineMarkupFactory[T]

  def createValidationObservable(validator: Validator, model: AnyRef): AbstractValidationObservable = {
    new DefaultValidationObservable(model, validator)
  }

  def reduce(in: Iterable[T]): T

}