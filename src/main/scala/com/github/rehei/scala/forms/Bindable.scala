package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil
import com.github.rehei.scala.forms.binding.AbstractBinding
import scala.xml.NodeSeq
import scala.xml.Text
import scala.language.existentials
import scala.reflect.runtime.universe._
import com.github.rehei.scala.forms.decorators.LabelDecorator

class Bindable(val modelClazz: Class[_],
            val query: String,
            val binding: AbstractBinding[_]) extends Renderable {

  private val rawMethod = ReflectUtil.getGetterMethod(modelClazz, query)
  val getter = (model: AnyRef) => ReflectUtil.get(model, query)
  val setter = (model: AnyRef, value: Any) => {

    val postProcessedValue = {
      if (value != null && value.isInstanceOf[String]) {
        value.asInstanceOf[String].trim()
      } else {
        value
      }
    }

    ReflectUtil.set(model, query, postProcessedValue)
  }

  def render[T](model: AnyRef, markupFactory: AbstractMarkupFactory[T]) = {
    binding.bind(this, model, markupFactory)
  }

  def getFirstParameterizedTypeArgument(): Class[_] = {
    ReflectUtil.getFirstParameterizedTypeArgument(rawMethod)
  }
  
  def getBindableType(): Class[_] = {
    rawMethod.getReturnType
  }

}
  