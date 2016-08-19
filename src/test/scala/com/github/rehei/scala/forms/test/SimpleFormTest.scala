package com.github.rehei.scala.forms.test

import scala.reflect.runtime.universe

import org.junit.Test
import org.junit.Assert
import org.junit.Assert._

import com.github.rehei.scala.forms.Form
import com.github.rehei.scala.forms.Section
import com.github.rehei.scala.forms.binding.StringBinding
import com.github.rehei.scala.forms.test.model.Company;
import com.github.rehei.scala.forms.util.Conversions.queryToBindable
import com.github.rehei.scala.macros.ReflectionMacros.$
import com.github.rehei.scala.forms.binding.InlineBinding
import com.github.rehei.scala.macros.Reflection
import com.github.rehei.scala.forms.test.model.Employee
import com.github.rehei.scala.forms.test.model.Employee
import com.github.rehei.scala.forms.model._

class SimpleFormTest {

  @Test
  def fu() {
    val model = new Company()
    model.employees.add(new Employee())
    model.employees.add(new Employee())

    val company = new Reflection[Company]
    val employee = new Reflection[Employee]

    val employeeForm =
      Form
        .attach(
          Section.attach(employee(_.name).bindUsing(new StringBinding())))

    val form =
      Form
        .attach(
          Section
            .attach(
              company(_.name).bindUsing(new StringBinding()))
            .attach(
              company(_.name).bindUsing(new StringBinding()))
            .attach(
              company(_.employees).bindUsing(new InlineBinding(employeeForm))))

    val result = form.render(model, new TestMarkupFactory())
    val expected = {
      MyForm(List(
        MySection(List(
          MyTextbox(),
          MyTextbox(),
          MyInlineBinding(List(
            MyForm(List(MySection(List(MyTextbox())))),
            MyForm(List(MySection(List(MyTextbox()))))))))))
    }

    assertEquals(result, expected)
    println(form.render(model, new TestMarkupFactory()))
  }

}