Tower Defense Project

How we make the 'Tower defense game'?
-Java AWT(Abstract Window Toolkit)

First, making the layout in Class Frame by Inheritance.
About the layout
    class Frame
    -The Frame is the container that contain title bar and can have menu bars.
    -class Frame extends JFrame

    class Screen
    -screen which draw stuff
        -setting graphics(background, images, etc...)
        -draw room,store
    -class Screen extends JPanel
    -The Panel is the container that doesn't contain title bar and menu bars.

    class Room
    -load up all different level we create
    -we grid which made up of rectangle boxes (8 column x 12 rows)
    -road for monsters to walk and the map

    class Block
    -class Block extends Rectangle

    class Value
    -all the values we used for our drawing

   File Save
   -mission layout

    class Save
    -load mission layout file

    class Store
    -shop for tower

    class KeyHandel
    -gives us the mouse position
    -class KeyHandel implements MouseMotionListener, MouseListener


Then, we make the monsters
About the monsters
    class Mob
    -


Last, we make shooting and multiple level