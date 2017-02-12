package com.github.rehei.scala.forms.validation.observe

import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.validation.UIValidation

abstract class AbstractValidationObservable {
 
  def appendValidation(validation: UIValidation)
  
  def registerResetCallback(resetCallback: ResetCallback)
  
  def registerPreValidationCallback(preValidationCallback: PreValidationCallback)
  
  def registerValidationCallback(validationCallback: ValidationCallback)

  def reset()

  def renderPreValidationCallbacks(): Boolean

  def renderValidationCallbacks()
  
  def createSubValidationObservable(validator: Validator, model: AnyRef): AbstractValidationObservable
  
  
}