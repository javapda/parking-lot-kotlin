# [parking-lot-kotlin](https://github.com/javapda/parking-lot-kotlin)

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