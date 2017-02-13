package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.Bindable

case class IsTrue() extends AbstractValidationRule {

  def isValid(model: AnyRef, field: Bindable): Boolean = {
    field.getter(model) != null && field.getter(model) == true
  }

}