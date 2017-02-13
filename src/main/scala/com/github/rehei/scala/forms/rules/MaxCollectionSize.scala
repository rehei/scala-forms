package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.UndeterminedBindable

class MaxCollectionSize(val sizeInclusive: Int) extends AbstractValidationRule {

  override def isValid(model: AnyRef, field: UndeterminedBindable): Boolean = {
    field.getter(model).asInstanceOf[java.util.Collection[_]].size() <= sizeInclusive
  }

}