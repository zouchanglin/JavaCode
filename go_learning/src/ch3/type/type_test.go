package userType

import (
	"math"
	"testing"
)

type MyInt int64

func TestUserType(t *testing.T)  {
	var a int = 10
	var b int64  = 20

	//a = b error
	//b = a error

	var c MyInt  = 30

	//c = b error
	c = MyInt(b) //OK
	t.Log(a, b, c)
}

func TestIncludeNum(t *testing.T)  {
	t.Log(math.MaxInt64)
	t.Log(math.MaxFloat64)
	t.Log(math.MaxUint32)
	t.Log(math.MaxInt8)
}