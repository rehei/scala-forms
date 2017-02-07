package com.github.rehei.scala.forms.util

import com.github.rehei.scala.forms.decorators.FieldDecorator
import scala.language.existentials
import scala.reflect.runtime.universe._
import java.util.HashMap
import scala.reflect.ClassTag

object DataTypeConversionTable {

  protected val convert: HashMap[Class[_], (String) => _] = new HashMap[Class[_], (String) => _]()

  convert.put(classOf[String], (m: String) => m)
  convert.put(classOf[Int], (m: String) => m.toInt)
  convert.put(classOf[Double], (m: String) => m.toDouble)

  def convertTo(tag: Class[_]) = {
    convert.get(tag)
  }

}