package obj_type

import (
	"fmt"
	"testing"
)

func DoSomething(p interface{})  {
	if i, ok := p.(int);ok{
		fmt.Printf("This is int, %d\n", i)
		return
	}

	if i, ok := p.(string);ok{
		fmt.Printf("This is String, %s\n", i)
		return
	}
	fmt.Println("Unknow Type")
}

func DoSomethingSwitch(p interface{})  {
	switch v:=p.(type) {
	case int:
		fmt.Printf("This is int, %d\n", v)
	case string:
		fmt.Printf("This is string, %s\n", v)
	default:
		fmt.Printf("Unknow Type\n")
	}
}



func TestDoSomething(t *testing.T)  {
	DoSomething(200) //This is int, 200
	DoSomethingSwitch("Hello") //This is string, Hello
}