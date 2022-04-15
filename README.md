# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Fall 2022 Edition!

**Name:** Lillith Chute and Donglin Xu

**Email:** chute.l@northeastern.edu

**Preferred Name:** Lillith and Donglin

**Updated:** April 14, 2022


### About/Overview

This program is about the development of a world that will eventually become part of a game.  This world will be loosely based 
upon a game called Kill Doctor Lucky, which has several iterations.  The world basically comprises a 
two-dimensional grid.  On this grid is laid out a series of spaces that make up the world or board.

In this world there is a target.  This target moves through the spaces one at a time in sequence.  
In addition to the target that moves through the spaces, there are items in this world.  
These items are located in the spaces.  Items are used to attack the target.  There is also a pet in the world.  
This pet is used to make what is inside a room invisible.  A space can have one or more items or none at all.  There are also players both 
human and computer that can interact in the world.  Any number of players can be added.  Players have six actions they can perform 
as listed in the features section.



### List of Features

The program can perform the following:
1. Given a properly formatted world building file, the program will read it and create a world.
2. Given the name or index of any given space, the program will find all the spaces that share a wall (neighbors) and report them.
3. The program, given the index or name of a space, will return a full description of that space.  This description will include the name of the space, all the items contained in the space, and all the neighboring spaces.
4. You can move the target through the world.  Moving the target moves it in the index order of the spaces.  If the target reaches the last space the target will return to the first space.
5. The program can create a file of the image.  It has a displayBoard function that creates a BufferedImage and writes it to disk.
6. The program can return the location of the target at any point in time.
7. It can return the name of any given space by its index.
8. It can return a list of all the space contained in the world.
9. Set the number of turns that game will use before ending
10. Alternately you can enter 'quit' to end the game early. 
11. It can add players, either human controlled or computer controlled, to the world.
12. Computer players will automatically take their turns and pass the turn to the next player.
13. Players can do the following actions: 
    1. Move to a neighboring space and sets the turn to the next player.
    2. Pick up an item in the space and sets the turn to the next player.
    3. Look around the space (see neighboring spaces, target if it is there, items, other players, etc.) and sets turn to the next player.
    4. Looks at the player to see where they are and what they are doing and what they are carrying and sets turn to next player.
    5. Attack the target with an item that they are carrying or if they have no items they can poke the target in the eye for one point of damage.
    6. Move the pet from any space to any space in the world.


### How to Run
MileStone 1:
The JAR file is called WorldBuilder.jar.  The only parameter it requires is a fully qualified path and filename for the properly formatted world building file.
An example usage using windows would be to open a command window.  Navigate to the directory 
containing the jar file.  Use the following command:

java -jar WorldBuilder.jar {filename of world building file}

i.e. for me using the Suicide Squad world:

java -jar WorldBuilder.jar CortoMaltese.txt

MileStone 2:
The JAR file is called MileStone2.jar.  The only parameter it requires is a fully qualified path and filename for the properly formatted world building file.
An example usage using windows would be to open a command window.  Navigate to the directory
containing the jar file.  Use the following command:

java -jar MileStone2.jar {filename of world building file}

i.e. for me using the Suicide Squad world:

java -jar MileStone2.jar CortoMaltese.txt

MileStone 3:
The JAR file is called MileStone3.jar.  The only parameter it requires is a fully qualified path and filename for the properly formatted world building file.
An example usage using windows would be to open a command window.  Navigate to the directory
containing the jar file.  Use the following command:

java -jar MileStone3.jar {filename of world building file}

i.e. for me using the Suicide Squad world:

java -jar MileStone3.jar CortoMaltese.txt

### How to Use the Program

MileStone 1:
The program is currently not interactive.  You give it a file, and it will return a prearranged sequence.  
The sequence of data that is returned is a demonstration of all the functions that the program provides.  

MileStone 2:
The program is now interactive.  Example runs are provided in the /res folder to help guide in the programs use.
The following is a general guideline for using the program.

1. The program will prompt you with some basic instruction.  You first will enter the maximum allowable turns the game will have before ending.
2. Next, you enter all the players that will participate in the game
   1. Type human for a human controlled player, entering name, starting point, and number of items they can carry
   2. Type computer for a computer controlled player, entering name, starting point, and number of items they can carry
   3. When finished entering type 'done'
3. The game begins at this point with the following commands that can be used.  The game will continue until the player types 'quit' or the maximum allowed turns is reached.
    1. Pick up item:  Then type in the name of the item and is a turn
    2. look around: Will provide detailed information about the immediate environment and is a turn
    3. move : Type in the name of the space to move to and is a turn
    4. Describing a player: Detailed description of the player and is a turn 
    5. Describing a space: Describes any space in the world.  Is not a turn and a legacy command from milestone 1
    6. Saving an image of the world to disk: Saves an image of the world to disk.  From milestone 1.

### Example Runs

