package array_test

import (
	"testing"
)

func TestArray01(t *testing.T)  {
	//声明并初始化为默认值
	var a [3]int
	a[0] = 1

	b := [3] int {11, 22, 33}
	c := [2][2] int {{11, 22}, {33, 44}}

	t.Log(a)
	t.Log(b)
	t.Log(c)
}