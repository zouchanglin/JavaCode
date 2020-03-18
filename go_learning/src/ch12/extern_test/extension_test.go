package extern_test

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
	pet *Pet
}

func (dog *Dog) Speck() {
	dog.pet.Speck()
}

func (dog *Dog) SpeckTo(host string)  {
	dog.pet.Speck()
	fmt.Println(" ", host)
}

func TestDog(t *testing.T)  {
	dog := new(Dog)
	dog.SpeckTo("Lin")
}