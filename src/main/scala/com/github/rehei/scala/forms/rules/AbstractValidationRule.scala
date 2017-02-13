package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.BindableComponent
import com.github.rehei.scala.forms.validation.Validation
import com.github.rehei.scala.forms.Bindable

abstract class AbstractValidationRule {

  def isWrong(model: AnyRef, field: Bindable) = {
    !isValid(model, field)
  }

  def isValid(model: AnyRef, field: Bindable): Boolean

  def message(message: String) = {
    Validation(this, message)
  }

}