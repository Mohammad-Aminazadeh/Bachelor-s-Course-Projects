function parents = doParentSelection(Population,populationSize,parentNumber)
    parentIndices = randi(populationSize,[parentNumber 1]);
    parents = Population(parentIndices);
end