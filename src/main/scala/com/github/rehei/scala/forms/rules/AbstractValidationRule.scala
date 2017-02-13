package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.validation.Validation
import com.github.rehei.scala.forms.UndeterminedBindable

abstract class AbstractValidationRule {

  def isWrong(model: AnyRef, field: UndeterminedBindable) = {
    !isValid(model, field)
  }

  def isValid(model: AnyRef, field: UndeterminedBindable): Boolean

  def message(message: String) = {
    Validation(this, message)
  }

}