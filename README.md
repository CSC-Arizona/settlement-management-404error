# final-project-start

This is the GitHub repo we will use to store our CSC 335 Final Project, Fall 2016

Team Name:404 Error

Project (Risk, Pokemon, Settlement Management, or MUD): Settlement Management

Project Manager: Cody


All four team members make a commit that modifies this file with your name added below this line (leave this line here)

1) Jonathon Davis

2) Ethan Ward 

3) Katherine Walters

4) Maxwell Faridian


addToMap: isAdjacentTo


Task Sequence for Creating a Room:
    check isAdjacentTo an accessible path
    check amount of freeAdjacentLand >= chosen room.getRequiredSpace
         freeAdjacent land could be defined as any destroyable block. 
         Agent destroys the block and gathers the dropped items in the process of building the room.
	check chosenRoom.getRequiredMaterials (which checks ea furniture pieces required materials)
	gather required materials from the store room if not in inventory
	build room