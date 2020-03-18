package string_test

import (
	"strconv"
	"strings"
	"testing"
)

func TestString(t *testing.T)  {
	var s string
	t.Log(s) //初始化默认值""
	s = "hello"
	t.Log(len(s))

	//s[3] = 'c' error string不可变
	s = "\xE4\xB8\xA5" //可以存储任意二进制数据
	t.Log(s, len(s)) //严
	s = "中国"
	t.Log(len(s)) //len求的是字节数

	s = "中"
	t.Log(len(s)) //3

	c := []rune(s)

	t.Logf("中 unicode %x", c[0]) //中 unicode 4e2d
	t.Logf("中 UTF8 %x", s) //中 UTF8 e4b8ad
}

func TestStringFunc(t *testing.T)  {
	//切割字符串
	s := "A,B,C"
	split := strings.Split(s, ",")
	for _, ch := range split{
		t.Log(ch)
	}

	//连接字符串数组的元素
	t.Log(strings.Join(split, "-"))

	//字符串与数字的转换
	s = strconv.Itoa(250)
	t.Logf("s = %s", s)

	num := "100"
	//返回两个值，err代表是否发生错误
	if i, err := strconv.Atoi(num); err == nil{
		t.Log(100 + i)
	}
}