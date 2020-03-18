package client

import (
	"fmt"
	"testing"
)
import "ch15/service"


func TestPackage(t *testing.T)  {
	fibonacciNum, err := service.FibonacciNum(5)
	if err != nil{
		fmt.Println("This error")
	}
	fmt.Println(fibonacciNum)
}