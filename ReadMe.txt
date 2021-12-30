Note:
1. This is a JDK 16-based java application

Important!
2. Set your JavaCC STATIC option to false, otherwise you will have to rerun the program in order to try a new function.
3. This application is capable of plotting up to the 10th polynomial of every function. The limitation is due to the 
low computing power of the PC I implemented the app on. To have the app work beyond the 10th polynomial, follow the steps below:
a. Navigate into the GraphApp module
b. Open the src folder
c. Open the AppFrame.java file
d. On line 40, change the value of the variable "N" to the maximum polynomial value you wish to plot.

Features of the App:
1. f(x) is the function to be plotted
2. X0 is the point at which the series expansion of the function is taken.
3. The 'plot' button plots the function f(x)
4. The '>' button plots the next polynomial
5. The '<' button plots the previous polynomial
6. The 'plot over graph' check box plots the polynomials on top of each other when checked, otherwise plots the current polynomial
7. The 'Px' label represents the current polynomial being plotted.

How to use this App:
a. Navigate into the GraphApp module
b. Open the src folder
c. Run Main.Java
There you go! Good luck!