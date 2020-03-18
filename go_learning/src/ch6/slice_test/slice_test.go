package slice_test

import "testing"

func TestSlice(t *testing.T)  {
	var s0 []int //切片的声明
	t.Log(len(s0), cap(s0)) //0 0
	s0 = append(s0, 1)
	t.Log(len(s0), cap(s0)) //1 1

	s1 := []int{1, 2, 3, 4}
	t.Log(len(s1), cap(s1)) //4 4

	s2 := make([]int, 3, 5)
	t.Log(len(s2), cap(s2)) //4 4

	s2 = append(s2, 1)
	t.Log(len(s2), cap(s2)) //4 4
	t.Log(s2[0], s2[1], s2[2], s2[3])
}

func TestSliceGrowing(t *testing.T)  {
	s := []int{}
	for i:=0; i<10; i++{
		s = append(s, i)
		t.Log(len(s), cap(s))
	}
}

func TestSliceShareMemory(t *testing.T){
	year := []string{"Jan", "Feb", "Mar", "Apr", "May", "Jun",
		"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}

	Q2 := year[3:6]
	t.Log(Q2, len(Q2), cap(Q2)) //[Apr May Jun] 3 9
	summer := year[5:8]
	t.Log(summer, len(summer), cap(summer)) //[Jun Jul Aug] 3 7
	summer[0] = "UnKnow"
	t.Log(Q2, len(Q2), cap(Q2)) //[Apr May UnKnow] 3 9
}

func TestSliceCompare(t *testing.T){
	//a := []int{1, 2 , 3, 4}
	//b := []int{1, 2 , 3, 4}
	//if a + b{ //error
	//	t.Log("equal")
	//}
}