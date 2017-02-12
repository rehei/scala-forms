package com.github.rehei.scala.forms.validation.observe

import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.validation.ValidationResult

case class NoValidationObservable() extends AbstractValidationObservable {

  override def appendValidationResult(validationResult: ValidationResult) = Unit
  override def appendValidationCallback(validationCallback: ValidationCallback) = Unit

  override def validate() = true
  override def reset() = Unit
  override def createSubValidationObservable(validator: Validator, model: AnyRef) = this

}