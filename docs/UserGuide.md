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

### Add members to group: `member`

Adds a new member to the group.

Format: `member USER_NAME`
- `USER_NAME` is the name of the user to be added to the group. 

Example: `member Alice`

This command will add a new member named 'Alice' to the group.

Output: `Alice has been added to group.`


### Exiting a group: `exit`

Exits the current group.

Format: `exit GROUP_NAME`

Example: `exit Friends`

This command will exit the current group.

### Show balance of user: `balance`

Shows list of members the user owes money to.

Format: `balance USER_NAME`

Example: `balance Shaoliang`

This command will display the balance of the user named Shaoliang.

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

### Create expenses: `expense`

Create a new expense for a given group.

#### Create expense split equally
Format:`expense DESCRIPITON /amount AMOUNT /paid PAYER_USER_NAME /user USER_NAME /user USER_NAME`

`PAYER_USER_NAME` is the username of the person who paid for the transaction.
`USER_NAME` is the username of the payee.

- The amount will be split equally between all members including the payer.
- The expense will be added to a list of expenses.

#### Create expense split unequally
Format:`expense DESCRIPITON /unequal /amount TOTAL_AMOUNT 
/paid PAYER_USER_NAME /user USER_NAME AMOUNT_OWED /user USER_NAME AMOUNT_OWED`

`PAYER_USER_NAME` is the username of the person who paid for the transaction.
`USER_NAME` is the username of the payee.
`AMOUNT_OWED` is the amount owed by the 

- The amount will be split unequally between all members including the payer based on the `AMOUNT_OWED`.
- The expense will be added to a list of expenses.

### Saving the data

Split-liang automatically saves the data in each group to `GROUP_NAME.txt` in the `data` folder after the application exits. There is no need to save manually.

The data is loaded automatically when the application starts.

### Saying goodbye: `bye`

This command exits the application.


## FAQ

1. **Q: How do I create a new group?**
   - A: To create a new group, use the `create group` command followed by the group name.
2. **Q: How do I transfer my data to another device?**
   - A: You can copy the `data` folder to the new device to transfer your data. 

## Command Summary

{Give a 'cheat sheet' of commands here}

- `help`: Shows a message explaining how to use the application.
- `create group GROUP_NAME`: Creates a new group with the specified group name.
- `enter GROUP_NAME`: Enters an existing group with the specified group name.
- `member USER_NAME`: Adds a new member to the group.
- `exit`: Exits the current group.
- `settle USER_NAME1 /user USER_NAME2`: Settles the expenses between two users in the group.
- `bye`: Exits the application.