# User Guide

## Introduction

Split-liang is an application that helps you split expenses with friends in a fun way! If you are tired of keeping track and calculating who owes who, Split-liang is here to help you. With Split-liang, you can create groups, add members, add expenses, and settle debts between members. Split-liang will automatically calculate the amount each member owes or is owed. You can also play slots to remove your debts!

## Content Page
1. [Quick Start](#quick-start)
2. [Features](#features)
    - [Viewing help: `help`](#viewing-help-help)
    - [Creating a group: `create`](#creating-a-group-create)
    - [Entering a group: `enter`](#entering-a-group-enter)
    - [Add members to group: `member`](#add-members-to-group-member)
    - [Exiting a group: `exit`](#exiting-a-group-exit)
    - [Show balance of user: `balance`](#show-balance-of-user-balance)
    - [Settle expenses: `settle`](#settle-expenses-settle)
    - [Trying your luck: `luck`](#trying-your-luck-luck)
    - [Create expenses: `expense`](#create-expenses-expense)
    - [Saving the data](#saving-the-data)
    - [Saying goodbye: `bye`](#saying-goodbye-bye)

--------------------------------------------------------------------------------------------------------------------


## Quick Start

1. Ensure that you have Java 11 or above installed in your computer.
2. Download the latest version of `Split-liang` from [here](https://github.com/AY2324S2-CS2113-T15-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your Split-liang.
4. Open a command terminal, `cd` to the folder where the jar file is located. Run the command `java -jar Split-liang.jar`. The application should start and display the welcome message.

## Features

### Viewing help: `help`

This command will display a message explaining how to use the application.

Format: `help`

Example: `help`

Output:

Welcome, here is a list of commands:

- `help`: Access help menu.
- `create <name>`: Create a group.
- `exit <name>`: Exit current group.
- `member <name>`: Add a member to the group.
- `expense <description> /amount <amount> /paid <paid_by> /user <user_1> /user <user_2> ...`: Add an expense SPLIT
  EQUALLY.
- `expense <description> /unequal /amount <amount> /paid <paid_by> /user <user_1> <amount_owed> /user <user_2> <amount owed> ...`:
  Add an expense SPLIT UNEQUALLY.
- `list`: List all expenses in the group.
- `balance <user_name>`: Show user's balance.
- `settle <payer_name> /user <payee_name>`: Settle the amount between two users.
- `luck`: Luck is in the air tonight.


### Creating a group: `create`

Creates a new group with the specified group name.

Format: `create GROUP_NAME`

`GROUP_NAME` is the name of the group.

Example: `create Friends`

This command will create a new group named 'Friends'.

### Entering a group: `enter`

Enters an existing group with the specified group name.

Format: `enter GROUP_NAME`

`GROUP_NAME` is the name of the group.

Example: `enter Friends`

This command will enter the group named 'Friends'.

### Add members to group: `member`

Adds a new member to the group.

Format: `member USER_NAME`

`USER_NAME` is the name of the user to be added to the group.

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

Split-liang automatically saves the data in each group to `GROUP_NAME.txt` in the `data` folder after the application
exits. There is no need to save manually.

The data is loaded automatically when the application starts.

### Saying goodbye: `bye`

This command exits the application.

## FAQ

1. **Q: How do I create a new group?**
    - A: To create a new group, use the `create group` command followed by the group name.
2. **Q: How do I transfer my data to another device?**
    - A: You can copy the `data` folder to the new device to transfer your data.

## Command Summary

Action | Format, Examples
--------|------------------
Help | `help`
Create group | `create GROUP_NAME` <br> e.g. `create Friends`
Enter group | `enter GROUP_NAME` <br> e.g. `enter Friends`
Add member | `member USER_NAME` <br> e.g. `member Alice`
Exit group | `exit GROUP_NAME` <br> e.g. `exit Friends`
Settle expenses | `settle USER_NAME1 /user USER_NAME2` <br> e.g. `settle Alice /user Bob`
Exit application | `bye`


