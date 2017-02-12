package com.github.rehei.scala.forms.validation.observe

import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.validation.UIValidation

case class NoValidationObservable() extends AbstractValidationObservable {

  override def appendValidation(validation: UIValidation) = Unit
  override def registerResetCallback(resetCallback: ResetCallback) = Unit
  override def registerPreValidationCallback(preValidationCallback: PreValidationCallback) = Unit
  override def registerValidationCallback(validationCallback: ValidationCallback) = Unit

  override def reset() = Unit
  override def renderPreValidationCallbacks() = true
  override def renderValidationCallbacks() = Unit
  override def createSubValidationObservable(validator: Validator, model: AnyRef) = this

}