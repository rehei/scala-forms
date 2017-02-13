package com.github.rehei.scala.forms.util

import com.github.rehei.scala.macros.Query
import scala.language.implicitConversions
import com.github.rehei.scala.forms.UndeterminedBindable
import com.github.rehei.scala.forms.validation.Assertion
import com.github.rehei.scala.forms.table.TableHead

object Conversions {

  implicit def queryToBindable(query: Query) = {
    UndeterminedBindable(Class.forName(query.clazzName), query.propertyPath)
  }
  implicit def queryToAssert(query: Query) = {
    Assertion(Class.forName(query.clazzName), query.propertyPath)
  }
  implicit def queryToHead(query: Query) = {
    new TableHead(Class.forName(query.clazzName), query.propertyPath)
  }
}