package obj_test

import (
	"fmt"
	"testing"
)

type Employee struct {
	Id string
	Name string
	Age int
}

//行为定义
func (e Employee) toString() string {
	fmt.Printf("toString e.Name address is %x\n", &e.Name)
	return fmt.Sprintf("Id=%s Name=%s Age=%d", e.Id, e.Name, e.Age)
}

//行为定义
func (e *Employee) toStringPoint() string {
	fmt.Printf("toStringPoint e.Name address is %x\n", &e.Name)
	return fmt.Sprintf("Id=%s Name=%s Age=%d", e.Id, e.Name, e.Age)
}

func TestToString(t *testing.T)  {
	employee := Employee{"0", "Bob", 20}
	fmt.Printf("TestToString e.Name address is %x\n", &employee.Name)
	t.Log(employee.toString())
	t.Log(employee.toStringPoint())
}

func TestCreateEmployee(t *testing.T){
	employee := Employee{"0", "Bob", 20}
	employee2 := Employee{Id:"0", Name:"Bob", Age:20}
	employee3 := new(Employee) //返回的是指针
	employee3.Id = "0"
	employee3.Name = "June"
	employee3.Age = 10

	t.Log(employee) //{0 Bob 20}
	t.Log(employee2) //{0 Bob 20}
	t.Log(*employee3) //{0 June 10}

	//employee type is obj_test.Employee
	t.Logf("employee type is %T", employee)

	//employee3 type is *obj_test.Employee
	t.Logf("employee3 type is %T", employee3)
}

