# [parking-lot-kotlin](https://github.com/javapda/parking-lot-kotlin)

## [Stage 2/5](https://hyperskill.org/projects/75/stages/417/implement) : Only 2 spots
### Description
At this stage, our minimalistic parking lot has two parking spots. Let's assume that the first spot is occupied and the second one is free.

The parking lot should allow the user to park the car. This is implemented using the park command. After the user has entered this command, the registration number and the color of the car should be specified. For example, park KA-01-HH-1234 Blue. The registration number should not contain spaces. The color can be written in either uppercase or lowercase letters.

As the first spot is already taken, the program should allocate the second spot and print: Blue car parked in spot 2. The color should match what the user inputs.

To pick up the car, the user should print the command leave and then the number of the parking spot, for example, leave 1. If there is no car in the given spot, the program should print an error: There is no car in spot 1. Otherwise, it should notify the user that the spot is now available: Spot 1 is free.

### bad test runs:
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