package com.github.rehei.scala.forms.test

import scala.reflect.runtime.universe

import org.junit.Test

import com.github.rehei.scala.forms.Form
import com.github.rehei.scala.forms.Section
import com.github.rehei.scala.forms.binding.StringBinding
import com.github.rehei.scala.forms.util.Conversions.queryToBindable
import com.github.rehei.scala.macros.ReflectionMacros.$

class SimpleFormTest {

  @Test
  def fu() {
    val model = new TestModel()
    val form = Form
      .attach(
        Section
          .attach(
            $[TestModel](_.name)
              .bindUsing(new StringBinding()))
          .attach(
            $[TestModel](_.name)
              .bindUsing(new StringBinding())))

              
    val result = form.render(model, new TestMarkupFactory())
              
    println(form.render(model, new TestMarkupFactory()))

  }

}