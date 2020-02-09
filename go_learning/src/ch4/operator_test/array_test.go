package array_test

import "testing"

func TestArray(t *testing.T)  {
	a := [...]int{1,2,3,4,5}
	b := [...]int{1,2,3,4,5}

	t.Log(a)
	t.Log(a == b)
}

func TestBitOpt(t *testing.T)  {
	a := 7 //0111
	//用位定义的常量标识
	const (
		Open = 1 << iota
		Close
		Pending
	)
	//清除Open状态
	a = a &^ Open
	t.Log(a&Open == Open, a&Close == Close, a&Pending == Pending) //false true true
	//清除Close状态
	a = a &^ Close
	t.Log(a&Open == Open, a&Close == Close, a&Pending == Pending) //false false true
}