package interface_test

import (
	"testing"
)

type Programmer interface {
	WriteHelloWorld() string
}

type GoProgrammer struct {

}

func (goProgrammer *GoProgrammer) WriteHelloWorld() string{
	return "Hello world, golang"
}

func TestClient(t *testing.T) {
	programmer := new(GoProgrammer)
	t.Log(programmer.WriteHelloWorld()) //Hello world, golang
}