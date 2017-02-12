package com.github.rehei.scala.forms.validation.observe

import com.github.rehei.scala.forms.validation.Validator

abstract class AbstractValidationObservable {
 
  def registerResetCallback(resetCallback: ResetCallback)
  
  def registerPreValidationCallback(preValidationCallback: PreValidationCallback)
  
  def registerValidationCallback(validationCallback: ValidationCallback)

  def renderResetCallbacks()

  def renderPreValidationCallbacks(): Boolean

  def renderValidationCallbacks()
  
  def createSubValidationObservable(validator: Validator, model: AnyRef): AbstractValidationObservable
  
}