function newPopulation = doSurvivorSelection(Population,offsprings)
    numberofPopulation = numel(Population);
    newPopulation = [Population;offsprings];
    [~,sortOrder] = sort([newPopulation.Fitness]);
    newPopulation = newPopulation(sortOrder);
    newPopulation = newPopulation(1:numberofPopulation);
end