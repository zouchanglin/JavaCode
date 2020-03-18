package polymorphic

import (
	"fmt"
	"testing"
)

type Code string

type Programmer interface {
	WriteHelloWorld() Code
}

type GoProgrammer struct {
}

func (goProgrammer *GoProgrammer) WriteHelloWorld() Code {
	return "Hello world, golang"
}

type JavaProgrammer struct {
}

func (javaProgrammer *JavaProgrammer) WriteHelloWorld() Code {
	return "Hello world, Java"
}

func WriteFirstProgrammer(p Programmer){
	fmt.Printf("%T %v\n", p, p.WriteHelloWorld())
}

func TestPolymorphic(t *testing.T)  {
	goProgrammer := new(GoProgrammer)
	javaProgrammer := new(JavaProgrammer)
	WriteFirstProgrammer(goProgrammer) //*polymorphic.GoProgrammer Hello world, golang
	WriteFirstProgrammer(javaProgrammer) //*polymorphic.JavaProgrammer Hello world, Java
}