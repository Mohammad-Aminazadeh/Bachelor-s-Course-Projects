function offsprings = doRecombination(parents,nDim,goldNumber,RecombinationRate)
    individualSize = numel(parents(1).Position);
    parentNumbers = numel(parents);
    offsprings = parents;
    
    for i=1:2:parentNumbers
        if isAllowedtoRecombine(RecombinationRate)
            offspring1 = nan(1,individualSize);
            offspring2 = nan(1,individualSize);
            randomNumber = randi(individualSize,[1 1]);
            parent1 = parents(i).Position(:);
            parent2 = parents(i+1).Position(:);
            offspring1(1:randomNumber) = parents(i).Position(1:randomNumber);
            offspring2(1:randomNumber) = parents(i+1).Position(1:randomNumber);

            offsprings(i).Position = completeOffsprings(randomNumber,individualSize,offspring1,parent2);
            offsprings(i).Fitness = CalculateFitness(offsprings(i).Position,nDim,goldNumber);
            offsprings(i+1).Position = completeOffsprings(randomNumber,individualSize,offspring2,parent1);
            offsprings(i+1).Fitness = CalculateFitness(offsprings(i+1).Position,nDim,goldNumber);
        end
    end
end