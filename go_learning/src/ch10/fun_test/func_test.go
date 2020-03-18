package fun_test

import (
	"fmt"
	"testing"
	"time"
)

func timeSpent(inner func(op int) int) func(op int) int {
	return func(op int) int {
		start := time.Now()
		ret := inner(op)
		fmt.Println("time spent:", time.Since(start).Seconds())
		return ret
	}
}

func slowFunc(op int)int{
	time.Sleep(time.Second * 1)
	return op
}

func TestFuncPro(t *testing.T)  {
	spent := timeSpent(slowFunc)
	t.Log(spent(10))
}