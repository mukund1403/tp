# User Guide

## Introduction

Split-liang is an application that helps you split expenses with friends in a fun way!


## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Viewing help: `help`
Shows a message explaining how to use the application.

Format: `help`

### Creating a group: `create group`

Creates a new group with the specified group name.

Format: `create group GROUP_NAME`

- `GROUP_NAME` is the name of the group.

Example: `create Friends`

This command will create a new group named 'Friends'.

### Entering a group: `enter`

Enters an existing group with the specified group name.

Format: `enter GROUP_NAME`

- `GROUP_NAME` is the name of the group.

Example: `enter Friends`

This command will enter the group named 'Friends'.

### Exiting a group: `exit`

Exits the current group.

Format: `exit GROUP_NAME`

Example: `exit Friends`

This command will exit the current group.

### Settle expenses: `settle`

Settles the expenses between two users in the group.

Format: `settle USER_NAME1 /user USER_NAME2`

- `USER_NAME1` is the name of the first user.
- `USER_NAME2` is the name of the second user.
- `/user` is a keyword to indicate the start of the second user's name.

Example: `settle Alice /user Bob`

This command will settle the expenses between Alice and Bob, showing what Alice owes Bob.

### Trying your luck: `luck`

Play slots to remove debts

Format: `luck` (Coming soon feature)

- Enters the slot machine 
  - `/reroll` to reroll the slots
  - `/exit` to exit the slot machine
  - Example: `/reroll`

This command enable users play slots to remove their debts

## FAQ

1. **How do I create a new group?**
   To create a new group, use the `create group` command followed by the group name.

## Command Summary

{Give a 'cheat sheet' of commands here}

- `help`: Shows a message explaining how to use the application.
- `create group GROUP_NAME`: Creates a new group with the specified group name.
- `enter GROUP_NAME`: Enters an existing group with the specified group name.
- `exit`: Exits the current group.
- `settle USER_NAME1 /user USER_NAME2`: Settles the expenses between two users in the group.
- `bye`: Exits the application.