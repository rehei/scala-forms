package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.UndeterminedBindable

case class MinLength(val sizeInclusive: Int) extends AbstractValidationRule {

  override def isValid(model: AnyRef, field: UndeterminedBindable): Boolean = {
    field.getter(model) match {
      case null => false
      case m if m.toString().length() < sizeInclusive => false
      case _ => true
    }
  }

}