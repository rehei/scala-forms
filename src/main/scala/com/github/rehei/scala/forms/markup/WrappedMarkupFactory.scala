package com.github.rehei.scala.forms.markup

import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable
import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.binding.AbstractBinding
import com.github.rehei.scala.forms.binding.InlineBinding
import com.github.rehei.scala.forms.validation.Validator

class WrappedMarkupFactory[T](protected val base: AbstractMarkupFactory[T]) extends AbstractMarkupFactory[T] {

  override def renderForm(validationObservable: AbstractValidationObservable, model: AnyRef, sub: T, beforeDataBound: () => Unit, postDataBound: () => Unit, isRootForm: Boolean) = {
    base.renderForm(validationObservable, model, sub, beforeDataBound, postDataBound, isRootForm)
  }

  override def renderSection(sub: T) = {
    base.renderSection(sub)
  }

  override def renderBinding(validationObservable: AbstractValidationObservable, context: Bindable, model: AnyRef, binding: AbstractBinding[_]) = {
    base.renderBinding(validationObservable, context, model, binding)
  }

  override def createInlineMarkupFactory(validationObservable: AbstractValidationObservable, inlineBinding: InlineBinding) = {
    base.createInlineMarkupFactory(validationObservable, inlineBinding)
  }

  override def createValidationObservable(validator: Validator, model: AnyRef) = {
    base.createValidationObservable(validator, model)
  }

  override def reduce(in: Iterable[T]) = {
    base.reduce(in)
  }

}