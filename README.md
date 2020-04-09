#### Yap Kian San 18001106   Computer Engineering
#### Yap Kian Shui 18001084  Computer Engineering
# OOP Project
# Tower Defense Project

### Reference: 

    1. lab tutorial project 'karatheladybug'. (https://github.com/arwankhoiruddin/karatheladybug)
    2. Java AWT Tutorial. (https://www.youtube.com/watch?v=VuGJO47Ommg&list=PLXpDKtDlsapicH6agKynUCCRbqkbtGG7s)
    3. Java Game Development. (https://www.youtube.com/watch?v=hBhAWTSu104&list=PLA331A6709F40B79D)
    4. tower defense tutorial. (https://www.youtube.com/watch?v=Euiqdd09n68)
    5. for monster finding path (https://github.com/arwankhoiruddin/brick-path)
    6. 2D Tower Defense Tutorial. (https://www.youtube.com/watch?v=uwp-xW0kylc)
  
#### What's new in our project:

### Solved bugs
    1. The coin increase is corrected when the monster is being shot and killed by multiple towers.
    2. The monsters spawned exceeded the winning condition.(infinite spawning)

### Added changes
    1. The game can count the correct kill amount of monster.
    2. The coin increase after killing monster is correct.
    3. The monsters can walk correctly according to the layout.
    4. The monsters are spawned according to winning condition.
    5. Blood effect after killing monster.



## How we make the 'Tower defense game'?

#### First, making the layout in Class Frame by Inheritance.
#### About the layout
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


### After the layout is done.
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