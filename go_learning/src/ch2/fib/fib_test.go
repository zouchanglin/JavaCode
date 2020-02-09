package fib

import (
	"testing"
)

func TestFibList(t *testing.T)  {
	a := 1
	b := 1
	for i:=0; i<6; i++ {
		//fmt.Printf("%d ", a)
		t.Logf("%d ", a)
		tmp := a
		a = b
		b += tmp
	}
}