package com.github.rehei.scala.forms.rules

import com.github.rehei.scala.forms.Field

class MaxCollectionSize(val sizeInclusive: Int) extends AbstractValidationRule {

  override def isValid(model: AnyRef, field: Field): Boolean = {
    field.getter(model).asInstanceOf[java.util.Collection[_]].size() <= sizeInclusive
  }

}