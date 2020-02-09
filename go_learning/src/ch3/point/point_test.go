package point

import (
	"testing"
)

func TestPoint_01(t *testing.T)  {
	a := 10
	aPtr := &a
	//aPtr++ error
	t.Log("a =",*aPtr)
}

func TestString_01(t *testing.T)  {
	var str string
	t.Logf("*%s*", str)
	t.Log(len(str))
}