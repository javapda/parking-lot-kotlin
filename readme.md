# [parking-lot-kotlin](https://github.com/javapda/parking-lot-kotlin)

## [Stage 5/5](https://hyperskill.org/projects/75/stages/420/implement) : Carspotting
### Description
Now it's time to add some query commands.

The command _**reg_by_color**_ prints all registration numbers of cars of a particular color, 
taking color as a parameter. The color may be written in uppercase or lowercase letters. For 
example, _**reg_by_color BLACK**_. The answer should contain registration numbers separated 
by commas. The order should be the same as in the _**status**_ command. If there are no cars 
of this color, the output should be like this: _**No cars with color BLACK were found.**_.

The command _**spot_by_color**_ is similar to the previous one, but it prints the parking 
space numbers of all the cars of a particular color.

The command _**spot_by_reg**_ returns you the number of the spot where a car is located based 
on its registration number, for example, _**spot_by_reg KA-01**_. This command will also return 
an error message if your car was not found: _**No cars with registration number KA-01 were found.**_ 
For convenience, let's suppose that all car registration numbers are unique.

### Example
The symbol > represents the user input.
```
> spot_by_color yellow
Sorry, a parking lot has not been created.
> create 4
Created a parking lot with 4 spots.
> park KA-01-HH-9999 White
White car parked in spot 1.
> park KA-01-HH-3672 White
White car parked in spot 2.
> park Rs-P-N-21 Red
Red car parked in spot 3.
> park Rs-P-N-22 Red
Red car parked in spot 4.
> reg_by_color GREEN
No cars with color GREEN were found.
> reg_by_color WHITE
KA-01-HH-9999, KA-01-HH-3672
> spot_by_color GREEN
No cars with color GREEN were found.
> spot_by_color red
3, 4
> spot_by_reg ABC
No cars with registration number ABC were found.
> spot_by_reg KA-01-HH-3672
2
> spot_by_reg Rs-P-N-21
3
> exit
```
## [Stage 4/5](https://hyperskill.org/projects/75/stages/419/implement) : Size matters
### Description
In real life, parking lots vary in size. At this stage, we will get better at making art imitate life. 
To do this, we will make our program customizable by adding a *create* command that allows the user to specify 
the number of spots. For example, the command *create 3* makes a parking lot with three spots. The number of 
spots should be positive. The program output should be the following: *Created a parking lot with 3 spots.*

Other commands like *park* or *leave* should return an error *Sorry, a parking lot has not been created.* until the 
user enters the *create* command. If the user calls create again, the previous parking state should be reset.

It is also important to keep track of which spaces are occupied by which cars. For this, add a *status* command 
that prints all occupied spots in ascending order. For each spot, it should print the spot number, the car’s plate 
registration number, and the color of the car, all separated by spaces like the example below. If there are no occupied 
spots, the program should print: Parking lot is empty.

### Example
The symbol > represents the user input.
```
> park KA-01-HH-9999 White
Sorry, a parking lot has not been created.
> create 3
Created a parking lot with 3 spots.
> status
Parking lot is empty.
> park KA-01-HH-9999 White
White car parked in spot 1.
> park KA-01-HH-3672 Green
Green car parked in spot 2.
> park Rs-P-N-21 Red
Red car parked in spot 3.
> leave 2
Spot 2 is free.
> status
1 KA-01-HH-9999 White
3 Rs-P-N-21 Red
> exit
```

## [Stage 3/5](https://hyperskill.org/projects/75/stages/418/implement) : Expand and park
### Description
Two spots are not enough for a parking lot, so let's increase the number of parking spaces. We'll jump straight to 20 spaces, numbered from 1 to 20. Initially, all the spots are vacant.

When the user wants to park the car, the program should find an available parking spot with the lowest number.

The user can write commands park and leave repeatedly and type exit to end the program.

If the parking lot is full and there's no room, the program should type Sorry, the parking lot is full..

If there are several available spots for a car, the program should always assign the spot with the lowest number.

### Example
The symbol > represents the user input. Note that it's not part of the input.
```
> park KA-01-HH-9999 White
White car parked in spot 1.
> park KA-01-HH-3672 Green
Green car parked in spot 2.
...

> park Rs-P-N-21 Red
Sorry, the parking lot is full.
> leave 1
Spot 1 is free.
> park Rs-P-N-21 Red
Red car parked in spot 1.
> exit
```

## [Stage 2/5](https://hyperskill.org/projects/75/stages/417/implement) : Only 2 spots
### Description
At this stage, our minimalistic parking lot has two parking spots. Let's assume that the first spot is occupied and the second one is free.

The parking lot should allow the user to park the car. This is implemented using the park command. After the user has entered this command, the registration number and the color of the car should be specified. For example, park KA-01-HH-1234 Blue. The registration number should not contain spaces. The color can be written in either uppercase or lowercase letters.

As the first spot is already taken, the program should allocate the second spot and print: Blue car parked in spot 2. The color should match what the user inputs.

To pick up the car, the user should print the command leave and then the number of the parking spot, for example, leave 1. If there is no car in the given spot, the program should print an error: There is no car in spot 1. Otherwise, it should notify the user that the spot is now available: Spot 1 is free.

### bad test runs: (NOTE: solution was to break up the readln() in the main() functions while loop)
```courseignore
FEEDBACK:
Error in test #1

Program ran out of input. You tried to read more than expected.

Please find below the output of your program during this failed test.
Note that the '>' character indicates the beginning of the input line.

---

> park KA-01-HH-1234 White
White car parked in spot 2.
```

### Examples
The symbol > represents the user input. Note that it isn't part of the input.

#### Example 1:
```
> park KA-01-HH-1234 Blue
Blue car parked in spot 2.
```
#### Example 2:
```
> leave 1
Spot 1 is free.
```

#### Example 3:

```
> leave 2
There is no car in spot 2.
```

## [Stage 1/5](https://hyperskill.org/projects/75/stages/416/implement) : First steps
### Completed: 11:17pm, 7/6/2024
### Description
Parking lots are an urban necessity. They provide places for you to leave your car without having to worry about it being stolen or towed. Modern car parking systems are automated and are capable of self-management. In this project, you will create a similar system, but in a simplified form.

Let's start by writing a program that can place your car in a parking lot and free the lot when the car leaves. This will be the "skeleton" of our functional parking lot, and we’ll add on to it in the following steps. At busy times, the parking lot may run out of vacant spaces. The program will provide us with aggregated information about the current state of the lot.

In this stage, we'll print the car's color when it parks, leaves, or arrives. We'll get the colors from the console for these lines. For instance, if the car's color is red in each case, the output will be Red car has parked, Red car left the parking lot and Red car just parked here.

### Example
The symbol > represents the user input. Note that it's not part of the input.

Output three lines using the car color input from the console, as shown in the example below.
```
> White
White car has parked.
> Yellow
Yellow car left the parking lot.
> Green
Green car just parked here.
```