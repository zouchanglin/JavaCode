package extern_test2

import (
	"fmt"
	"testing"
)

type Pet struct {

}

func (pet *Pet) Speck() {
	fmt.Println("...")
}

func (pet *Pet) SpeckTo(host string)  {
	pet.Speck()
	fmt.Println(" ", host)
}

type Dog struct {
	Pet
}

func (dog *Dog) Speck() {
	fmt.Println("Wang Wang!")
}


func TestDog(t *testing.T)  {
	dog := new(Dog)
	//dog.SpeckTo("Lin")
	dog.Speck()
}