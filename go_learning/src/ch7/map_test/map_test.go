package map_test

import "testing"

func TestInitMap(t *testing.T)  {
	m1 := map[string]int{"one":1, "two":2, "three":3}
	t.Log(m1, len(m1)) //map[one:1 three:3 two:2] 3

	m2 := map[int]int{}
	m2[4] = 16
	t.Log(m2, len(m2)) //map[4:16] 1

	m3 := make(map[int]int, 10)
	t.Log(m3, len(m3)) //map[] 0
}

func TestAccessNotExistingKey(t *testing.T)  {
	m := map[int]int{}
	t.Log(m[8], len(m)) //不存在也是0

	m[2] = 0
	t.Log(m[2])

	if v, ok:= m[3]; ok{
		t.Log("存在m[3],值为", v)
	}else {
		t.Log("不存在m[3]") //不存在m[3]
	}
}


func TestForEachMap(t *testing.T)  {
	m1 := map[string]int{"one":1, "two":2, "three":3}
	for k, v := range m1{
		t.Log(k, "=", v)
	}
}