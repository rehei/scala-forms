package com.github.rehei.scala.forms.validators

import com.github.rehei.scala.forms.validation.AbstractValidation
import com.github.rehei.scala.forms.Field

case class MaxLength(val sizeInclusive: Int) extends AbstractValidation {

  override def isValid(model: AnyRef, field: Field): Boolean = {
    field.getter(model) match {
      case null => true
      case m if m.toString().length() <= sizeInclusive => true
      case _ => false
    }
  }

}