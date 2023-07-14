function fitness = CalculateFitness(x,nDim,goldNumber)
    individual = reshape(x,[nDim nDim]);
    fitness = 0;
    dim1 = 0;
    dim2 = 0;
    for i=1:nDim
        sum1 = abs(sum(individual(i,:))- goldNumber);
        sum2 = abs(sum(individual(:,i))- goldNumber);
        fitness = fitness + (sum1+sum2);
        dim1 = dim1 + individual(i,i);
        dim2 = dim2 + individual(i,nDim-i+1);
    end
    fitness = fitness + abs(dim1 - goldNumber);
    fitness = fitness + abs(dim2 - goldNumber);
end