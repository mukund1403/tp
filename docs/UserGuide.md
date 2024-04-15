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
    - [Create a new expense: `expense`](#create-a-new-expense-expense)
    - [List all expenses for a group: `list`](#list-expenses-list)
    - [Delete an expense: `delete expense`](#delete-an-expense-delete-expense)
    - [Show balance of user: `balance`](#show-balance-of-user-balance)
    - [Settle expenses: `settle`](#settle-expenses-settle)
    - [Trying your luck: `luck`](#trying-your-luck-luck)
    - [Saving the data](#saving-the-data)
    - [Saying goodbye: `bye`](#saying-goodbye-bye)

--------------------------------------------------------------------------------------------------------------------


## Quick Start

1. Ensure that you have Java 11 or above installed in your computer.
2. Download the latest version of `Split-liang` from [here](https://github.com/AY2324S2-CS2113-T15-3/tp/releases).
3. Copy the file to the folder you want to use as the home folder for your Split-liang.
4. Open a command terminal, `cd` to the folder where the jar file is located. Run the command `java -jar Split-liang.jar`. The application should start and display the welcome message.

## Command Structure

A command has the general format:
```
COMMAND ARGUMENT /param PARAM1 /param PARAM2 ...
```

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

--------------------------------------------------------------------------------------------------------------------
### GROUP COMMANDS

#### Creating a group: `create`

Creates a new group with the specified group name.

Format: `create /group GROUP_NAME`

- `/group` is a keyword to indicate the start of the group name.
- `GROUP_NAME` is the name of the group.

Example: `create /group Friends`

This command will create a new group named 'Friends'.

#### Entering a group: `enter`

Enters an existing group with the specified group name.

Format: `enter /group GROUP_NAME`

- `/group` is a keyword to indicate the start of the group name.
- `GROUP_NAME` is the name of the group.

Example: `enter /group Friends`

This command will enter the group named 'Friends'.

#### Add members to group: `member`

Adds a new member to the group.

Format: `member USER_NAME`

- `USER_NAME` is the name of the user to be added to the group. It is in alphanumeric format.
- `USER_NAME` must be unique. It cannot be the same as an existing member's name.
- `USER_NAME` can contain whitespaces but cannot be empty.
- `USER_NAME` is not case-sensitive.
- `USER_NAME` can contain special characters.

Example: `member Alice`

This command will add a new member named 'Alice' to the group.

Output: `Alice has been added to group.`

#### Exiting a group: `exit`

Exits the current group.

Format: `exit /group GROUP_NAME`

- `/group` is a keyword to indicate the start of the group name.
- `GROUP_NAME` is the name of the group.

Example: `exit /group Friends`

This command will exit the group named 'Friends'.

--------------------------------------------------------------------------------------------------------------------
### EXPENSE COMMANDS

#### Create a new expense: `expense`

Create a new expense for a given group.

##### 1. Create expense split equally

Format:`expense DESCRIPITON /amount AMOUNT /paid PAYER_USER_NAME /user USER_NAME /user USER_NAME`

- `PAYER_USER_NAME` is the username of the person who paid for the transaction.
- `USER_NAME` is the username of payees. Each expense can have multiple payees but only one payer.
- `AMOUNT` has to be a valid float value. It will be split equally between all members including the payer.
- If `AMOUNT` value entered is greater than 2 decimal places, it will round off to 2 decimal places before calculating split.
- The payer name (`PAYER_USER_NAME`) and all payees (`USER_NAME`) must be existing members of the group. 
Otherwise, exception will be thrown.
- Once the expense is created, the success 
- The expense will be added to a list of expenses.

Examples:
- `expense dinner /amount 9.00 /paid Alice /user Bob /user Charlie`   
    This command will create a new expense with total amount of SGD 9.00 with a split of:  
        Alice: SGD 3.00, Bob: SGD 3.00, and Charlie: SGD 3.00.


- `expense lunch /amount 10.00 /paid Alice /user Bob /user Charlie`  
    This command will create a new expense with total amount of SGD 10 with a split of:     
        Alice: SGD 3.33, Bob: SGD 3.33, and Charlie: SGD 3.33.

Expenses with unequal split and Expense with different currencies take in the base parameters of Expenses split equally 
plus one additional parameter. All the rules mentioned for equally split expenses still apply.

##### 2. Create expense split unequally

Format:`expense DESCRIPITON /unequal /amount TOTAL_AMOUNT
/paid PAYER_USER_NAME /user USER_NAME AMOUNT_OWED /user USER_NAME AMOUNT_OWED`

- `/unequal` indicates that the expense will be split according to the `AMOUNT_OWED` by each user.
- The payer's amount owed will be automatically calculated by the program and does not have to be inputted.

Examples:
- `expense dinner /unequal /amount 9.00 /paid Alice /user Bob 3 /user Charlie 4`  
  This command will create a new expense with total amount of SGD 9.00 with a split of:  
    Alice: SGD 2.00, Bob: SGD 3.00, and Charlie: SGD 4.00 (Alice's split is automatically calculated)


- `expense dinner /unequal /amount 14.00 /paid Alice /user Bob 5 /user Charlie 6`  
  This command will create a new expense with total amount of SGD 14 with a split of:  
    Alice: SGD 3.00, Bob: SGD 5.00, and Charlie: SGD 6.00 (Alice's split is automatically calculated)

##### 3. Create expense with different currency

Format: `expense DESCRIPTION /currency CURRENCY /amount TOTAL_AMOUNT
/paid PAYER_USER_NAME /user USER_NAME AMOUNT_OWED /user USER_NAME AMOUNT_OWED`

- `/currency` indicates the currency of the transaction.
- The currency has to be from the currency list mentioned [here](#list-of-currencies). 
Otherwise, the app will throw an error.
- If no `/currency` is present in the command, the program will use SGD as the default currency.
- The currency feature can be used with expenses split equally or unequally.
- Only one currency can be entered per transaction.

Examples:
- `expense dinner /currency USD /unequal /amount 9.00 /paid Alice /user Bob 3 /user Charlie 4`  
  This command will create a new expense with total amount of USD 9.00 with a split of:  
  Alice: USD 2.00, Bob: USD 3.00, and Charlie: USD 4.00 (Alice's split is automatically calculated)


- `expense dinner /amount 9.00 /paid Alice /user Bob /user Charlie`  
  This command will create a new expense with total amount of USD 9.00 with a split of:  
  Alice: USD 3.00, Bob: USD 3.00, and Charlie: USD 3.00

<br>

#### List expenses: `list`

Lists all expenses for the current group

Format: `list`
- The indexes shown in front of each expense can be used with `delete expense` to delete an expense (see below).  

<br>

#### Delete an expense: `delete expense`

Deletes an expense based on index specified from list command.

Format: `delete expense LIST_INDEX`
- The `LIST_INDEX` needs to be a valid index from the Expenses list. To check expenses list use the `list` command 
(see above).
- If the `LIST_INDEX` does not exist, an exception will be thrown.

Example: 
- `delete expense 3`  
This command deletes the expense at index 3 on the expense list.
--------------------------------------------------------------------------------------------------------------------
### BALANCE COMMAND

#### Show balance of user: `balance`

Shows list of members the user owes money to.

Format: `balance USER_NAME`

Example: 
```
balance Alice

  /\_/\
 ( ^.^ )
  > ^ <
<----------SUCCESS----------->
User Alice's Balance List:
  Bob : 5.00SGD
  Eve : 10.00SGD
  Eve : -10.00AUD
End of Balance List
<---------------------------->
```

This command will display the balance of the user named Alice.
In this case, Bob owes Alice `5.00SGD`, 
Eve owes Alice `10.00SGD` 
and Alice owes Eve `10.00AUD`

--------------------------------------------------------------------------------------------------------------------
### SETTLE COMMAND

#### Settle expenses: `settle`

Settles the expenses between two users in the group.

Format: `settle USER_NAME1 /user USER_NAME2`

- `USER_NAME1` is the name of the first user.
- `USER_NAME2` is the name of the second user.
- `/user` is a keyword to indicate the start of the second user's name.

Example: `settle Alice /user Bob`

This command will settle the expenses between Alice and Bob, showing what Alice owes Bob.

--------------------------------------------------------------------------------------------------------------------
### LUCK COMMAND

#### Trying your luck: `luck`

Play slots to remove debts

Format: `luck` (Coming soon feature)

- Enters the slot machine
    - `/reroll` to reroll the slots
    - `/exit` to exit the slot machine
    - Example: `/reroll`

This command enable users play slots to remove their debts

--------------------------------------------------------------------------------------------------------------------
### Saving the data

Split-liang automatically saves the data in each group to `GROUP_NAME.txt` in the `data\groups` folder after the application
exits. There is no need to save manually.

The data is loaded automatically when the application starts.

- The data folder is located in the same directory as the jar file.
- The data folder contains the data for each group in a separate text file.
- The data folder is created automatically if it does not exist.
- The data folder can be deleted to clear all data.
- Corrupted data files will be ignored and not loaded.

--------------------------------------------------------------------------------------------------------------------
### Exiting the Application 
#### Saying goodbye: `bye`

This command exits the application.

--------------------------------------------------------------------------------------------------------------------
## List of currencies

### The currencies currently supported by the Expenses feature:

| Currency          | Tag to Enter (This is what should be entered after `/currency`) when creating new expense |
|-------------------|-------------------------------------------------------------------------------------------|
| US Dollar         | USD e.g. `/currency USD`                                                                  |
| Singapore Dollar  | SGD e.g. `/currency SGD`                                                                  |
| Chinese Yuan      | RMB e.g. `/currency RMB`                                                                  |
| Euro              | EUR e.g. `/currency EUR`                                                                  |
| Japanese Yen      | JPY e.g. `/currency JPY`                                                                  |
| Australian Dollar | AUD e.g. `/currency AUD`                                                                  |
| Malaysian Ringgit | MYR e.g. `/currency MYR`                                                                  |

--------------------------------------------------------------------------------------------------------------------

## FAQ

1. **Q: How do I create a new group?**
    - A: To create a new group, use the `create group` command followed by the group name.
2. **Q: How do I transfer my data to another device?**
    - A: You can copy the `data` folder to the new device to transfer your data.
3. **Q: Can I enter a new expense or add a new member without being in a group?**
    - A: No you need to first create a new group or enter an existing one after which you can carry out those actions.

--------------------------------------------------------------------------------------------------------------------

## Command Summary
<br>

| Action           | Format, Examples                                                                                                                           |
|------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| Help             | `help`                                                                                                                                     |
| Create group     | `create GROUP_NAME` <br> e.g. `create Friends`                                                                                             |
| Enter group      | `enter GROUP_NAME` <br> e.g. `enter Friends`                                                                                               |
| Add member       | `member USER_NAME` <br> e.g. `member Alice`                                                                                                |
| Exit group       | `exit GROUP_NAME` <br> e.g. `exit Friends`                                                                                                 |
| Add expense      | `expense DESCRIPTION /amount AMOUNT /paid USER_NAME /user USER_NAME GROUP_NAME` <br> e.g. `expense lunch /amount 20 /paid Alice /user Bob` |
| List expenses    | `list` <br> e.g. `list`                                                                                                                    |
| Delete expense   | `delete expense LIST_INDEX` <br> e.g. `delete expense 3`                                                                                   |
| Balance          | `balance USER_NAME` <br> e.g. `balance Alice`                                                                                              |
| Settle expenses  | `settle USER_NAME1 /user USER_NAME2` <br> e.g. `settle Alice /user Bob`                                                                    |
| Exit application | `bye`                                                                                                                                      |
=======



