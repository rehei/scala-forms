package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.Bindable

case class MaxLength(val sizeInclusive: Int) extends AbstractValidationRule {

  override def isValid(model: AnyRef, field: Bindable): Boolean = {
    field.getter(model) match {
      case null => true
      case m if m.toString().length() <= sizeInclusive => true
      case _ => false
    }
  }

}