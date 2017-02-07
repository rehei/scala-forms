package com.github.rehei.scala.forms.util

import com.github.rehei.scala.forms.decorators.FieldDecorator
import scala.language.existentials
import scala.reflect.runtime.universe._
import java.util.HashMap

object DataTypeConversionTable {

  protected val convert: HashMap[Type, (String) => _] = new HashMap[Type, (String) => _]()

  convert.put(typeOf[String], (m: String) => m)
  convert.put(typeOf[Int], (m: String) => m.toInt)
  convert.put(typeOf[Double], (m: String) => m.toDouble)

  def convertTo[T](implicit tag: TypeTag[T]) = {
    convert.get(tag.tpe)
  }

}