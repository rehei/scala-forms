package com.github.rehei.scala.forms.validation

import com.github.rehei.scala.forms.Field

abstract class AbstractValidation {

  def isWrong(model: AnyRef, field: Field) = {
    !isValid(model, field)
  }

  def isValid(model: AnyRef, field: Field): Boolean

  def message(message: String) = {
    Validation(isValid _, message)
  }

}