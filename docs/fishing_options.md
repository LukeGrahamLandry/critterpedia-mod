## ABOUT

when you use a fishing rods from critterpedia work like vanilla rods 
except that once a fish bites and you real it in, you trigger a gui minigame.  

mods and datapacks can add new options for things to catch through this system.  

## DATA 

data structure for new fishing

loaded from datapack `fishing` directory 

each file is a fishing option 

### rod
 
list of which rod types can catch this fish

default options: `["critterpedia:small_rod", "critterpedia:large_rod"]`

*required*

### critter

the resource location of the critter type to add to the critterpedia gui when you catch this fishing option. 

*optional*, defaults to the key of this file. ie, `pack_modid:file_name`

### item

the resource location of the item to give the player when they successfully catch this fishing option.  

*required*

### rarity 

how likely this option is to happen and how hard it is to catch.

*required*  
default options are `common`, `uncommon`, `rare` but mods can add their own

defines the following values: 

- weight: how likely this option is to be chosen
- color: colour of the bar in the gui
- size: size of the bar in the gui
- time: how long the mini-game lasts
- speed: how far the fish icon can move at a time
- activity: how often the fish icon moves
- decay: how quickly you lose progress when fish not in bar

> see default numbers for these in base.api.FishingRarity#createDefaults
> TODO: add units


