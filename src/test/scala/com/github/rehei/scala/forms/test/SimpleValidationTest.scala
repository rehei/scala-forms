package com.github.rehei.scala.forms.test

import com.github.rehei.scala.forms.test.model.Company
import org.junit.Test
import com.github.rehei.scala.forms.test.model.Employee
import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.macros.Reflection
import com.github.rehei.scala.forms.util.Conversions._
import com.github.rehei.scala.forms.validators.IsTrue
import com.github.rehei.scala.forms.validators.MinLength
import com.github.rehei.scala.forms.validators.MaxLength

class SimpleValidationTest {

  @Test
  def test() {

    val company = new Reflection[Company]
    val employee = new Reflection[Employee]

    val model = new Company()
    model.employees.add(new Employee())
    model.employees.add(new Employee())

    val validator = {
      Validator
        .attach(
          company(_.name)
            .assert(MinLength(5).message("foo"))
            .assert(MaxLength(10).message("bla")))
    }

    validator.validate(model)

  }

}