package map_test

import "testing"

func TestMapWithFunValue(t *testing.T)  {
	//Key是整型、Value是方法类型
	m := map[int] func(op int) int {}

	m[1] = func(op int) int {
		return op
	}

	m[2] = func(op int) int {
		return op * op
	}

	m[3] = func(op int) int {
		return op * op * op
	}

	t.Log(m[1](2 ), m[2](2), m[3](2))
}

func TestMapForSet(t *testing.T)  {
	mySet := map[int]bool{}
	//1、添加元素
	mySet[1] = true

	//n := 3 //3 is not existing
	n := 1  //1 is existing

	//2、判断一个元素是否存在
	if mySet[n]{
		t.Logf("%d is existing", n) //1 is existing
	}else{
		t.Logf("%d is not existing", n)
	}

	//3、获取Set的长度
	mySet[2] = true
	mySet[3] = true
	t.Log("len =", len(mySet)) //len = 3

	//4、从Set删除元素
	delete(mySet, 1)
	n = 1
	if mySet[n]{
		t.Logf("%d is existing", n)
	}else{
		t.Logf("%d is not existing", n) //1 is not existing
	}
}