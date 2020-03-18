package error

import (
	"errors"
	"fmt"
	"testing"
)

//定义预置的错误
var MyError = errors.New("n should be in range [0, 100]")

func FibonacciNum(n int) ([]int, error){
	if n < 0 || n > 100{
		return nil, MyError
	}
	fibList :=[]int{1, 1}
	for i := 2; i< n ; i++{
		fibList = append(fibList, fibList[i-2] + fibList[i-1])
	}
	return fibList, nil
}

func TestFibonacciNum(t *testing.T)  {
	num, err := FibonacciNum(-10)
	if err == MyError{ //代码就可以判断了
		t.Log(err)
	}else{
		t.Log(num)
	}
}









func TestPanicVsExit(t *testing.T){
	defer func() {
		fmt.Println("Finally")
	}()
	fmt.Println("Start")
	//os.Exit(-1)
	panic(errors.New("Something wrong!"))
}


func TestPanic(t *testing.T){
	defer func() {
		if err := recover(); err != nil{
			fmt.Println(err)
		}
	}()
	fmt.Println("Start")

	panic(errors.New("Something wrong!"))
}