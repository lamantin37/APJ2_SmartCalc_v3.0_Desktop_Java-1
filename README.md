# SmartCalc v3.0

Implementation of SmartCalc v3.0 in Java.


## Contents

1. [Chapter I](#chapter-i) \
   1.1. [Introduction](#introduction)
2. [Chapter II](#chapter-ii) \
   2.1. [MVP pattern](#mvp-pattern) \
   2.2. [MVVM pattern](#mvvm-pattern)
3. [Chapter III](#chapter-iii) \
   3.1. [Part 1](#part-1-smartcalc-v30-implementation) \
   3.2. [Part 2](#part-2-bonus-loan-сalculator)\
   3.3. [Part 3](#part-3-bonus-deposit-calculator) \
   3.4. [Part 4](#part-4-bonus-configuration-and-logging) \
   3.5. [Part 5](#part-5-bonus-cross-platform)
4. [Chapter IV](#chapter-iv)


## Chapter I

![SmartCalc_v3.0_Desktop](misc/images/APJ2_SmartCalc_v3.0_Desktop.png)

Chuck was late, so he was in a hurry and couldn't sit still. Only the occasional stern look in the rearview mirror from the cab driver humbled him each time.

"Could you go any faster, I'm in a hurry," Chuck said harshly.

"Young man, I'm going the speed limit here, and I'm not going to break the rules for no reason. Calm down, we're almost there. And you could have left the house sooner..." Chuck skipped further lectures and went back to his phone. Time was running out. 

Buildings, palm trees, and people with dogs flew by the window. California is usually hot this time of year, so many people were sunbathing on their lawns. But Chuck wasn't really interested in that; he glanced at the map from time to time and at the time on his phone, checking how much longer he had to go. He wasn't flying halfway across the country for sun, sand, and relaxation. His mission was to find answers to his questions, and some anonymous person had kindly agreed to provide them. Yes, the text message from him wasn't very informative: just an address, a time, and a promise of answers, but Chuck felt that he was sure to get help here. He got as close to the truth as he could.
The taxi pulled up to a small square where three people were standing. Chuck's head was spinning, "I hope I'm not too late, I hope I'm not too late. He shot out of the cab and dropped his bag on the ground. The driver shouted something at him, but just then another familiar voice caught Chuck's attention:

"Chuck?! And you're here too?" Eve said slightly surprised.

"Oh, hi, Eve! What a faraway place to meet. How small the world is! And what do you mean, here? Did you get that weird text message too?" Chuck seemed out of breath, constantly looking around for either potential danger or something interesting. "I hope I'm not too late."

"We all got that text," John interjected. "I'm John, this is Thomas."

"I'm Chuck. I never thought it would be such a mysterious meeting in the middle of nowhere," Chuck replied, looking around.

"It's not exactly nowhere, though," Thomas replied. "Until a few months ago, there was a nice, lively jazz club here, owned by the father of a good friend of mine. Who, it turns out, was an acquaintance of John's. He was a colleague of yours, wasn't he? Except that neither John nor I had heard from him in over three months. And the club... Well, you can see how it turned out," Thomas looked sadly at the building.

"Where did you work?" Eve asked John.

"A local branch of SIS," John murmured back. Administering network applications and configuring computer hardware."

"Chuck and I are also from SIS. Different departments and the Eastern Division, but still," Eve said thoughtfully. "Where do you work, Thomas?

"Advanced Solutions Inc. Subsidiary of SIS, recently transferred there. I had to take a leave of absence to come here, but I was seriously worried about Seb, and apparently not for nothing. Turns out we're all connected to SIS in one way or another."

"And not just that, right, Eve? Chuck said. I've got some documents here that I think you might find interesting, so where are they..." Who knows where this conversation would have gone if not for the simultaneous beeping and vibrating of the smartphones in everyone's pockets.

> Greetings to all! I'm very pleased that you were interested and were able to arrive on time. You are gathered here for a reason, and you will indeed get all the answers. But only after a small test. There is one task that you like to test me on. But now it's time to trade places. Prove that you are ready and able to handle the tasks ahead of you, and then I will answer all your questions. A test with details is already waiting for you in your personal repositories. Please begin immediately. Thank you.

Chuck couldn't keep a small smile from his face and whispered only one word:

"The Terminator..."

## Introduction

In this project, you will implement an extended version of the common calculator in the Java programming language that implements the same functionality as the application previously developed in the SmartCalc v2.0 project. You will hone your skills in the new programming language, learn the MVP or MVVM pattern, and add help and history functionality to the application.

## Chapter II

### MVP pattern

The MVP pattern shares two components with MVC: the model and the view. However, it replaces the controller with a presenter.

The presenter implements the interaction between the model and the view.
When the view notifies the presenter that the user has done something (such as pressed a button), the presenter decides to update the model and synchronizes any changes between the model and the view. However, the presenter does not interact directly with the view. Instead, it uses an interface to communicate. This allows all components of the application to be tested individually afterwards.

![](misc/images/MVP-Process.png)

### MVVM pattern

MVVM is a more modern update of MVC. The main purpose of MVVM is to provide a clear separation between the presentation and model layers. MVVM supports bi-directional data binding between View and View Model components.

The View acts as a subscriber to property value change events provided by the View Model. When a property has changed in the View Model, it notifies all subscribers, and the View in turn requests the updated property value from the View Model. When the user interacts with an UI element, the View calls the appropriate command provided by the View Model.

A View Model is both an abstraction of a View and a wrapper of data from the Model to be bound. In other words, it contains the Model transformed into the View, as well as the commands that the View can use to affect the Model.

![](misc/images/MVVM-Process.png)


## Chapter III

## Part 1. SmartCalc v3.0 implementation

You need to implement SmartCalc v3.0:

- The program must be developed in Java 8.
- The program code must be located in the src folder.
- You must stick to Google Code Style when writing code.
- You need to develop a desktop application.
- Prepare the installer, which will install the application to the system with the standard settings (installation path, creating a shortcut).
- Prepare an implementation with a graphical user interface for either Linux or Mac OS, based on any GUI library or framework (GUI layer implementation in HTML/CSS/JS is acceptable).
- The program must be implemented using the MVVM or MVP pattern, and:
   - there should be no business logic code in the view code;
   - there must be no interface code in the model, presenter and view model.
- The "core" of the calculator in the form of an algorithm for the formation and calculation of the Polish notation and various computational functions connect as a dynamic library in C/C++ from the SmartCalc v1.0 or SmartCalc v2.0 projects.
- The model should be a "core" with a wrapper in Java.
- The model must have the full functionality of the calculator so that it can be used in the future without the other layers.
- Prepare full coverage of the methods in the model layer with unit tests.
- The application should have a help section with a description of the program interface in random form.
- The program must store the history of operations, allow loading expressions from the history, and clear the entire history.
- The history must be saved between runs of the application.
- Both integers and real numbers can be entered into the program, written either in point or exponential form.
- The calculation should be performed after the complete input of the calculated expression and pressing the `=` symbol.
- Calculation of arbitrary bracketed arithmetic expressions in infix notation.
- Calculate arbitrary parenthesized arithmetic expressions in infix notation with substitution of the _x_ variable as a number.
- Plotting a function defined by an expression in infix notation with the variable _x_ (with coordinate axes, scale markers, and grid with adaptive step):
  - It is not necessary to allow the user to change the scale.
- The definition range and the value range of the functions are limited at least to numbers from -1000000 to 1000000.
- To plot a function, it is also necessary to specify the displayed definition and value ranges.
- The checked accuracy of the fractional part is at least 7 decimal places.
- The user must be able to enter up to 255 characters.
- Bracketed arithmetic expressions in infix notation shall support the following arithmetic operations and mathematical functions:
   - **Arithmetic operators**:

      | Operator name | Infix Notation <br />(Classic) | Prefix notation <br /> (Polish notation) |  Postfix notation <br />(Reverse Polish notation) |
      | ------ | ------ | ------ | ------ |
      | Parentheses | (a + b) | (+ a b) | a b + |
      | Addition | a + b | + a b | a b + |
      | Subtraction | a - b | - a b | a b - |
      | Multiplication | a * b | * a b | a b * |
      | Division| a / b | / a b | a b \ |
      | Rasing to the power | a ^ b | ^ a b | a b ^ |
      | Remainder of division | a mod b | mod a b | a b mod |
      | Unary plus | +a | +a | a+ |
      | Unary minus | -a | -a | a- |

      >Please note that the multiplication operator contains a mandatory `*` sign. Processing an expression with the `*` sign omitted is optional and left to the developer's discretion.

      | Function description | Function |   
      | ---------------- | ------- |  
      | Calculates cosine | cos(x) |   
      | Calculates sine | sin(x) |  
      | Calculates tangent | tan(x) |  
      | Calculates arc cosine | acos(x) | 
      | Calculates the arcsine | asin(x) | 
      | Calculates arctangent | atan(x) |
      | Calculates square root | sqrt(x) |
      | Calculates natural logarithm | ln(x) | 
      | Calculates decimal logarithm | log(x) |

## Part 2. Bonus. Loan сalculator

Provide a special mode "loan calculator" (you can take websites like banki.ru and calcus.ru as an example):
- Input: total loan amount, term, interest rate, type (annuity, differentiated);
- Output: monthly payment, overpayment for the loan, total repayment.

## Part 3. Bonus. Deposit calculator

Provide a special mode "deposit calculator" (you can take websites like banki.ru and calcus.ru as an example):
- Input: deposit amount, deposit term, interest rate, tax rate, periodicity of payments, capitalization of interest, list of additions, list of partial withdrawals;
- Output: accrued interest, tax amount, amount on deposit by the end of the term.

## Part 4. Bonus. Configuration and logging

Add settings to the application:
- Add reading of settings from configuration file when the program runs.
- Include in the configuration file 3 or more parameters to choose from, such as background color, font size, etc.
- Add descriptions of editable parameters to help.

Add logging to the application:
- Store operation history in logs.
- Save logs in the logs folder, one file per rotation period.
- It should be possible to set the period of logs rotation (hour/day/month).
- Files must be named according to the following pattern: `logs_dd-MM-yy-hh-mm-ss` (the time of file creation).

## Part 5. Bonus. Cross-platform

Make your application cross-platform:
- Add support Linux, Mac and Windows.
- The installer should also be available for Linux, Mac and Windows (several different installers are allowed).


## Chapter IV

Chuck finished the calculator without too much trouble. He had learned Java in college, so he quickly put together a simple desktop application. The other guys seemed to be finishing their work as well. \
As soon as everyone was done, new messages from an anonymous person appeared on their phones:

> Thank you. I see you all did well. That's great, even though the algorithms predicted it all along. Please establish a secure connection to the server specified in the following message and connect to the chat room specified. There we will be able to talk freely and calmly. I also have some special information for Thomas and John regarding your friend Seb. See you in the chat room!

💡 [Tap here](https://forms.yandex.ru/cloud/6418259f73cee70c7447898c/) **to leave your feedback on the project**. Product Team really tries to make your educational experience better.
