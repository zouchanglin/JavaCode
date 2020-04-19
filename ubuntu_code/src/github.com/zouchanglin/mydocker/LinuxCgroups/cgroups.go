package main

import (
	"fmt"
	"io/ioutil"
	"os"
	"os/exec"
	"path"
	"strconv"
	"syscall"
)

func main()  {
	//挂载了memory subsystem的hierarchy的根目录位置
	const cgroupMemoryHierarchyMount = "/sys/fs/cgroup/memory"

	if os.Args[0] == "/proc/self/exe" {
		//容器进程
		fmt.Printf("current pid %d\n", syscall.Getpid())
		cmd := exec.Command("sh", "-c", `stress --vm-bytes 200m --vm-keep -m 1`)
		cmd.SysProcAttr = &syscall.SysProcAttr{
		}
		cmd.Stdin = os.Stdin
		cmd.Stdout = os.Stdout
		cmd.Stderr = os.Stderr
		if err := cmd.Run();err != nil{
			fmt.Println(err)
			os.Exit(1)
		}
	}

	cmd := exec.Command("/proc/self/exe")
	cmd.SysProcAttr = &syscall.SysProcAttr{
		Cloneflags: syscall.CLONE_NEWUTS|syscall.CLONE_NEWPID| syscall.CLONE_NEWNS,
	}
	cmd.Stdin = os.Stdin
	cmd.Stdout = os.Stdout
	cmd.Stderr = os.Stderr

	if err := cmd.Start();err != nil{
		fmt.Println("Error", err)
		os.Exit(1)
	}else {
		//得到fork出来进程映射在外部命名空间的pid
		fmt.Printf("%v", cmd.Process.Pid)
		// 在系统默认创建挂载了memory subsystem的Hierarchy上创建cgroup
		_ = os.Mkdir(path.Join(cgroupMemoryHierarchyMount, "test-memory-limit"), 0755)
		// 将容器进程加入到这个cgroup中
		_ = ioutil.WriteFile(path.Join(cgroupMemoryHierarchyMount,
			"test-memory-limit", "tasks"), []byte(strconv.Itoa(cmd.Process.Pid)), 0644)
		// 限制cgroup进程使用
		_ = ioutil.WriteFile(path.Join(cgroupMemoryHierarchyMount,
			"test-memory-limit", "memory.limit_in_bytes"), []byte("100m"), 0644)
	}
	_, _ = cmd.Process.Wait()
}