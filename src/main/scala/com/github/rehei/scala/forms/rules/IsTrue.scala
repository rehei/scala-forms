package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.UndeterminedBindable

case class IsTrue() extends AbstractValidationRule {

  def isValid(model: AnyRef, field: UndeterminedBindable): Boolean = {
    field.getter(model) != null && field.getter(model) == true
  }

}