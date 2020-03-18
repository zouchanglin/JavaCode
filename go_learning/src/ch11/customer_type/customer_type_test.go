package customer_type

import (
	"fmt"
	"testing"
	"time"
)

type IntConv func(op int) int

func slowFunc(op int)int{
	time.Sleep(time.Second * 1)
	return op
}

func timeSpent(inner IntConv) IntConv {
	return func(op int) int {
		start := time.Now()
		ret := inner(op)
		fmt.Println("time spent:", time.Since(start).Seconds())
		return ret
	}
}

func TestFuncPro(t *testing.T)  {
	spent := timeSpent(slowFunc)
	t.Log(spent(10))
}