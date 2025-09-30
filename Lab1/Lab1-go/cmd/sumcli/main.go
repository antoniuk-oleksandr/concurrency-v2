package main

import (
	"fmt"
	"lab1-go/internal/commands"
	"os"

	"github.com/spf13/cobra"
)

func main() {
	rootCmd := &cobra.Command{
		Use:   "sumcli",
		Short: "CLI for summing arrays using different strategies",
	}

	commands.RegisterStrategyCommands(rootCmd)

	if err := rootCmd.Execute(); err != nil {
		fmt.Println(err)
		os.Exit(1)
	}
}
