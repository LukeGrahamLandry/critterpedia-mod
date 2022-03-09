## ABOUT

when you use a fishing rods from critterpedia work like vanilla rods 
except that once a fish bites and you real it in, you trigger a gui minigame.  

mods and datapacks can add new options for things to catch through this system.  

## Fishing Options 

data structure for new fishing

loaded from datapack `fishing` directory 

each file is a fishing option 

### rod
 
list of which rod types can catch this fish

default options: `["critterpedia:small_rod", "critterpedia:large_rod"]`

*required*

### critter

the resource location of the critter type to add to the critterpedia gui when you catch this fishing option. 

*optional*, defaults to the key of this file (`NAMESPACE:FILENAME`)

### item

the resource location of the item to give the player when they successfully catch this fishing option.  

*optional*, defaults to `NAMESPACE:live_FILENAME`

### rarity 

how likely this option is to happen and how hard it is to catch.

*required*  
default options are `common`, `uncommon`, `rare` but mods can add their own

## Rarities

loaded from datapack `fishingrarity` directory

each file is a rarity that can be used from fishing options

units:
- fractions are represented as decimal numbers between 0 and 1
- distances are fractions of the rod
- speeds are fractions of the rod per tick
- acceleration is increase to speed per tick
- progress is a fraction of the total progress

defines the following values:
- weight: how likely it is to be caught
- barSize: length of the bar you control 
- color: colour of the bar in the gui (UNIMPLIMENTED)
- fishSpeed: how fast the fish moves 
- fishMoveChance: fraction of how likely the fish is to start moving each tick it is still
- barGravity: how much the bar accelerates downward
- progressGainRate: how much progress you gain per tick while the bar is on the fish 
- progressLossRate: how much progress you lose per tick while the bar is not on the fish
- barForce: how much speed to add to the bar when you press space 

