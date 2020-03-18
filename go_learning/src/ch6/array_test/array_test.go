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

	for _,data := range b{
		t.Log(data)
	}
	for _,data := range c{
		t.Log(data)
	}
}

func TestArraySection(t *testing.T){
	arr := [...]int{11, 22, 33, 44}
	arr_sec := arr[:3] //取前三个元素
	t.Log(arr_sec)
	arr_sec = arr[3:] //取下标为3的后面的所有元素
	t.Log(arr_sec)
}