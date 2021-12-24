## The Plan 

register entity types normally 

CritterType
- retrieved based on resource location of the entity
- the resource location of the slot in the critterpedia
- the critter category: insect, bird, fish, etc, each is a different tab in the menu. this should be a static string so other mods can add them
- pickup type. default based on category. this should be a static string so other mods can add them
- object that implements DisplayCritterData with extra info to show in the gui. have drawInCritterpedia method
    - will normally be an instance of the same class for the whole category (each with different stats) but doesn't have to be. 
    - if unset, will check if the entity implements it
    - example stats: which biome they spawn in. beetle's randomness / speed?
- list of different subtypes, this will just be the resource location of the entity type

so like 
- id: critters:butterfly
- category: critters:bug
- pickup type: critters:net
- subtype: critters:yellow_butterfly 
it has to be easy to add a new subtype to an existing type

hashmap of subtypes (entity type rl) to critter type id rl 
so you can getCritterFor(EntityType) -> CritterType

world data should save for each player a Map<CritterTypeRL> -> List<SubTypeRL> for all caught to show in critterpedia
then just add to that whenever someone picks up something that has a CritterType.
world data just to be safe so it cant get to big by accident and "book ban" people

mod compatibility 
- listen for fmlcommonsetupevent and Critters.register(CritterPlugin)
- implements CritterPlugin
    - getID(), will generally be the mod id. can be disabled by config
    - getCritters() returns list of new CritterType(entity.name.get(), category))
    - getCategories() returns list of CritterCategory
- structure the mod so all my entities are just plugins to the main api so its an obvious example for other people
- make a tutorial on how to make plugins

mixin to buckets that checks pickup type 
other pickup items: box, net only vary by type they check for

make sure to do all ai as goals so it can easily handle lots of reskinned versions of the same entity 

packages
- base: CritterType, CritterCategory, DisplayCritterData, CritterPlugin, PickupType. the gui, mixins that make the buckets work
- content: all the entities and stuff
- plugins: vanilla?

GUI
- figure out scroll bars or just have buttons
- search bar?
- tabs