package com.github.rehei.scala.forms.validators

import com.github.rehei.scala.forms.validation.AbstractValidation
import com.github.rehei.scala.forms.Field

class MaxCollectionSize(val sizeInclusive: Int) extends AbstractValidation {

  override def isValid(model: AnyRef, field: Field): Boolean = {
    field.getter(model).asInstanceOf[java.util.Collection[_]].size() <= sizeInclusive
  }

}