package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.binding.AbstractBinding
import com.github.rehei.scala.forms.util.ReflectUtil

case class UndeterminedBindable(val modelClazz: Class[_], val query: String) {

  def bindUsing(binding: AbstractBinding[_]) = {
    new Bindable(this, binding)
  }

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

  def getFirstParameterizedTypeArgument(): Class[_] = {
    ReflectUtil.getFirstParameterizedTypeArgument(rawMethod)
  }

  def getBindableType(): Class[_] = {
    rawMethod.getReturnType
  }

}