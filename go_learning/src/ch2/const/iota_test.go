package use

import "testing"

func TestConstUser(t *testing.T)  {
	const(
		Monday = iota + 1
		Tuesday
		Wednesday
		Thursday
		Friday
	)
	t.Logf("%d %d %d %d %d", Monday, Tuesday, Wednesday, Thursday, Friday)
}
func TestConstant(t *testing.T)  {
	a := 1
	//用位定义的常量标识
	const (
		Open = 1 << iota
		Close
		Pending
	)

	t.Log(a&Open == Open, a&Close == Close, a&Pending == Pending)
}
