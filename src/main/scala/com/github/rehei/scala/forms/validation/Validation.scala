package com.github.rehei.scala.forms.validation

import com.github.rehei.scala.forms.Field

case class Validation (val validateFunc: (AnyRef, Field) => Boolean, val message: String) {

  def this(validateFunc: (AnyRef, Field) => Boolean) = this(validateFunc, "")

  def isValid(model: AnyRef, field: Field) = {
    validateFunc(model, field)
  }

  def isWrong(model: AnyRef, field: Field) = !isValid(model, field)

}