package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.Field

case class IsTrue() extends AbstractValidationRule {

  def isValid(model: AnyRef, field: Field): Boolean = {
    field.getter(model) != null && field.getter(model) == true
  }

}