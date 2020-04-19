package main

import (
log "github.com/Sirupsen/logrus"
"github.com/urfave/cli"	//这个包提供了命令行工具
"os"
)

const usage = `mydocker is a simple container runtime implementation.
                   The purpose of this project is to learn how docker works and how to write a docker by ourselves
                   Enjoy it, just for fun.`

func main() {
	app := cli.NewApp()
	app.Name = "mydocker"
	app.Usage = usage
	//暂时定义两个命令init、run
	app.Commands = []cli.Command{
		initCommand,
		runCommand,
	}
	//`app.Before` 内初始化了一下`logrus`的日志配置。
	app.Before = func(context *cli.Context) error {
		// Log as JSON instead of the default ASCII formatter.
		log.SetFormatter(&log.JSONFormatter{})
		log.SetOutput(os.Stdout)
		return nil
	}
	//运行出错时 记录日志
	if err := app.Run(os.Args); err != nil {
		log.Fatal(err)
	}
}