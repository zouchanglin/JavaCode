package service

import (
	"errors"
	"fmt"
)

func init() {
	fmt.Println("service init1...")
}

func init() {
	fmt.Println("service init2...")
}


func FibonacciNum(n int) ([]int, error){
	if n < 0 || n > 100{
		return nil, errors.New("not in range [0, 100]")
	}
	fibList :=[]int{1, 1}
	for i := 2; i< n ; i++{
		fibList = append(fibList, fibList[i-2] + fibList[i-1])
	}
	return fibList, nil
}