MileStone 1:
I have the following as example runs of the program at milestone 1 in the /res folder:
1. The first run is contained in RunOfMansionWorld.txt.  This run is based off a world building file provided for the class.  It has a world called Doctor Lucky's Mansion, with 21 spaces, and 20 items.  The run checks the target location, gets the names of locations the target enters, gets neighboring spaces, descriptions, and finally generates and save an image of the world to disk.
2. The second run is contained in RunOfCortoMalteseWorld.txt.  This run is based off a world building file I created which is, in turn, based of a movie called The Suicide Squad.  It has a world called Corto Maltese, with 9 spaces, and 25 implements of destruction.  The run checks the target location, gets the names of locations the target enters, gets neighboring spaces, descriptions, and finally generates and save an image of the world to disk.
List any example runs that you have in res/ directory and provide a description of what each example represents or does. Make sure that your example runs are provided as *plain text files*.

MileStone 2:
I have the following as an example run of milestone 2 in the /res folder:
The run is contained in Milestone2ExampleRun.txt.  This run is based off a world building file provided for the class.  
The run demonstrates the following:
1. adding a human-controlled player to the world
2. adding a computer-controlled player to the world
3. the player moving around the world
4. the player picking up an item
5. the player looking around
6. taking turns between multiple players
7. displaying the description of a specific player
8. displaying information about a specific space in the world
9. creating and saving a graphical representation of the world map to the current directory
10. demonstrates the game ending after reaching the maximum number of turns

MileStone 3:
I have the following as example runs of milestone 3 in the /res folder:
The runs are contained in 
* BotKillsTargetMilestone3.txt
* PlayerKillsTargetMilestone3.txt
* PlayerVersusPuterAndPuterWon.txt
* TargetEscapesMilestone3.txt
    
These runs are based off a world building file provided for the class.  
The runs demonstrate the following:
1. adding a human-controlled player to the world
2. adding a computer-controlled player to the world
3. the player moving around the world
4. the player picking up an item
5. the player looking around
6. taking turns between multiple players
7. displaying the description of a specific player
8. demonstrates the game ending after reaching the maximum number of turns
9. The target character's pet effect on the visibility of a space from neighboring spaces
10. the player moving the target character's pet
11. a human-player making an attempt on the target character's life
12. a computer-controlled player making an attempt on the target character's life
13. a human-player winning the game by killing the target character
14. a computer-controlled player winning the game by killing the target character
15. the target character escaping with his life and the game ending

### Design/Model Changes

Version 1 changes:
1. Changed name of getIndexOfItem method to getSpaceIndexOfItem
2. Realized that I would need to know the maximum number of spaces for the target.  I didn't need to know the or need to set the current space location because the target always starts at index 0 and goes until it returns to the beginning again.
3. In the Space interface 
   1. the getItems needed to have the list of all items passed in.
   2. getNeighbors needed to have all the spaces passed in.
   3. Added methods to return the upper left x and y as well as the lower right x and y.
   4. Added method to check if target is in the space
4. Space implementation
   1. made all the private variables final.
   2. added private methods that are related.  One checks all the coordinates of the two spaces it then passes them to a method to check if the lines intersect.  If so, they are neighbors
5. Added a helper class called InstanceBuilder which does what it says.  YOu pass the parameters required to build an instance of one of the classes, and it hands back an instance.
6. BuildWorld Interface and Implementation underwent a significant change.  Too numerous to list here, but is reflected in the design document.

Milestone 2 Changes:
1. Changed displayBoard method name to printWorldImageToDisk to better reflect what's happening.
2. Added Player interface and concrete implementation.  The player will now be able to interact in the world.
3. Added a controller interface and controller class as I work toward a full MVC implementation.
4. Adding in the Command Pattern.  This will encapsulate the actions/behaviors that can happen in the world like:
   1. Picking up an item
   2. Looking around
   3. moving a player
   4. Describing a player
   5. Describing a space
   6. Adding a human player
   7. Adding a computer player
   8. Saving an image of the world to disk
5. Added all the additional player behaviors into the GameManager.  The player isn't aware of nor tracks stuff such as items, other players, whose turn it is and so forth.  So, the following behaviors have been added:
   1. Moving a player: Moves a player to a neighboring space and sets the turn to the next player.
   2. Pick up an item: Picks up the item in the space and sets the turn to the next player.
   3. Look around: Looks around the space and sets turn to the next player.
   4. Describe player: Looks at the player to see where they are and what they are doing and sets turn to next player.

Milestone 3 Changes:

First, it would be difficult do document all the changes because I threw out most of my design and fundamentally started from scratch 
for this milestone.  I didn't like the fact that everything was being done at the game level and the player was not the 
one doing the actions even though the player should be the one doing so.  So, I started over.  Fundamentally, all the actions 
that were once contained in the WorldImpl that a player might make are now with the player.

1. Redesigned the following:
   1. Player
   2. Space
   3. World
2. Added Pet interface and concrete implementation.  The player will now be able to interact in the world.
3. Added to the Command Pattern.  
    1. Attack
    2. Move Pet
4. The player now performs the actions:
    1. Look around
    2. Describe Player
    3. Pick up item
    4. Move
    5. Attack
    6. Move Pet
5. Attacking is now a part of the game.  As long as the player can't be seen, they can attack the target with an item they are carrying or poke them in the eye if they have nothing.  There are the following constrains:
   1. The target must be in the same room
   2. The attacking player cannot be seen by another player.  This means:
      1. Another player is not in the same space
      2. Another player is not in a neighboring space
