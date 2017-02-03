package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.Field
import com.github.rehei.scala.forms.validation.Validation

abstract class AbstractValidationRule {

  def isWrong(model: AnyRef, field: Field) = {
    !isValid(model, field)
  }

  def isValid(model: AnyRef, field: Field): Boolean

  def message(message: String) = {
    Validation(this, message)
  }

}