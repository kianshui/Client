# Tower Defense Project

##How we make the 'Tower defense game'?
libs-Java AWT(Abstract Window Toolkit)

###First, making the layout in Class Frame by Inheritance.
####About the layout
    class Frame
    -The Frame is the container that contain title bar and can have menu bars.
    -class Frame extends JFrame

    class Screen
        -screen which draw stuff
            -setting graphics(background, images, etc...)
            -draw room,store
        -class Screen extends JPanel
        -The Panel is the container that doesn't contain title bar and menu bars.
        -spawn monster
        -shoting
        -change level

    class Room
        -load up all different level we create
        -we grid which made up of rectangle boxes (8 column x 12 rows)
        -road for monsters to walk and the map

    class Block
        -class Block extends Rectangle
        -shoting

    class Value
        -all the values we used for our drawing

    class Store
        -shop for tower
        -draw player health and coin
   
    class KeyHandel
        -gives us the mouse position
        -class KeyHandel implements MouseMotionListener, MouseListener
   File Save
   -mission layout

    class Save
        -load mission layout file


###After the layout is done.
Then, we make the class of monsters.

    class Mob
        -class Mob extends Rectangle
        -spawn monster
        -monster moves
        -walk according to layout
        -monster health
        -health deduction
        -check if monster died
        -delete monster
        -get Coin


And Shoting

    Shoting
        -build tower with mouse
            -tower price
            -shoting range(rectangle)
        -deduct monster's health
        -gain money when kill

Proceed to next level
    
    check killed monsters with winning condition
        -proceed to next level
        -WIN