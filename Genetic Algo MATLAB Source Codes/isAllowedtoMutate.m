function flag = isAllowedtoMutate(mutationRate)
    randomNumber = rand();
    if randomNumber <= mutationRate
        flag = 1;
    else
        flag = 0;
    end
end