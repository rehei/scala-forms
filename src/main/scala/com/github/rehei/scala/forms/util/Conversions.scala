package com.github.rehei.scala.forms.util

import com.github.rehei.scala.macros.Query
import scala.language.implicitConversions
import com.github.rehei.scala.forms.Bindable

object Conversions {
  implicit def queryToBindable(query: Query) = {
    Bindable(Class.forName(query.clazzName), query.propertyPath)
  }
}