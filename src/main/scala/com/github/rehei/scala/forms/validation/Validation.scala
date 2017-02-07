package com.github.rehei.scala.forms.validation

import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.rules.AbstractValidationRule

case class Validation(val validationRule: AbstractValidationRule, val defaultMessage: String) {

  def validate(model: AnyRef, field: Bindable) = {
    val valid = this.isValid(model, field)
    val message = if (valid) { "" } else { defaultMessage }

    ValidationResult(field.query, validationRule.getClass.getCanonicalName, valid, message)
  }

  protected def isValid(model: AnyRef, field: Bindable) = {
    validationRule.isValid(model, field)
  }

}