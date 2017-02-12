package com.github.rehei.scala.forms.test

import scala.reflect.runtime.universe

import org.junit.Test
import org.junit.Assert
import org.junit.Assert._

import com.github.rehei.scala.forms.Form
import com.github.rehei.scala.forms.Section
import com.github.rehei.scala.forms.test.model.Company;
import com.github.rehei.scala.forms.util.Conversions.queryToBindable
import com.github.rehei.scala.macros.ReflectionMacros.$
import com.github.rehei.scala.forms.binding.InlineBinding
import com.github.rehei.scala.macros.Reflection
import com.github.rehei.scala.forms.test.model.Employee
import com.github.rehei.scala.forms.test.model.Employee
import com.github.rehei.scala.forms.binding.TextField
import com.github.rehei.scala.forms.validation.NoValidator

class SimpleFormTest {

  @Test
  def test() {
    val model = new Company()
    model.employees.add(new Employee())
    model.employees.add(new Employee())

    val company = new Reflection[Company]
    val employee = new Reflection[Employee]

    val employeeForm =
      Form
        .attach(
          Section.attach(employee(_.name).bindUsing(TextField())))

    val form =
      Form
        .attach(
          Section
            .attach(
              company(_.name).bindUsing(TextField()))
            .attach(
              company(_.name).bindUsing(TextField()))
            .attach(
              company(_.employees).bindUsing(new InlineBinding(employeeForm, NoValidator))))

    val result = form.render(model, new TestMarkupFactory())
    result match {
      case (
        MyForm(
          MySub(
            MySection(
              MySub(
                MyTextbox(),
                MyTextbox(),
                MyInlineFrame(
                  MySub(
                    MyInlineElement(MyForm(MySub(MySection(MySub(MyTextbox())))), MyRemoveButton(removeFunc1)),
                    MyInlineElement(MyForm(MySub(MySection(MySub(MyTextbox())))), MyRemoveButton(removeFunc2))),
                  MyInsertButton(insertFunc))))))) => {

        removeFunc1()
        removeFunc2()

        assert(model.employees.isEmpty())

        insertFunc()
        assert(model.employees.size() == 1)

      }
    }

  }

}