package for_test

import "testing"

func TestForPrint(t *testing.T)  {
	for a := 10; a<100; a++{
		t.Log("XXX", a)
	}

	for {
		t.Log("AAA")
	}
}