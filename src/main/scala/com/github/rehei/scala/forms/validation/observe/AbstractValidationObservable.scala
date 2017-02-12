package com.github.rehei.scala.forms.validation.observe

import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.validation.ValidationResult

abstract class AbstractValidationObservable {

  def appendValidationResult(validationResult: ValidationResult)
  def appendValidationCallback(validationCallback: ValidationCallback)

  def validate(): Boolean
  def reset()
  def createSubValidationObservable(validator: Validator, model: AnyRef): AbstractValidationObservable

}