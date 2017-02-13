package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.UndeterminedBindable

case class MaxLength(val sizeInclusive: Int) extends AbstractValidationRule {

  override def isValid(model: AnyRef, field: UndeterminedBindable): Boolean = {
    field.getter(model) match {
      case null => true
      case m if m.toString().length() <= sizeInclusive => true
      case _ => false
    }
  }

}