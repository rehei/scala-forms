package com.github.rehei.scala.forms.validation.observe

import com.github.rehei.scala.forms.validation.Validator

case class NoValidationObservable() extends AbstractValidationObservable {

  override def registerResetCallback(resetCallback: ResetCallback) = Unit
  override def registerPreValidationCallback(preValidationCallback: PreValidationCallback) = Unit
  override def registerValidationCallback(validationCallback: ValidationCallback) = Unit

  override def renderResetCallbacks() = Unit
  override def renderPreValidationCallbacks() = true
  override def renderValidationCallbacks() = Unit
  override def createSubValidationObservable(validator: Validator, model: AnyRef) = this

}