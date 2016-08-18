package com.github.rehei.scala.forms

import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil
import com.github.rehei.scala.forms.binding.AbstractBinding


class Field(val modelClazz: Class[_],
            val query: String,
            val binding: AbstractBinding) extends Renderable {

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

  override def render(model: AnyRef, markupFactory: MarkupFactory) = {
    <bla></bla>
  }

}
  