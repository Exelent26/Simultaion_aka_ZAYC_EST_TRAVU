/*
        while (targetCount != 0) {
            for (int i = 0; i < main.World.creatures.size(); i++) {
                Creature creature = (Creature) main.World.creatures.get(i);
                path = worldBFS.pathfinder(creature, world);
                if (!path.isEmpty()) {
                    for (int j = 0; j < path.size()-1; j++) {
                        world.moveEntity(path.get(j), path.get(j + 1), creature);
                        WordRender.worldRender(world);
                        System.out.println();
                    }
                    path.clear();
                    targetCount--;

                    if (targetCount < 2) {
                        world.setEntity(world.makeRandomPositionForEntity(), new Grass());
                    }
                } else {
                    // Если пути к текущей траве нет, травоядное перемещается случайно
                    main.utils.Coordinates newPosition = creature.makeCoordinatesForRandomMovement(creature.coordinates,world);
                    world.moveEntity(creature.coordinates, newPosition, creature);
                    WordRender.worldRender(world);
                    System.out.println();
                }

            }

        }
*/
// TODO: Временное решение для создания пути и охоты за травой и зайцами
/*int grassQuantity = world.getEntitiesOfType(Grass.class).size();


        while (grassQuantity != 0) {
            for (Creature creature : World.creatures) {
                if (creature instanceof Herbivore) {

                    path = worldBFS.pathfinder(creature,world);
                    if (!path.isEmpty()) {
                        Coordinates nextStep = path.get(1);
                        if (world.getEntity(nextStep) != null && world.getEntity(nextStep).getClass() == Grass.class) {
                            grassQuantity--;
                            world.removeEntity(nextStep);
                            System.out.println(grassQuantity);
                        }
                        world.moveEntity(path.get(0), nextStep, creature);

                    }else {
                        break;
                    }
                }
            }
            WordRender.worldRender(world);
            System.out.println();

        }
        int herbivoreQuantity = world.getEntitiesOfType(Herbivore.class).size();
        while (herbivoreQuantity != 0) {
            for (Creature creature : World.predator) {
                if (creature instanceof Predator) {

                    path = improvedBfsPathFinder.findPath(creature.coordinates,creature.getFood());
                    if (!path.isEmpty()) {
                        Coordinates nextStep = path.get(1);
                        if (world.getEntity(nextStep) != null && world.getEntity(nextStep).getClass() == Herbivore.class) {
                            herbivoreQuantity--;
                            world.removeEntity(nextStep);
                            System.out.println(grassQuantity);
                        }
                        world.moveEntity(path.get(0), nextStep, creature);

                    }else {
                        break;
                    }
                }
            }
            WordRender.worldRender(world);
            System.out.println();

        }

    }*/