6. A pet is added to the game.  The pet can be moved from any space to any space by a player.  The pet makes the contents of a room invisible.  So, if a player is in a neighboring space to the pet, they cannot see into the room with the pet.  Can't see items, other players, etc.
7. Look around has been updated to include:
   1. The pet information
   2. Other players in the room
   3. Neighboring spaces to include the following about neighboring spaces:
      1. Players
      2. items
      3. Pet
      4. Target
8. At the start of every turn player's get some basic information so that they know where they are and stuff around them.
9. The computer player is a little smarter in that they won't simply choose the first space in a neighboring space list or the first item in the list.  These are also randomized now.
10. Items have damage and become evidence.
11. Neighbors are now calculated at the reading of the world building file and stored in a list inside each space.
12. Made my Corto Maltese Suicide Squad world more rich.

### Assumptions

MileStone 1:
1. There can be only one Target during the course of the game.
2. Target's name does not change.
3. Items are set in their locations at the beginning of the game and don't move.
4. Item's name and damage do not change.
5. Space's names and attributes cannot change.
6. There can be more than one space with the same name.  In my world there can be multiple guard houses.
7. The only thing that can change at this point is the target's location on the map.

MileStone 2:
1. There can be only one Target during the course of the game.
2. Target's name does not change.
3. Target moves with each action that is a 'turn'.
   1. Target moves in order of the index of the spaces starting with 0.
4. Items are set in their locations at the beginning of the game.
5. Item's name and damage do not change.
6. Items can now be picked up by a player and move with them
7. Space's names and attributes cannot change.
8. There can be more than one space with the same name.  In my world there can be multiple guard houses.
9. There can be unlimited number of players.
10. Players are added at the beginning of the game and after the game begins no players can be added.
11. Players and items:
    1. Players can pick up items in the space they are in.
    2. If an item has been picked up by another player, that item will not be visible and cannot be picked up
    3. Once a player picks up an item they can't drop it.
    4. Players have a max limit of items they can carry and can't carry more than that.
12. Players and spaces
    1. Players can move but only to adjoining spaces.  
13. The only thing that can change at this point is the target's location on the map.

Milestone 3
1. Target location information is included at the start of every player's turn so, they know where he is as would be noted on a game board.
2. Pet makes the contents of a room invisible but not the room itself.  Thus, a player knows that room might be a neighbor but nothing else about it.
3. If a player fails because they make a typo or try to move to a wrong room that doesn't exist, they get to retry their turn
   1. This doesn't apply to a failed attack which is a legitimate attack but, they are seen.
4. If a player uses an item as an attack it is removed from game play.  This will free up an inventory slot.
5. The pet only moves when a player moves it.
6. Computer player can do all the same actions as a human player
7. The computer player will ALWAYS choose to attack if they cannot be seen.  They will also ALWAYS choose the item with the highest damage.
8. The amount of damage an item does is not displayed to the player adding some chance to the game.

### Limitations

MileStone 1:
1. The way that the program computes neighboring spaces is checking for intersecting lines.  This leads to some difficulty in figuring out how to tell if spaces overlap.  So, currently the application assumes that the buildings will not overlap.
2. I have been unable to figure our how to work with the graphics classes so the images tend to render small and are a little off, and I have not been able to figure out why.  There has been very thorough testing of the spaces functionality and all pass, so I am assuming the issue is not understanding how to work with the library. 

MileStone 2:
1. The limitations above still stand.  I haven't had a chance to get to those yet.
2. The way I set up the Player creates a pseudo-issue with testing.  The Player model is just a simple class that holds information about that player.  However, the game manager is what creates the behaviors.  So, for a computer player, it is the same as a human player just with a flag set.  The manager is the model that randomly decides what a computer player will do.  So, no way to force that into a known testing result without adding testing related methods to the interface.  However, the actions of a computer player are EXACTLY the same as a human player and they have all been thoroughly tested.
3. This is not a limitation per se, but I am unhappy with the way that the GameManager is doing all the work here.  It makes me wonder if there is a better, more efficient way to handle this.

Milestone 3:
1. The way that the program computes neighboring spaces is checking for intersecting lines.  This leads to some difficulty in figuring out how to tell if spaces overlap.  So, currently the application assumes that the buildings will not overlap.
2. I have been unable to figure our how to work with the graphics classes so the images tend to render small and are a little off, and I have not been able to figure out why.  There has been very thorough testing of the spaces functionality and all pass, so I am assuming the issue is not understanding how to work with the library.
3. The way I set up the Player creates a pseudo-issue with testing.  The Player model is just a simple class that holds information about that player.  However, the game manager is what creates the behaviors.  So, for a computer player, it is the same as a human player just with a flag set.  The manager is the model that randomly decides what a computer player will do.  So, no way to force that into a known testing result without adding testing related methods to the interface.  However, the actions of a computer player are EXACTLY the same as a human player and they have all been thoroughly tested.
4. I did not implement the DFS for the pet because I completely redid the design of the game during milestone 3.

### Citations

Not applicable.



