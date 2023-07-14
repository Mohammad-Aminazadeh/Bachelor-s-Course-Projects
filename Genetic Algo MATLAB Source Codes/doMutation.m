function offsprings = doMutation(offsprings,nDim,goldNumber,mutationRate)
    individualSize = numel(offsprings(1).Position);
    for i=1:numel(offsprings)
        if isAllowedtoMutate(mutationRate)
            
            randomNumbers = [-1 -1];
            while randomNumbers(1) == randomNumbers(2)
                randomNumbers = randi(individualSize,[1 2]);
            end
                    
            temp = offsprings(i).Position(randomNumbers(1));
            offsprings(i).Position(randomNumbers(1)) = offsprings(i).Position(randomNumbers(2));
            offsprings(i).Position(randomNumbers(2)) = temp;
            
            offsprings(i).Fitness = CalculateFitness(offsprings(i).Position,nDim,goldNumber);
        end
    end
end