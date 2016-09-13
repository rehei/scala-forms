package com.github.rehei.scala.forms.validators

import com.github.rehei.scala.forms.Field
import com.github.rehei.scala.forms.validation.AbstractValidation

case class IsTrue() extends AbstractValidation {

  def isValid(model: AnyRef, field: Field): Boolean = {
    field.getter(model) != null && field.getter(model) == true
  }

}