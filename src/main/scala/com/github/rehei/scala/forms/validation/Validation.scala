package com.github.rehei.scala.forms.validation

import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.rules.AbstractValidationRule
import com.github.rehei.scala.forms.UndeterminedBindable

case class Validation(val validationRule: AbstractValidationRule, val defaultMessage: String) {

  def validate(model: AnyRef, field: UndeterminedBindable) = {
    val valid = this.isValid(model, field)
    val message = if (valid) { "" } else { defaultMessage }

    ValidationResult(field.query, validationRule.getClass.getCanonicalName, valid, message)
  }

  protected def isValid(model: AnyRef, field: UndeterminedBindable) = {
    validationRule.isValid(model, field)
  }

}